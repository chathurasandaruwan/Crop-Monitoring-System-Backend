package lk.ijse.CropMonitoringSystemBackend.controller;

import lk.ijse.CropMonitoringSystemBackend.dto.impl.EquipmentDTO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.StaffDTO;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.exeption.EquipmentNotFoundException;
import lk.ijse.CropMonitoringSystemBackend.exeption.StaffNotFoundException;
import lk.ijse.CropMonitoringSystemBackend.service.EquipmentService;
import lk.ijse.CropMonitoringSystemBackend.util.AppUtil;
import lk.ijse.CropMonitoringSystemBackend.util.RegexProcess;
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
    @DeleteMapping(value = "/{equipment_id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("equipment_id") String equipment_id) {
        try {
            if (!RegexProcess.equipmentIdMatcher(equipment_id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            equipmentService.deleteEquipment(equipment_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EquipmentNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{equipment_id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEquipment(@RequestBody EquipmentDTO equipmentDTO, @PathVariable String equipment_id){
        try {
            if (!RegexProcess.equipmentIdMatcher(equipment_id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            equipmentService.updateEquipment(equipment_id,equipmentDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EquipmentNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
