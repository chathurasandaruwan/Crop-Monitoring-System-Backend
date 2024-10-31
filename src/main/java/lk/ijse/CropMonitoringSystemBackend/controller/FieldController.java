package lk.ijse.CropMonitoringSystemBackend.controller;

import lk.ijse.CropMonitoringSystemBackend.dto.impl.FieldDTO;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.exeption.FieldNotFoundException;
import lk.ijse.CropMonitoringSystemBackend.service.FieldService;
import lk.ijse.CropMonitoringSystemBackend.util.AppUtil;
import lk.ijse.CropMonitoringSystemBackend.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {
    @Autowired
    private FieldService fieldService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestPart("field_name") String fieldName,
            @RequestPart("location.x") String x,
            @RequestPart("location.y") String y,
            @RequestPart("extent_size") String extentSize,
            @RequestPart("image1") MultipartFile image1,
            @RequestPart("image2") MultipartFile image2
    ) {
        //generate field code
        String fieldCode = AppUtil.generateFieldCode();
        //image ---> Base64
        String image1Base64 ="";
        String image2Base64 ="";

        // Create and set Point object
        Point point = new Point();
        point.setLocation(Double.valueOf(x),Double.valueOf(y));
        try {

            image1Base64 = AppUtil.imageToBase64(image1.getBytes());
            image2Base64 = AppUtil.imageToBase64(image2.getBytes());
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setField_code(fieldCode);
            fieldDTO.setField_name(fieldName);
            fieldDTO.setLocation(point);
            fieldDTO.setImage1(image1Base64 );
            fieldDTO.setImage2(image2Base64);
            fieldDTO.setExtent_size(Double.valueOf(extentSize));

            //call service layer
            fieldService.saveField(fieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getAllFields", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllFields() {
        return fieldService.getAllFields();
    }
    @PutMapping(value = "/{fieldCode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateField(
            @RequestPart("field_name") String fieldName,
            @RequestPart("location.x") String x,
            @RequestPart("location.y") String y,
            @RequestPart("extent_size") String extentSize,
            @RequestPart("image1") MultipartFile image1,
            @RequestPart("image2") MultipartFile image2,
            @PathVariable String fieldCode){
        //image ---> Base64
        String image1Base64 ="";
        String image2Base64 ="";
        // Create and set Point object
        Point point = new Point();
        point.setLocation(Double.valueOf(x),Double.valueOf(y));
        try {
            image1Base64 = AppUtil.imageToBase64(image1.getBytes());
            image2Base64 = AppUtil.imageToBase64(image2.getBytes());
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setField_code(fieldCode);
            fieldDTO.setField_name(fieldName);
            fieldDTO.setLocation(point);
            fieldDTO.setImage1(image1Base64);
            fieldDTO.setImage2(image2Base64);
            fieldDTO.setExtent_size(Double.valueOf(extentSize));

            if(!RegexProcess.FieldCodeMatcher(fieldCode) || fieldDTO == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            //call service layer
            fieldService.updateField(fieldCode,fieldDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{fieldCode}")
    public ResponseEntity<Void> deleteUser(@PathVariable("fieldCode") String fieldCode) {
        try {
            if (!RegexProcess.FieldCodeMatcher(fieldCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            //TODO: call service layer
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
