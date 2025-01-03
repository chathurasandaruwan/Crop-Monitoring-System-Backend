package lk.ijse.CropMonitoringSystemBackend.dao;

import lk.ijse.CropMonitoringSystemBackend.entity.impl.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentDAO extends JpaRepository<EquipmentEntity,String> {
    Optional<EquipmentEntity> findByName(String name);
}
