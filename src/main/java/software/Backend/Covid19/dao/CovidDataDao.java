package software.Backend.Covid19.dao;

import org.apache.ibatis.annotations.Mapper;
import software.Backend.Covid19.shared.model.CityCovidData;
import software.Backend.Covid19.shared.model.CountryCovidData;
import software.Backend.Covid19.shared.model.CovidData;
import software.Backend.Covid19.shared.model.MunicipalityCovidData;

import java.util.Date;

@Mapper
public interface CovidDataDao {

    public void insertCovidData(CovidData covidData);
    public void updateCovidData(CovidData covidData);
    public void insertCountryCovidData(CountryCovidData countryCovidData);
    public Integer verifyCountryCovidData(String date,Integer idCountry);
    public Integer getLastIdCovidData();

    public Date lastDateCity(Integer cityId);
    public void createCovidData(CovidData covidData);
    public void createCityCovidData(CityCovidData cityCovidData);
    public Date seeDateExistsCity(String dateData, Integer cityId);

    public Date seeDateExistsMunicipality(String convertido, Integer municipalityId);
    public void createMunicipalityCovidData(MunicipalityCovidData municipalityCovidData);
}
