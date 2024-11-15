package lk.ijse.CropMonitoringSystemBackend.dao;

import lk.ijse.CropMonitoringSystemBackend.entity.impl.MonitoringLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LogDAO extends JpaRepository<MonitoringLogEntity, String> {
    Optional<MonitoringLogEntity> findByTempId(String tempId);
}

