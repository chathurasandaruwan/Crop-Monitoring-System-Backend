package lk.ijse.CropMonitoringSystemBackend.service;

import lk.ijse.CropMonitoringSystemBackend.dto.StaffStatus;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);

    List<StaffDTO> getAllStaff();

    void deleteStaff(String staffId);

    void updateStaff(String staffId, StaffDTO staffDTO);

    StaffStatus getSelectedStaffMemberByName(String name);
}
