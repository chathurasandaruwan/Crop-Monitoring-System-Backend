package lk.ijse.CropMonitoringSystemBackend.dao;

import lk.ijse.CropMonitoringSystemBackend.entity.impl.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CropDAO extends JpaRepository<CropEntity, String> {

    Optional<CropEntity> findByCommonName(String name);
}

