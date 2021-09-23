package software.Backend.Covid19.bl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.Backend.Covid19.dao.CountryDao;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;

@Component
public class CovidDataUtil {
    private CountryDao countryDao;

    @Autowired
    public void CovidDataUtil(CountryDao countryDao){
        this.countryDao = countryDao;
    }

    public ArrayList<ArrayList> getVaccine(String country, Integer length){
        ArrayList<ArrayList> vaccineData = new ArrayList();
        try {
            URL url = new URL("https://disease.sh/v3/covid-19/vaccine/coverage/countries/"+country+"?lastdays="+length.toString());

            Calendar c = Calendar.getInstance();


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public ArrayList<ArrayList> getDataCovid(String country, Integer length){
        return null;
    }

    /*
    public static String formatCalender(Calender c){
        DataFormat dataFormat = new SimpleDateFormat("M/d/yy");
        return dataFormat.format(c.getTime());
    }

     */
}
