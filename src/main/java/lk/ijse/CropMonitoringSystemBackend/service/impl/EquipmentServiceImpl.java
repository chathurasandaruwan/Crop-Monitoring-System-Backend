package lk.ijse.CropMonitoringSystemBackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.EquipmentDTO;
import lk.ijse.CropMonitoringSystemBackend.service.EquipmentService;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {

    }
}
