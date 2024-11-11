package lk.ijse.CropMonitoringSystemBackend.controller;

import lk.ijse.CropMonitoringSystemBackend.dto.impl.StaffDTO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.VehicleDTO;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.util.AppUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveVehicle(@RequestBody VehicleDTO vehicleDTO){
        try {
            vehicleDTO.setVehicle_code(AppUtil.generateVehicleCode());
            // call service layer

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
