package lk.ijse.CropMonitoringSystemBackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.CropMonitoringSystemBackend.dao.VehicleDAO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.VehicleDTO;
import lk.ijse.CropMonitoringSystemBackend.entity.impl.VehicleEntity;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.exeption.VehicleNotFoundException;
import lk.ijse.CropMonitoringSystemBackend.service.VehicleService;
import lk.ijse.CropMonitoringSystemBackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDAO vehicleDAO;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        VehicleEntity savedVehicle = vehicleDAO.save(mapping.toVehicleEntity(vehicleDTO));
        if (savedVehicle==null){
            throw new DataPersistException("Vehicle not Saved");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicle() {
        List<VehicleEntity> allVehicle = vehicleDAO.findAll();
        return mapping.toVehicleDTOList(allVehicle);
    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        Optional<VehicleEntity> vehicleEntity = vehicleDAO.findById(vehicleCode);
        if (!vehicleEntity.isPresent()){
            throw new VehicleNotFoundException("Vehicle not Found");
        }else {
            vehicleDAO.deleteById(vehicleCode);
        }
    }
}
