package software.Backend.Covid19.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.Backend.Covid19.dao.CountryDao;
import software.Backend.Covid19.dao.CovidDataDao;
import software.Backend.Covid19.shared.dto.LocationResponse;

import java.util.List;

@Service
public class CountryBl {
    private CountryDao countryDao;
    private CovidDataDao covidDataDao;

    @Autowired
    public CountryBl(CountryDao countryDao,CovidDataDao covidDataDao){
        this.countryDao = countryDao;
        this.covidDataDao=covidDataDao;
    }

    public List<LocationResponse> getCountries(){
        return countryDao.countries();
    }

}
