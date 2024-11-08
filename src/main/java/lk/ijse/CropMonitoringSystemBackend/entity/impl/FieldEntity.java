package lk.ijse.CropMonitoringSystemBackend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.CropDTO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.StaffDTO;
import lk.ijse.CropMonitoringSystemBackend.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "field")
public class FieldEntity implements SuperEntity {
    @Id
    private String field_code;
    @Column(unique = true)
    private String fieldName;
    private Point location;
    private Double extent_size;
    @Column(columnDefinition = "LONGTEXT")
    private String image1;
    @Column(columnDefinition = "LONGTEXT")
    private String image2;

    @OneToMany(mappedBy = "field")
    private List<CropEntity> cropDTOS;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "staff_field_details",
            joinColumns = @JoinColumn(name = "field_code"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    private List<StaffEntity> staffs;
}
