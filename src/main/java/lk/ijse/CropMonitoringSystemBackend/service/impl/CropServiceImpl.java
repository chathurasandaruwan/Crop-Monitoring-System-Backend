package lk.ijse.CropMonitoringSystemBackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.CropMonitoringSystemBackend.dao.CropDAO;
import lk.ijse.CropMonitoringSystemBackend.dao.FieldDAO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.CropDTO;
import lk.ijse.CropMonitoringSystemBackend.entity.impl.CropEntity;
import lk.ijse.CropMonitoringSystemBackend.entity.impl.FieldEntity;
import lk.ijse.CropMonitoringSystemBackend.exeption.CropNotFoundException;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.service.CropService;
import lk.ijse.CropMonitoringSystemBackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDAO cropDAO;

    @Autowired
    private FieldDAO fieldDAO;


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

    @Override
    public void deleteCrop(String cropCode) {
        Optional<CropEntity> existCrop = cropDAO.findById(cropCode);
        if (!existCrop.isPresent()){
            throw new CropNotFoundException("Crop Not Found");
        }else {
            cropDAO.deleteById(cropCode);
        }
    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) {
        Optional<CropEntity> existCrop = cropDAO.findById(cropCode);
        if (!existCrop.isPresent()){
            throw new CropNotFoundException("Crop Not Found");
        }else {
            Optional<FieldEntity> fieldEntity = fieldDAO.findById(cropDTO.getField_code());
            existCrop.get().setCommonName(cropDTO.getCommonName());
            existCrop.get().setScientific_name(cropDTO.getScientific_name());
            existCrop.get().setImage(cropDTO.getImage());
            existCrop.get().setCategory(cropDTO.getCategory());
            existCrop.get().setSeason(cropDTO.getSeason());
            existCrop.get().setField(fieldEntity.get());
        }
    }

    @Override
    public CropDTO getSelectedCropByName(String name) {
        Optional<CropEntity> existCrop = cropDAO.findByCommonName(name);
        if (!existCrop.isPresent()){
            throw new CropNotFoundException("Crop Not Found");
        }else {
            return mapping.toCropDTO(existCrop.get());
        }
    }
}
