package lk.ijse.CropMonitoringSystemBackend.dto.impl;

import lk.ijse.CropMonitoringSystemBackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CropDTO implements SuperDTO {
    private String crop_code;
    private String commonName;
    private String scientific_name;
    private String image;
    private String category;
    private String season;
    private String field_code;
}
