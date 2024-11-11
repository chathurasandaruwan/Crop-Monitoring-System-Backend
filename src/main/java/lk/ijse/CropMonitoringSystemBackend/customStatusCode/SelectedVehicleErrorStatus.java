package lk.ijse.CropMonitoringSystemBackend.customStatusCode;

import lk.ijse.CropMonitoringSystemBackend.dto.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedVehicleErrorStatus implements VehicleStatus {
    private int statusCode;
    private String StatusMessage;
}
