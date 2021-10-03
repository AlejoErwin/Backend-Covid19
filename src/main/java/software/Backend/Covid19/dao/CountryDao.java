package software.Backend.Covid19.dao;

import org.apache.ibatis.annotations.Mapper;
import software.Backend.Covid19.shared.dto.LocationResponse;

import java.util.List;

@Mapper
public interface CountryDao {

    public List<LocationResponse> countries();

}
