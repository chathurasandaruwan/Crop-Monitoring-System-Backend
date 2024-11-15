package lk.ijse.CropMonitoringSystemBackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.CropMonitoringSystemBackend.dao.LogDAO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.MonitoringLogDTO;
import lk.ijse.CropMonitoringSystemBackend.entity.impl.MonitoringLogEntity;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.service.LogService;
import lk.ijse.CropMonitoringSystemBackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDAO logDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveLog(MonitoringLogDTO monitoringLogDTO) {
        MonitoringLogEntity savedEntity = logDAO.save(mapping.toLogEntity(monitoringLogDTO));
        if (savedEntity==null){
            throw new DataPersistException("Log not Saved");
        }
    }

    @Override
    public List<MonitoringLogDTO> getAllLogs() {
        return mapping.asLogDTOList(logDAO.findAll());
    }
}
