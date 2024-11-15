package lk.ijse.CropMonitoringSystemBackend.util;

import lk.ijse.CropMonitoringSystemBackend.dto.impl.*;
import lk.ijse.CropMonitoringSystemBackend.entity.impl.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper mapper;

    //    for field mapping
    public FieldDTO toFieldDTO(FieldEntity field){
        return mapper.map(field,FieldDTO.class);
    }
    public FieldEntity toFieldEntity(FieldDTO field){
        return mapper.map(field,FieldEntity.class);
    }

    public List<FieldDTO> asFieldDTOList(List<FieldEntity> all) {
        return mapper.map(all, new TypeToken<List<FieldDTO>>(){}.getType());
    }
    //    for crop mapping
    public CropDTO toCropDTO(CropEntity crop){
        return mapper.map(crop, CropDTO.class);
    }
    public CropEntity toCropEntity(CropDTO crop){
        return mapper.map(crop,CropEntity.class);
    }

    public List<CropDTO> asCropDTOList(List<CropEntity> all) {
        return mapper.map(all, new TypeToken<List<CropDTO>>(){}.getType());
    }
//    for staff mapping
    public StaffEntity toStaffEntity(StaffDTO staffDTO) {
        return mapper.map(staffDTO,StaffEntity.class);
    }

    public List<StaffDTO> toStaffDTOList(List<StaffEntity> all) {
        return mapper.map(all,new TypeToken<List<StaffDTO>>(){}.getType());
    }

    public StaffDTO toStaffDTO(StaffEntity staffEntity) {
        return mapper.map(staffEntity,StaffDTO.class);
    }

//    for vehicle mapping
    public VehicleEntity toVehicleEntity(VehicleDTO vehicleDTO) {
        return mapper.map(vehicleDTO,VehicleEntity.class);
    }

    public List<VehicleDTO> toVehicleDTOList(List<VehicleEntity> allVehicle) {
        return mapper.map(allVehicle,new TypeToken<List<VehicleDTO>>(){}.getType());
    }

    public VehicleDTO toVehicleDTO(VehicleEntity vehicleEntity) {
        return mapper.map(vehicleEntity,VehicleDTO.class);
    }

//    for Equipment mapping
    public EquipmentEntity toEquipmentEntity(EquipmentDTO equipmentDTO) {
        return mapper.map(equipmentDTO, EquipmentEntity.class);
    }

    public List<EquipmentDTO> toEquipmentDTOList(List<EquipmentEntity> all) {
        return mapper.map(all,new TypeToken<List<EquipmentDTO>>(){}.getType());
    }

    public EquipmentDTO toEquipmentDTO(EquipmentEntity equipmentEntity) {
        return mapper.map(equipmentEntity,EquipmentDTO.class);
    }

//    for log mapping
    public MonitoringLogEntity toLogEntity(MonitoringLogDTO monitoringLogDTO) {
        return mapper.map(monitoringLogDTO,MonitoringLogEntity.class);
    }

    public List<MonitoringLogDTO> asLogDTOList(List<MonitoringLogEntity> all) {
        return mapper.map(all,new TypeToken<List<MonitoringLogDTO>>(){}.getType());
    }
}
