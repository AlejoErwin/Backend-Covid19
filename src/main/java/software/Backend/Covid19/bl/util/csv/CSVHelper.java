package software.Backend.Covid19.bl.util.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import software.Backend.Covid19.shared.dto.DataCsvCityRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";

    static String[] HEADERs = { "Fecha", "Casos", "Casos_Acum", "Muertes", "Muertes_Acum", "Recuperados", "Recuperados_Acum"};
    public static List<DataCsvCityRequest> csvToDataDepartmentCsvRequest(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<DataCsvCityRequest> dataDepartmentCsvRequestList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (CSVRecord csvRecord : csvRecords) {
                DataCsvCityRequest dataDepartmentCsvRequest = new DataCsvCityRequest();
                dataDepartmentCsvRequest.setDate(sdf.parse(csvRecord.get("Fecha")));
                //LOGGER.error(String.valueOf(dataCsvRequest.getDate()));
                dataDepartmentCsvRequest.setConfirmed(Integer.parseInt(csvRecord.get("Casos")));
                dataDepartmentCsvRequest.setConfirmedCumulative(Integer.parseInt(csvRecord.get("Casos_Acum")));
                dataDepartmentCsvRequest.setDeaths(Integer.parseInt(csvRecord.get("Muertes")));
                dataDepartmentCsvRequest.setDeathsCumulative(Integer.parseInt(csvRecord.get("Muertes_Acum")));
                dataDepartmentCsvRequest.setRecovered(Integer.parseInt(csvRecord.get("Recuperados")));
                dataDepartmentCsvRequest.setRecoveredCumulative(Integer.parseInt(csvRecord.get("Recuperados_Acum")));
                dataDepartmentCsvRequest.setVaccinated(Integer.parseInt(csvRecord.get("Vacunados")));
                dataDepartmentCsvRequestList.add(dataDepartmentCsvRequest);
            }

            return dataDepartmentCsvRequestList;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
