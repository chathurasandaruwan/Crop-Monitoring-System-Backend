package lk.ijse.CropMonitoringSystemBackend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.CropMonitoringSystemBackend.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "crop")
public class CropEntity implements SuperEntity {
    @Id
    private String crop_code;
    @Column(unique = true)
    private String commonName;
    private String scientific_name;
    @Column(columnDefinition = "LONGTEXT")
    private String image;
    private String category;
    private String season;
    @ManyToOne
    @JoinColumn(name = "field_code",nullable = false)
    private FieldEntity field;
    @ManyToMany(mappedBy = "crops", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<MonitoringLogEntity> logs;
}
