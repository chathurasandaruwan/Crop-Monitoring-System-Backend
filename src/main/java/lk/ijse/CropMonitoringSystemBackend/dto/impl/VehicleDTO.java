package lk.ijse.CropMonitoringSystemBackend.dto.impl;

import lk.ijse.CropMonitoringSystemBackend.dto.SuperDTO;
import lk.ijse.CropMonitoringSystemBackend.dto.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO implements VehicleStatus {
    private String vehicle_code;
    private String licensePlateNum;
    private String category;
    private String fuel_type;
    private String status;
    private String Remarks;
    private String staffId;
}
