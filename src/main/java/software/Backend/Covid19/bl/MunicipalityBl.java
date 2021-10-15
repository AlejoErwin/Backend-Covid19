package software.Backend.Covid19.bl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.Backend.Covid19.bl.util.csv.CSVHelper;
import software.Backend.Covid19.dao.*;
import software.Backend.Covid19.shared.dto.DataCsvCityRequest;
import software.Backend.Covid19.shared.dto.DataCsvMunicipalityRequest;
import software.Backend.Covid19.shared.model.CityCovidData;
import software.Backend.Covid19.shared.model.CovidData;
import software.Backend.Covid19.shared.model.MunicipalityCovidData;
import software.Backend.Covid19.shared.model.Transaction;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MunicipalityBl {

    private TransactionDao transactionDao;
    private MunicipalityDao municipalityDao;
    private CityDao cityDao;
    private CovidDataDao covidDataDao;

    private static Logger LOGGER = LoggerFactory.getLogger(CityBl.class);
    @Autowired
    public MunicipalityBl(TransactionDao transactionDao, MunicipalityDao municipalityDao,CityDao cityDao,CovidDataDao covidDataDao) {
        this.transactionDao = transactionDao;
        this.municipalityDao = municipalityDao;
        this.cityDao = cityDao;
        this.covidDataDao= covidDataDao;
    }

    public void saveData(MultipartFile file, String isoCity, Integer userId, Transaction transaction){
        try{

            Integer cityId = cityDao.cityIdByIso(isoCity);
            List<DataCsvMunicipalityRequest> dataCsvMunicipalityRequest = CSVHelper.MunicipalityCsvRequest(file.getInputStream());
            CovidData covidData = new CovidData();

            for(DataCsvMunicipalityRequest data : dataCsvMunicipalityRequest){
                covidData.setDate(data.getDate());
                DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
                String convertido = fecha.format(covidData.getDate());
                Integer municipalityId = municipalityDao.municipalityIdByName(data.getMunicipality(),cityId); // id del municipio
                Date exists=covidDataDao.seeDateExistsMunicipality(convertido,municipalityId); // verifica la fecha si existe

                if (exists==null){
                    covidData.setIdPageUrl(null);
                    //covidData.setConfirmedCases(data.getConfirmed());
                    covidData.setConfirmedCumulative(data.getAssetsTotal());
                    //covidData.setDeathCases(data.getDeaths());
                    covidData.setDeathCumulative(data.getDeceasedTotal());
                    //covidData.setRecuperated(data.getRecovered());
                    covidData.setRecuperatedCumulative(data.getRecoveredTotal());
                    covidData.setStatus(1);
                    covidData.setTransaction(transaction);
                    covidDataDao.createCovidData(covidData);

                    MunicipalityCovidData municipalityCovidData = new MunicipalityCovidData();
                    municipalityCovidData.setIdMunicipality(municipalityId);
                    municipalityCovidData.setIdCovidData(transactionDao.getLastInsertId());
                    municipalityCovidData.setStatus(1);
                    municipalityCovidData.setTransaction(transaction);
                    covidDataDao.createMunicipalityCovidData(municipalityCovidData);
                }

            }


        } catch (IOException e){
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
