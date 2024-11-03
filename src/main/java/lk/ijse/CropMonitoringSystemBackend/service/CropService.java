package lk.ijse.CropMonitoringSystemBackend.service;

import lk.ijse.CropMonitoringSystemBackend.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO);
    List<CropDTO> getAllCrops();
    void deleteCrop(String cropCode);

    void updateCrop(String cropCode, CropDTO cropDTO);
}
