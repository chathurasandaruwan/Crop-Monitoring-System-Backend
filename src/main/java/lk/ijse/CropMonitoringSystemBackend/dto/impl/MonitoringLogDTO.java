package lk.ijse.CropMonitoringSystemBackend.dto.impl;

import lk.ijse.CropMonitoringSystemBackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitoringLogDTO implements SuperDTO {
    private String log_code;
    private Date date;
    private String details;
    private String image;
    private List<FieldDTO> fieldDTOS;
    private List<CropDTO> cropDTOS;
    private List<StaffDTO> staffDTOS;


}