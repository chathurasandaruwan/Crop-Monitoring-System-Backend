package lk.ijse.CropMonitoringSystemBackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.CropMonitoringSystemBackend.dao.CropDAO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.CropDTO;
import lk.ijse.CropMonitoringSystemBackend.entity.impl.CropEntity;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.service.CropService;
import lk.ijse.CropMonitoringSystemBackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDAO cropDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveCrop(CropDTO cropDTO) {
        CropEntity saveCrop = cropDAO.save(mapping.toCropEntity(cropDTO));
        if (saveCrop==null){
            throw new DataPersistException("Crop not Saved");
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        return mapping.asCropDTOList(cropDAO.findAll());
    }
}
