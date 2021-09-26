package software.Backend.Covid19.bl.util;

import software.Backend.Covid19.dao.*;
import software.Backend.Covid19.shared.dto.*;
import software.Backend.Covid19.shared.model.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class CovidDataUtil {
    private CovidDataDao covidDataDao;
    private Transaction transaction;
    private CountryDao countryDao;

    @Autowired
    public void CovidDataU(CovidDataDao covidDataDao,CountryDao countryDao) {
        this.covidDataDao = covidDataDao;
        this.countryDao=countryDao;

    }
    public ArrayList<ArrayList> getDataCovid(String country, Integer length) {
        ArrayList<ArrayList> covidData = new ArrayList();
        JSONParser parser = new JSONParser();
        try {
            URL url = new URL("https://disease.sh/v3/covid-19/historical/" + country + "?lastdays=" + length.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
            con.setRequestMethod("GET");
            con.setRequestProperty("accept", "application/json");
            InputStream responseStream = con.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            CovidDataHistory caseHistory = mapper.readValue(responseStream, CovidDataHistory.class);
            String strReplacement1 = caseHistory.getTimeline().toString();
            String strReplacement2 = strReplacement1.replaceAll("\\\\", "");
            Object obj = parser.parse(strReplacement2);
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject innerObjectRec = (JSONObject) jsonObject.get("recovered");
            JSONObject innerObjectCas = (JSONObject) jsonObject.get("cases");
            JSONObject innerObjectDea = (JSONObject) jsonObject.get("deaths");
            Calendar cal = Calendar.getInstance();
            Date date;
            cal.add(Calendar.DAY_OF_YEAR, -length);
            date = cal.getTime();
            ArrayList arr;
            for(int i=0;i<length;i++){
                arr=new ArrayList();
                arr.add(date);
                if(innerObjectCas.get(formatCalendar(cal))==null){
                    arr.add(-1);
                    arr.add(-1);
                    arr.add(-1);
                }
                else{
                    arr.add(Integer.parseInt(""+innerObjectCas.get(formatCalendar(cal))));
                    arr.add(Integer.parseInt(""+innerObjectDea.get(formatCalendar(cal))));
                    arr.add(Integer.parseInt(""+innerObjectRec.get(formatCalendar(cal))));
                }
                covidData.add(arr);
                cal.add(Calendar.DAY_OF_YEAR, 1);
                date = cal.getTime();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return covidData;
    }

    public ArrayList<ArrayList> getVaccine(String country, Integer length){
        ArrayList<ArrayList> vaccineData = new ArrayList();
        try {

            URL url = new URL("https://disease.sh/v3/covid-19/vaccine/coverage/countries/"+country+"?lastdays="+length.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("accept", "application/json");
            InputStream responseStream = con.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            Vaccines vaccines = mapper.readValue(responseStream, Vaccines.class);
            Calendar c = Calendar.getInstance();
            Date date;
            c.add(Calendar.DAY_OF_YEAR, -length);
            date = c.getTime();
            ArrayList arr;
            System.out.println(c.getTime());
            for(int i=0;i<length;i++){
                arr=new ArrayList();
                arr.add(date);
                if((Integer) vaccines.getTimeline().get(formatCalendar(c))==null){
                    arr.add(-1);
                }
                else{
                    arr.add((Integer) vaccines.getTimeline().get(formatCalendar(c)));
                }
                vaccineData.add(arr);
                c.add(Calendar.DAY_OF_YEAR, 1);
                date = c.getTime();
            }
            return vaccineData;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //@Scheduled(fixedRate = 30000L)
    // @GetMapping(value = "/swagger")
    public void readDataJsonSwagger() {
        try {
            List<LocationResponse> countries=countryDao.countries();
            for(int i=0;i<countries.size();i++){
                var general=getDataCovid(countries.get(i).getLocationName(),300);
                var vaccine=getVaccine(countries.get(i).getLocationName(),300);
                System.out.println(vaccine.toString());
                Date date;
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                DateFormat dateSelect = new SimpleDateFormat("yyyy-MM-dd");
                cal.set(Calendar.HOUR, cal.get(Calendar.HOUR)- 4);
                date = cal.getTime();
                CovidData covidData;
                CountryCovidData countryCovidData;
                Transaction transaction;
                Integer selectData;
                Integer covidDataId;
                String dateString;
                for(int j=0;j<general.size();j++){
                    covidData = new CovidData();
                    transaction = new Transaction();
                    transaction.setTxDate(date);
                    InetAddress ipAddress = InetAddress.getLocalHost();
                    String localIP = ipAddress.getHostAddress();
                    transaction.setTxHost(localIP);
                    transaction.setTxId(1);
                    transaction.setTxUpdate(date);
                    covidData.setIdPageUrl(4);
                    if(j == 0){
                        covidData.setDeathCases((Integer)general.get(j).get(2));
                        covidData.setConfirmedCases((Integer) general.get(i).get(1));
                        covidData.setVaccinated((Integer)vaccine.get(i).get(1));
                        covidData.setCumulativeCases((Integer) general.get(j).get(1));
                        covidData.setRecuperated((Integer) general.get(i).get(3));
                    }
                    else{
                        covidData.setDeathCases((Integer) general.get(j).get(2)-(Integer) general.get(j-1).get(2));
                        covidData.setConfirmedCases((Integer) general.get(j).get(1)-(Integer) general.get(j-1).get(1));
                        covidData.setVaccinated(-1);
                        covidData.setCumulativeCases((Integer) general.get(j).get(1));
                        covidData.setRecuperated((Integer) general.get(j).get(3)-(Integer) general.get(j-1).get(3));
                    }
                    covidData.setDate((Date) general.get(j).get(0));
                    covidData.setTransaction(transaction);
                    dateString=dateSelect.format((Date) general.get(j).get(0));
                    selectData = covidDataDao.verifyCountryCovidData(dateString,countries.get(i).getIdLocation());
                    if(j!=0 && j!=1) {
                        if (selectData == 0) {
                            covidDataDao.insertCovidData(covidData);
                            covidDataId = covidDataDao.getLastIdCovidData();
                            countryCovidData = new CountryCovidData();
                            countryCovidData.setIdCountry(countries.get(i).getIdLocation());
                            countryCovidData.setIdCovidData(covidDataId);
                            countryCovidData.setTransaction(transaction);
                            covidDataDao.insertCountryCovidData(countryCovidData);
                            for(int k=0;k < vaccine.size();k++){
                                String dateVaccine = dateSelect.format(vaccine.get(k).get(0));
                                String dateGeneral = dateSelect.format(general.get(j).get(0));
                                if(dateVaccine.compareTo(dateGeneral) == 0){
                                    covidData.setIdCovidData(covidDataId);
                                    covidData.setVaccinated((Integer)vaccine.get(j).get(1)-(Integer)vaccine.get(j-1).get(1));
                                    if(covidData.getVaccinated() >= 0){
                                        covidDataDao.updateCovidData(covidData);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String formatCalendar(Calendar c){
        DateFormat dateFormat = new SimpleDateFormat("M/d/yy");
        return dateFormat.format(c.getTime());
    }

}
