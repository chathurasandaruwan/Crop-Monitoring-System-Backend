package lk.ijse.CropMonitoringSystemBackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.CropMonitoringSystemBackend.dao.FieldDAO;
import lk.ijse.CropMonitoringSystemBackend.dao.StaffDAO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.FieldDTO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.FieldStaffDTO;
import lk.ijse.CropMonitoringSystemBackend.entity.impl.FieldEntity;
import lk.ijse.CropMonitoringSystemBackend.entity.impl.StaffEntity;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.exeption.FieldNotFoundException;
import lk.ijse.CropMonitoringSystemBackend.exeption.StaffNotFoundException;
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
    private StaffDAO staffDAO;
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
            tmpField.get().setFieldName(fieldDTO.getFieldName());
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

    @Override
    public FieldDTO getSelectedFieldByName(String name) {
        Optional<FieldEntity> existField = fieldDAO.findByFieldName(name);
        if (!existField.isPresent()){
            throw new FieldNotFoundException("Field Not Found");
        }else {
            return mapping.toFieldDTO(existField.get());
        }
    }

    @Override
    public void saveFieldStaff(FieldStaffDTO fieldStaffDTO) {
        Optional<FieldEntity> fieldOpt = fieldDAO.findById(fieldStaffDTO.getField_code());
        Optional<StaffEntity> staffOpt = staffDAO.findById(fieldStaffDTO.getStaffId());
        if(!fieldOpt.isPresent()) {
            throw new FieldNotFoundException(fieldStaffDTO.getField_code() + " : Field Does Not Exist");
        } else if(!staffOpt.isPresent()) {
            throw new StaffNotFoundException(fieldStaffDTO.getStaffId() + " : Staff Does Not Exist");
        }
        FieldEntity field = fieldOpt.get();
        StaffEntity staff = staffOpt.get();
        if (field.getStaffs().contains(staff)) {
            throw new DataPersistException(fieldStaffDTO.getField_code() + " : Field Already Have This Staff : " + fieldStaffDTO.getStaffId());
        }
        field.getStaffs().add(staff);
        staff.getFields().add(field);
        fieldDAO.save(field);
    }

    @Override
    public void deleteFieldStaff(String fieldCode, String staffId) {
        Optional<FieldEntity> fieldOpt = fieldDAO.findById(fieldCode);
        Optional<StaffEntity> staffOpt = staffDAO.findById(staffId);
        if(!fieldOpt.isPresent()) {
            throw new FieldNotFoundException(fieldCode + " : Field Does Not Exist");
        } else if(!staffOpt.isPresent()) {
            throw new StaffNotFoundException(staffId + " : Staff Does Not Exist");
        }
        FieldEntity field = fieldOpt.get();
        StaffEntity staff = staffOpt.get();
        field.getStaffs().remove(staff);
        staff.getFields().remove(field);
        fieldDAO.save(field);
    }


}
