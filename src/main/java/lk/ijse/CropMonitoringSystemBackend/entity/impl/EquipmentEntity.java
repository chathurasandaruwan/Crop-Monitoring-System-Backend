package lk.ijse.CropMonitoringSystemBackend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.CropMonitoringSystemBackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class EquipmentEntity implements SuperDTO {
    @Id
    private String equipment_id;
    @Column(unique = true)
    private String name;
    private String status;
    private String type;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    StaffEntity staff;
    @ManyToOne
    @JoinColumn(name = "field_id")
    FieldEntity field;
}
