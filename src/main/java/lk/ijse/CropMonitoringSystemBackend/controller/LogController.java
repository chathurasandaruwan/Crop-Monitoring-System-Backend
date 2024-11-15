package lk.ijse.CropMonitoringSystemBackend.controller;

import lk.ijse.CropMonitoringSystemBackend.dto.impl.*;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.exeption.LogNotFoundException;
import lk.ijse.CropMonitoringSystemBackend.service.LogService;
import lk.ijse.CropMonitoringSystemBackend.util.AppUtil;
import lk.ijse.CropMonitoringSystemBackend.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/log")
public class LogController {
    @Autowired
    private LogService logService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveLog(@RequestParam("tempId") String tempId,
                                        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd")Date date,
                                        @RequestParam("details") String details,
                                        @RequestParam("image") MultipartFile image,
                                        @RequestParam(value = "logFields", required = false, defaultValue = "") List<String> logFields,
                                        @RequestParam(value = "logCrops", required = false, defaultValue = "")List<String> logCrops,
                                        @RequestParam(value = "logStaff", required = false, defaultValue = "")List<String> logStaff) {
        String logCode = AppUtil.generateLogCode();
        String imageBase64 = "";
        try {
            imageBase64=AppUtil.imageToBase64(image.getBytes());
            List<FieldDTO> fieldDTOS = new ArrayList<>();
            for (String logField : logFields) {
                FieldDTO fieldDTO = new FieldDTO();
                fieldDTO.setField_code(logField);
                fieldDTOS.add(fieldDTO);
            }
            List<CropDTO> cropDTOS = new ArrayList<>();
            for (String logCrop : logCrops) {
                CropDTO cropDTO = new CropDTO();
                cropDTO.setCrop_code(logCrop);
                cropDTOS.add(cropDTO);
            }
            List<StaffDTO> staffDTOS = new ArrayList<>();
            for (String logStaff1 : logStaff) {
                StaffDTO staffDTO = new StaffDTO();
                staffDTO.setId(logStaff1);
                staffDTOS.add(staffDTO);
            }
            //call service layer
            logService.saveLog(new MonitoringLogDTO(logCode,tempId, date, details, imageBase64, fieldDTOS, cropDTOS, staffDTOS));
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getAllLog", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitoringLogDTO> getAllLog() {
        return logService.getAllLogs();
    }

    @DeleteMapping(value = "/{tempId}")
    public ResponseEntity<Void> deleteLog(@PathVariable("tempId") String tempId) {
        try {
            if (!RegexProcess.logCodeMatcher(tempId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            logService.deleteLog(tempId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (LogNotFoundException e) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
