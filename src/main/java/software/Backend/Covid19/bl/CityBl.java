package software.Backend.Covid19.bl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.Backend.Covid19.bl.util.csv.CSVHelper;
import software.Backend.Covid19.dao.*;
import software.Backend.Covid19.shared.dto.CityRequest;
import software.Backend.Covid19.shared.dto.DataCsvCityRequest;
import software.Backend.Covid19.shared.model.CityCovidData;
import software.Backend.Covid19.shared.model.CovidData;
import software.Backend.Covid19.shared.model.Transaction;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CityBl {
    private TransactionDao transactionDao;
    private CityDao cityDao;
    private CovidDataDao covidDataDao;

    private static Logger LOGGER = LoggerFactory.getLogger(CityBl.class);
    @Autowired
    public CityBl(TransactionDao transactionDao, CityDao cityDao,CovidDataDao covidDataDao) {
        this.transactionDao = transactionDao;
        this.cityDao = cityDao;
        this.covidDataDao=covidDataDao;
    }

    public void saveData(MultipartFile file, String isoCity, Integer userId, Transaction transaction){
        try{

            Integer cityId = cityDao.cityIdByIso(isoCity);
            LOGGER.error(String.valueOf(cityId));
            List<DataCsvCityRequest> dataCsvCityRequest = CSVHelper.CityCsvRequest(file.getInputStream());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //Date lastDateCovid = covidDataDao.lastDateCity(cityId);
            //LOGGER.error("Last: "+String.valueOf(lastDateCovid));
            //if(lastDateCovid == null){
              //  String aux = "1970-01-01";
               // lastDateCovid = sdf.parse(aux);
            //}

            CovidData covidData = new CovidData();

            for(DataCsvCityRequest data : dataCsvCityRequest){
                //if(lastDateCovid.before(data.getDate())){

                covidData.setDate(data.getDate());
                DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
                String convertido = fecha.format(covidData.getDate());
                Date exists=covidDataDao.seeDateExistsCity(convertido,cityId);

                if (exists==null){
                    covidData.setIdPageUrl(null);

                    covidData.setConfirmedCases(data.getConfirmed());
                    covidData.setConfirmedCumulative(data.getConfirmedCumulative());

                    covidData.setDeathCases(data.getDeaths());
                    covidData.setDeathCumulative(data.getDeathsCumulative());

                    covidData.setRecuperated(data.getRecovered());
                    covidData.setRecuperatedCumulative(data.getRecoveredCumulative());

                    covidData.setVaccinated(data.getVaccinated());
                    covidData.setStatus(1);
                    covidData.setTransaction(transaction);

                    covidDataDao.createCovidData(covidData);
                    CityCovidData cityCovidData = new CityCovidData();
                    cityCovidData.setIdCity(cityId);
                    cityCovidData.setIdCovidData(transactionDao.getLastInsertId());

                    cityCovidData.setStatus(1);
                    cityCovidData.setTransaction(transaction);
                    covidDataDao.createCityCovidData(cityCovidData);
                }

              //  }


            }


        } catch (IOException e){
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }



    public List<CityRequest> getCityAll() {
        List<CityRequest> data =cityDao.getCityAll();
        List<CityRequest> cityRequests = new ArrayList<>();
        for (int i=0;i<data.size();i++){
            CityRequest cityRequest = new CityRequest();
            cityRequest= data.get(i);
            CityRequest cityData = cityDao.getDataByCity(cityRequest.getCityId());
            if (cityData!=null){
                cityRequest.setDeaths(cityData.getDeaths());
                cityRequest.setConfirmed(cityData.getConfirmed());
                cityRequest.setRecovered(cityData.getRecovered());
            }
            cityRequests.add(cityRequest);
        }
        return cityRequests;
    }



}
