package lk.ijse.CropMonitoringSystemBackend.dto.impl;

import lk.ijse.CropMonitoringSystemBackend.dto.SuperDTO;
import lk.ijse.CropMonitoringSystemBackend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements SuperDTO {
    private String email;
    private String password;
    private Role role;
}
