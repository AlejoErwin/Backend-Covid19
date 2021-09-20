package software.Backend.Covid19.bl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.Backend.Covid19.dao.CountryDao;

import java.util.ArrayList;

@Component
public class CovidDataUtil {
    private CountryDao countryDao;

    @Autowired
    public void CovidDataUtil(CountryDao countryDao){
        this.countryDao = countryDao;
    }

    public ArrayList<ArrayList> getVaccine(String country, Integer length){
        return null;
    }

    public ArrayList<ArrayList> getDataCovid(String country, Integer length){
        return null;
    }

}
