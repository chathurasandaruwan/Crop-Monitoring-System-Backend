package lk.ijse.CropMonitoringSystemBackend.controller;

import lk.ijse.CropMonitoringSystemBackend.customStatusCode.SelectedVehicleErrorStatus;
import lk.ijse.CropMonitoringSystemBackend.dto.VehicleStatus;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.StaffDTO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.VehicleDTO;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.exeption.VehicleNotFoundException;
import lk.ijse.CropMonitoringSystemBackend.service.VehicleService;
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
@RequestMapping("api/v1/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveVehicle(@RequestBody VehicleDTO vehicleDTO){
        try {
            vehicleDTO.setVehicle_code(AppUtil.generateVehicleCode());
            // call service layer
            vehicleService.saveVehicle(vehicleDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getAllVehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> getAllVehicle() {
        return vehicleService.getAllVehicle();
    }

    @DeleteMapping(value = "/{vehicle_code}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicle_code") String vehicle_code) {
        try {
            if (!RegexProcess.vehicleCodeMatcher(vehicle_code)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            vehicleService.deleteVehicle(vehicle_code);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (VehicleNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{vehicle_code}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateVehicle(@RequestBody VehicleDTO vehicleDTO, @PathVariable String vehicle_code){
        try {
            if (!RegexProcess.vehicleCodeMatcher(vehicle_code)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            vehicleService.updateVehicle(vehicle_code,vehicleDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (VehicleNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{licensePlateNum}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleStatus getSelectedVehicleByLicensePlateNum(@PathVariable("licensePlateNum") String licensePlateNum) {
        try {
            return vehicleService.getSelectedVehicleByLicensePlateNum(licensePlateNum);
        } catch (VehicleNotFoundException e) {
            e.printStackTrace();
            return new SelectedVehicleErrorStatus(1,"Vehicle Not Found");
        }
    }

}
