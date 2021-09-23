package software.Backend.Covid19.dao;

import org.apache.ibatis.annotations.Mapper;
import software.Backend.Covid19.shared.dto.DrugstoreRequest;

import java.util.List;

@Mapper
public interface DrugstoreDao {

    public List<DrugstoreRequest> getHospitalCityId(Integer cityId);

    public List<DrugstoreRequest> getHospitalAll();
}
