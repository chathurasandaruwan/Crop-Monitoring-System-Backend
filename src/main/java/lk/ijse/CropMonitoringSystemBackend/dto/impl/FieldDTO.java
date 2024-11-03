package lk.ijse.CropMonitoringSystemBackend.dto.impl;

import lk.ijse.CropMonitoringSystemBackend.dto.FieldStatus;
import lk.ijse.CropMonitoringSystemBackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldDTO implements FieldStatus {
    private String field_code;
    private String fieldName;
    private Point location;
    private Double extent_size;
    private String image1;
    private String image2;
    private List<CropDTO> cropDTOS;
//    private List<StaffDTO> staffDTOS;

}
