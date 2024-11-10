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
        System.out.println(saveStaff.getDob());
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

    @Override
    public void updateStaff(String staffId, StaffDTO staffDTO) {
        Optional<StaffEntity> staffEntity = staffDAO.findById(staffId);
        if (!staffEntity.isPresent()){
            throw new StaffNotFoundException("Staff not Found");
        }else {
            staffEntity.get().setFirstName(staffDTO.getFirstName());
            staffEntity.get().setLastName(staffDTO.getLastName());
            staffEntity.get().setDesignation(staffDTO.getDesignation());
            staffEntity.get().setGender(staffDTO.getGender());
            staffEntity.get().setJoinedDate(staffDTO.getJoinedDate());
            staffEntity.get().setDob(staffDTO.getDob());
            staffEntity.get().setAddress1(staffDTO.getAddress1());
            staffEntity.get().setAddress2(staffDTO.getAddress2());
            staffEntity.get().setAddress3(staffDTO.getAddress3());
            staffEntity.get().setAddress4(staffDTO.getAddress4());
            staffEntity.get().setAddress5(staffDTO.getAddress5());
            staffEntity.get().setContactNo(staffDTO.getContactNo());
            staffEntity.get().setEmail(staffDTO.getEmail());
            staffEntity.get().setRole(staffDTO.getRole());
        }
    }

    @Override
    public StaffDTO getSelectedStaffMemberByName(String name) {
       Optional<StaffEntity>existStaff = staffDAO.findByFirstName(name);
        if (!existStaff.isPresent()){
            throw new StaffNotFoundException("Staff Not Found");
        }else {
            return mapping.toStaffDTO(existStaff.get());
        }
    }

}
