package lk.ijse.CropMonitoringSystemBackend.customStatusCode;

import lk.ijse.CropMonitoringSystemBackend.dto.EquipmentStatus;
import lk.ijse.CropMonitoringSystemBackend.dto.StaffStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedEquipmentErrorStatus implements EquipmentStatus {
    private int statusCode;
    private String StatusMessage;
}
