package lk.ijse.CropMonitoringSystemBackend.util;

import lk.ijse.CropMonitoringSystemBackend.dto.impl.CropDTO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.FieldDTO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.StaffDTO;
import lk.ijse.CropMonitoringSystemBackend.entity.impl.CropEntity;
import lk.ijse.CropMonitoringSystemBackend.entity.impl.FieldEntity;
import lk.ijse.CropMonitoringSystemBackend.entity.impl.StaffEntity;
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
}
