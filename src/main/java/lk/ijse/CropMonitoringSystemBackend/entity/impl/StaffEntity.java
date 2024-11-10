package lk.ijse.CropMonitoringSystemBackend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.CropMonitoringSystemBackend.entity.SuperEntity;
import lk.ijse.CropMonitoringSystemBackend.enums.Designation;
import lk.ijse.CropMonitoringSystemBackend.enums.Gender;
import lk.ijse.CropMonitoringSystemBackend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity implements SuperEntity {
    @Id
    private String id;
    @Column(unique = true)
    private String firstName;
    private String lastName;
    private Designation designation;
    private Gender gender;
    private Date joinedDate;
    private Date dob;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
//    private List<String> addresses;
    private String contactNo;
    @Column(unique = true)
    private String email;
    private Role role;
    @ManyToMany(mappedBy = "staffs", cascade = CascadeType.ALL)
    private List<FieldEntity> fieldEntities;

}
