package lk.ijse.CropMonitoringSystemBackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.CropMonitoringSystemBackend.dao.StaffDAO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.StaffDTO;
import lk.ijse.CropMonitoringSystemBackend.entity.impl.StaffEntity;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.exeption.StaffNotFoundException;
import lk.ijse.CropMonitoringSystemBackend.service.StaffService;
import lk.ijse.CropMonitoringSystemBackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveStaff(StaffDTO staffDTO) {
        StaffEntity saveStaff = staffDAO.save(mapping.toStaffEntity(staffDTO));
        if (saveStaff==null){
            throw new DataPersistException("Staff not Saved");
        }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return mapping.toStaffDTOList(staffDAO.findAll());
    }

    @Override
    public void deleteStaff(String staffId) {
        Optional<StaffEntity> staffEntity = staffDAO.findById(staffId);
        if (!staffEntity.isPresent()){
            throw new StaffNotFoundException("Staff not Found");
        }else {
            staffDAO.deleteById(staffId);
        }
    }

}
