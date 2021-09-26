package software.Backend.Covid19.dao;


import org.apache.ibatis.annotations.Mapper;
import software.Backend.Covid19.shared.dto.HospitalRequest;
import software.Backend.Covid19.shared.model.Hospital;

import java.util.List;

@Mapper
public interface HospitalDao {
    public void addHospital(Hospital hospital);
    public List<HospitalRequest> getHospitalCityId(Integer cityId);

    public List<HospitalRequest> getHospitalAll();
}