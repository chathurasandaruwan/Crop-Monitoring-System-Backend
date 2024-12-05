package lk.ijse.CropMonitoringSystemBackend.controller;

import lk.ijse.CropMonitoringSystemBackend.customStatusCode.SelectedCropErrorStatus;
import lk.ijse.CropMonitoringSystemBackend.customStatusCode.SelectedStaffErrorStatus;
import lk.ijse.CropMonitoringSystemBackend.dto.CropStatus;
import lk.ijse.CropMonitoringSystemBackend.dto.StaffStatus;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.StaffDTO;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.exeption.FieldNotFoundException;
import lk.ijse.CropMonitoringSystemBackend.exeption.StaffNotFoundException;
import lk.ijse.CropMonitoringSystemBackend.service.StaffService;
import lk.ijse.CropMonitoringSystemBackend.util.AppUtil;
import lk.ijse.CropMonitoringSystemBackend.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/v1/staff")
public class StaffController {
    @Autowired
    private StaffService service;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(@RequestBody StaffDTO staffDTO){
        try {
            staffDTO.setId(AppUtil.generateStaffId());
            // call service layer
            service.saveStaff(staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getAllStaff", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getAllStaff() {
        return service.getAllStaff();
    }

    @DeleteMapping(value = "/{staff_id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable("staff_id") String staff_id) {
        try {
            if (!RegexProcess.staffIdMatcher(staff_id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            service.deleteStaff(staff_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{staff_id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateStaff(@RequestBody StaffDTO staffDTO, @PathVariable String staff_id){
        try {
            if (!RegexProcess.staffIdMatcher(staff_id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            service.updateStaff(staff_id,staffDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffStatus getSelectedStaffMemberByName(@PathVariable("name") String name) {
        try {
            return service.getSelectedStaffMemberByName(name);
        } catch (StaffNotFoundException e) {
            e.printStackTrace();
            return new SelectedStaffErrorStatus(1, "Staff Not Found");
        }
    }
}
