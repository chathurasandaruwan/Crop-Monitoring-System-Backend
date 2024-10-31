package lk.ijse.CropMonitoringSystemBackend.controller;

import lk.ijse.CropMonitoringSystemBackend.dto.impl.FieldDTO;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.util.AppUtil;
import lk.ijse.CropMonitoringSystemBackend.util.RegexProcess;
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
        point.setLocation(Double.parseDouble(x),Double.parseDouble(y));
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

            System.out.println(fieldDTO.getField_code());
            System.out.println(fieldDTO.getField_name());
            System.out.println(fieldDTO.getLocation());
            System.out.println(fieldDTO.getExtent_size());
            //TODO:call service layer
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getAllFields", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllFields() {
        return null;
    }
}
