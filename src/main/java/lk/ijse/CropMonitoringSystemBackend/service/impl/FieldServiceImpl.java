package lk.ijse.CropMonitoringSystemBackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.CropMonitoringSystemBackend.dao.FieldDAO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.FieldDTO;
import lk.ijse.CropMonitoringSystemBackend.entity.impl.FieldEntity;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.exeption.FieldNotFoundException;
import lk.ijse.CropMonitoringSystemBackend.service.FieldService;
import lk.ijse.CropMonitoringSystemBackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveField(FieldDTO fieldDTO) {
        FieldEntity saveField = fieldDAO.save(mapping.toFieldEntity(fieldDTO));
        if (saveField==null){
            throw new DataPersistException("Field not Saved");
        }
    }
    @Override
    public List<FieldDTO> getAllFields() {
        return mapping.asFieldDTOList(fieldDAO.findAll());
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {
        Optional<FieldEntity> tmpField = fieldDAO.findById(fieldCode);
        if (!tmpField.isPresent()){
            throw new FieldNotFoundException("Field Not Found");
        }else {
            tmpField.get().setField_name(fieldDTO.getField_name());
            tmpField.get().setLocation(fieldDTO.getLocation());
            tmpField.get().setExtent_size(fieldDTO.getExtent_size());
            tmpField.get().setImage1(fieldDTO.getImage1());
            tmpField.get().setImage2(fieldDTO.getImage2());
        }

    }
    @Override
    public void deleteField(String fieldCode) {
        Optional<FieldEntity> existField = fieldDAO.findById(fieldCode);
        if (!existField.isPresent()){
            throw new FieldNotFoundException("Field Not Found");
        }else {
            fieldDAO.deleteById(fieldCode);
        }
    }

}
