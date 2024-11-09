package lk.ijse.CropMonitoringSystemBackend.customStatusCode;

import lk.ijse.CropMonitoringSystemBackend.dto.StaffStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedStaffErrorStatus implements StaffStatus {
    private int statusCode;
    private String StatusMessage;
}
