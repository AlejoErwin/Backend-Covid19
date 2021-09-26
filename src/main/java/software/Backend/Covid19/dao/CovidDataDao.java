package software.Backend.Covid19.dao;

import org.apache.ibatis.annotations.Mapper;
import software.Backend.Covid19.shared.model.CountryCovidData;
import software.Backend.Covid19.shared.model.CovidData;

@Mapper
public interface CovidDataDao {

    public void insertCovidData(CovidData covidData);
    public void updateCovidData(CovidData covidData);
    public void insertCountryCovidData(CountryCovidData countryCovidData);
    public Integer verifyCountryCovidData(String date,Integer idCountry);
    public Integer getLastIdCovidData();
}
