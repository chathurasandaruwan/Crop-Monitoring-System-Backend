package lk.ijse.CropMonitoringSystemBackend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.CropMonitoringSystemBackend.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "log")
public class MonitoringLogEntity implements SuperEntity {

    @Id
    private String log_code;
    private Date date;
    private String details;
    @Column(columnDefinition = "LONGTEXT")
    private String image;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "log_field",
            joinColumns = @JoinColumn(name = "log_id"),
            inverseJoinColumns = @JoinColumn(name = "field_id"))
    private List<FieldEntity> fields;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "log_crop",
            joinColumns = @JoinColumn(name = "log_id"),
            inverseJoinColumns = @JoinColumn(name = "crop_id"))
    private List<CropEntity> crops;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "log_staff",
            joinColumns = @JoinColumn(name = "log_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id"))
    private List<StaffEntity> staffs;


}
