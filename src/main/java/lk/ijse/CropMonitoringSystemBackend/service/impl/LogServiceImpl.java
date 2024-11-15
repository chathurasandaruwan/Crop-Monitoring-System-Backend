package lk.ijse.CropMonitoringSystemBackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.CropMonitoringSystemBackend.dao.CropDAO;
import lk.ijse.CropMonitoringSystemBackend.dao.FieldDAO;
import lk.ijse.CropMonitoringSystemBackend.dao.LogDAO;
import lk.ijse.CropMonitoringSystemBackend.dao.StaffDAO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.CropDTO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.FieldDTO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.MonitoringLogDTO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.StaffDTO;
import lk.ijse.CropMonitoringSystemBackend.entity.impl.MonitoringLogEntity;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.service.LogService;
import lk.ijse.CropMonitoringSystemBackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDAO logDAO;
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private CropDAO cropDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveLog(MonitoringLogDTO monitoringLogDTO) {
        MonitoringLogEntity logEntity = mapping.toLogEntity(monitoringLogDTO);

        List<String> logFields = new ArrayList<>();
        for (FieldDTO logField : monitoringLogDTO.getFields()) {
            logFields.add(logField.getField_code());
        }
        List<String> logCrops = new ArrayList<>();
        for (CropDTO logCrop : monitoringLogDTO.getCrops()) {
            logCrops.add(logCrop.getCrop_code());
        }
        List<String> logStaff = new ArrayList<>();
        for (StaffDTO staffDTO : monitoringLogDTO.getStaffs()) {
            logStaff.add(staffDTO.getId());
        }


        logEntity.setStaffs(staffDAO.findAllById(logStaff));
        logEntity.setCrops(cropDAO.findAllById(logCrops));
        logEntity.setFields(fieldDAO.findAllById(logFields));
        MonitoringLogEntity savedEntity = logDAO.save(logEntity);
        if (savedEntity==null){
            throw new DataPersistException("Log not Saved");
        }
    }
    @Override
    public List<MonitoringLogDTO> getAllLogs() {
        return mapping.asLogDTOList(logDAO.findAll());
    }
}
