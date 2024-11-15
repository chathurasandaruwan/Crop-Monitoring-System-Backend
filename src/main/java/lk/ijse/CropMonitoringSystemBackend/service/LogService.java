package lk.ijse.CropMonitoringSystemBackend.service;

import lk.ijse.CropMonitoringSystemBackend.dto.impl.MonitoringLogDTO;

public interface LogService {
    void saveLog(MonitoringLogDTO monitoringLogDTO);
}
