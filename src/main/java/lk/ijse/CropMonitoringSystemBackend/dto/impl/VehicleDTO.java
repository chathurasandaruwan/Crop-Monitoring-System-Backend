package lk.ijse.CropMonitoringSystemBackend.dto.impl;

import lk.ijse.CropMonitoringSystemBackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO implements SuperDTO {
    private String vehicle_code;
    private String license_plate_num;
    private String category;
    private String fuel_type;
    private String status;
    private StaffDTO staff;
    private String Remarks;
}
