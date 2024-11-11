package lk.ijse.CropMonitoringSystemBackend.dao;

import lk.ijse.CropMonitoringSystemBackend.entity.impl.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VehicleDAO extends JpaRepository<VehicleEntity, String> {

    Optional<VehicleEntity> findByLicensePlateNum(String licensePlateNum);
}

