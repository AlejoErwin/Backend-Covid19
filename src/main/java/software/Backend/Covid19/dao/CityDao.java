package software.Backend.Covid19.dao;

import org.apache.ibatis.annotations.Mapper;
import software.Backend.Covid19.shared.dto.CityRequest;
import software.Backend.Covid19.shared.dto.DrugstoreRequest;

import java.util.List;

@Mapper
public interface CityDao {

    public List<CityRequest> getCityAll();

}
