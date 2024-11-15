package lk.ijse.CropMonitoringSystemBackend.service;

import lk.ijse.CropMonitoringSystemBackend.dto.EquipmentStatus;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);

    List<EquipmentDTO> getAllEquipment();

    void deleteEquipment(String equipmentId);

    void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO);

    EquipmentDTO getSelectedEquipmentByName(String name);
}
