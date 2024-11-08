package lk.ijse.CropMonitoringSystemBackend.customStatusCode;

import lk.ijse.CropMonitoringSystemBackend.dto.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedCropErrorStatus implements FieldStatus {
    private int statusCode;
    private String StatusMessage;
}
