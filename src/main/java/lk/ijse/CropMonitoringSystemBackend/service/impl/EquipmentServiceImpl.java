package lk.ijse.CropMonitoringSystemBackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.CropMonitoringSystemBackend.dao.EquipmentDAO;
import lk.ijse.CropMonitoringSystemBackend.dao.FieldDAO;
import lk.ijse.CropMonitoringSystemBackend.dao.StaffDAO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.EquipmentDTO;
import lk.ijse.CropMonitoringSystemBackend.entity.impl.EquipmentEntity;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.exeption.EquipmentNotFoundException;
import lk.ijse.CropMonitoringSystemBackend.service.EquipmentService;
import lk.ijse.CropMonitoringSystemBackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDAO equipmentDAO;
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        EquipmentEntity equipmentEntity = equipmentDAO.save(mapping.toEquipmentEntity(equipmentDTO));
        if (equipmentEntity==null){
            throw new DataPersistException("Equipment not Saved");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        return mapping.toEquipmentDTOList(equipmentDAO.findAll());
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<EquipmentEntity> equipmentEntity = equipmentDAO.findById(equipmentId);
        if (!equipmentEntity.isPresent()){
            throw new EquipmentNotFoundException("Equipment not Found");
        }else {
            equipmentDAO.deleteById(equipmentId);
        }
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        Optional<EquipmentEntity> equipmentEntity = equipmentDAO.findById(equipmentId);
        if (!equipmentEntity.isPresent()){
            throw new EquipmentNotFoundException("Equipment not Found");
        }else {
            equipmentEntity.get().setStatus(equipmentDTO.getStatus());
            equipmentEntity.get().setName(equipmentDTO.getName());
            equipmentEntity.get().setType(equipmentDTO.getType());
            equipmentEntity.get().setStatus(equipmentDTO.getStatus());
            equipmentEntity.get().setStaff(staffDAO.getReferenceById(equipmentDTO.getStaffId()));
            equipmentEntity.get().setField(fieldDAO.getReferenceById(equipmentDTO.getField_code()));
        }
    }

    @Override
    public EquipmentDTO getSelectedEquipmentByName(String name) {
        Optional<EquipmentEntity> entityOptional = equipmentDAO.findByName(name);
        if (!entityOptional.isPresent()){
            throw new EquipmentNotFoundException("Equipment Not Found");
        }else {
            return mapping.toEquipmentDTO(entityOptional.get());
        }
    }
}
