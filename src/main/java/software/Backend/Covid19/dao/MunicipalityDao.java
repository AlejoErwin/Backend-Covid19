package software.Backend.Covid19.dao;

import org.apache.ibatis.annotations.Mapper;
import software.Backend.Covid19.shared.dto.CityRequest;

import java.util.List;

@Mapper
public interface MunicipalityDao {

    public Integer municipalityIdByName(String municipality,Integer idCity);
}
