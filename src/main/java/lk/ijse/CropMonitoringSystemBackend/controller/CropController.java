package lk.ijse.CropMonitoringSystemBackend.controller;

import lk.ijse.CropMonitoringSystemBackend.dto.impl.CropDTO;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.exeption.FieldNotFoundException;
import lk.ijse.CropMonitoringSystemBackend.service.CropService;
import lk.ijse.CropMonitoringSystemBackend.util.AppUtil;
import lk.ijse.CropMonitoringSystemBackend.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/crop")
public class CropController {
    @Autowired
    private CropService cropService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(
            @RequestPart("common_name") String common_name,
            @RequestPart("scientific_name") String scientific_name,
            @RequestPart("image")MultipartFile image,
            @RequestPart("category")String category,
            @RequestPart("season") String season,
            @RequestPart("field_code")String field_code
            ){
        //generate crop code
        String cropCode = AppUtil.generateCropCode();
        String imageBase64 = "";
        try {
            imageBase64 = AppUtil.imageToBase64(image.getBytes());
            CropDTO cropDTO = new CropDTO();
            cropDTO.setCrop_code(cropCode);
            cropDTO.setCommonName(common_name);
            cropDTO.setScientific_name(scientific_name);
            cropDTO.setImage(imageBase64);
            cropDTO.setCategory(category);
            cropDTO.setSeason(season);
            cropDTO.setField_code(field_code);
            //call service layer
            cropService.saveCrop(cropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getAllCrops",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrops(){
        return cropService.getAllCrops();
    }

    @DeleteMapping(value = "/{CropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable("CropCode") String CropCode) {
        try {
            if (!RegexProcess.CropCodeMatcher(CropCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            // TODO:call service layer
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
