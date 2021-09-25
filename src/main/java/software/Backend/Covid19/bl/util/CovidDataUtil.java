package software.Backend.Covid19.bl.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.Backend.Covid19.dao.CountryDao;
import software.Backend.Covid19.shared.dto.CovidDataHistory;
import software.Backend.Covid19.shared.model.Vaccines;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;

@Component
public class CovidDataUtil {
    private CountryDao countryDao;

    /*
    @Autowired
    public void CovidDataUtil(CountryDao countryDao){
        this.countryDao = countryDao;
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
            CovidDataHistory caseHistory = mapper.readValue(responseStream, CaseHistory.class);
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
                caseData.add(arr);
                cal.add(Calendar.DAY_OF_YEAR, 1);
                date = cal.getTime();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return caseData;
    }

    public ArrayList<ArrayList> getVaccine(String country, Integer length){
        ArrayList<ArrayList> vaccineData = new ArrayList();
        try {

            URL url = new URL("https://disease.sh/v3/covid-19/vaccine/coverage/countries/"+country+"?lastdays="+length.toString());
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

    public static String formatCalender(Calender c){
        DataFormat dataFormat = new SimpleDateFormat("M/d/yy");
        return dataFormat.format(c.getTime());
    }
    */
}
