package lk.ijse.CropMonitoringSystemBackend.dto.impl;

import lk.ijse.CropMonitoringSystemBackend.dto.SuperDTO;
import lk.ijse.CropMonitoringSystemBackend.enums.Designation;
import lk.ijse.CropMonitoringSystemBackend.enums.Gender;
import lk.ijse.CropMonitoringSystemBackend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO implements SuperDTO {
    private String id;
    private String first_name;
    private String last_name;
    private Designation designation;
    private Gender gender;
    private Date joined_date;
    private Date DOB;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String contact_no;
    private String email;
    private Role role;

//    TODO:field and vehicle list need added

}
