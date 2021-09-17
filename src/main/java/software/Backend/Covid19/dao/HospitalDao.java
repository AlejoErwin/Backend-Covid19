package software.Backend.Covid19.dao;

import org.apache.ibatis.annotations.Mapper;
import software.Backend.Covid19.shared.model.Hospital;

@Mapper
public interface HospitalDao {
    public void addHospital(Hospital hospital);

}