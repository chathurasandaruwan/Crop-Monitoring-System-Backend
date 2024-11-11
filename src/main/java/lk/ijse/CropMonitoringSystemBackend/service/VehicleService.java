package lk.ijse.CropMonitoringSystemBackend.service;

import lk.ijse.CropMonitoringSystemBackend.dto.VehicleStatus;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);

    List<VehicleDTO> getAllVehicle();

    void deleteVehicle(String vehicleCode);

    void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO);

    VehicleStatus getSelectedVehicleByLicensePlateNum(String licensePlateNum);

}
