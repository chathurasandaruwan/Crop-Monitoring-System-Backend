package lk.ijse.CropMonitoringSystemBackend.service;

import lk.ijse.CropMonitoringSystemBackend.dto.impl.CropDTO;

public interface CropService {
    void saveCrop(CropDTO cropDTO);
}
