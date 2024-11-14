package lk.ijse.CropMonitoringSystemBackend.controller;

import lk.ijse.CropMonitoringSystemBackend.dto.impl.EquipmentDTO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.StaffDTO;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.service.EquipmentService;
import lk.ijse.CropMonitoringSystemBackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEquipment(@RequestBody EquipmentDTO equipmentDTO){
        try {
            equipmentDTO.setEquipment_id(AppUtil.generateEquipmentId());
            // call service layer
            equipmentService.saveEquipment(equipmentDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getAllEquipment", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDTO> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }
}
