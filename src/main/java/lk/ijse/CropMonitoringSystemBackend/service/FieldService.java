package lk.ijse.CropMonitoringSystemBackend.service;

import lk.ijse.CropMonitoringSystemBackend.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);

    List<FieldDTO> getAllFields();

    void updateField(String fieldCode, FieldDTO fieldDTO);

    void deleteField(String fieldCode);
}
