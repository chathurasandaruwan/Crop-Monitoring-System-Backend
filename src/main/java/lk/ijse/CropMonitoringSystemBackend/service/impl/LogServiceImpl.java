package lk.ijse.CropMonitoringSystemBackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.CropMonitoringSystemBackend.dao.LogDAO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.MonitoringLogDTO;
import lk.ijse.CropMonitoringSystemBackend.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDAO logDAO;
    @Override
    public void saveLog(MonitoringLogDTO monitoringLogDTO) {

    }
}
