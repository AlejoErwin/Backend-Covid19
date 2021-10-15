package software.Backend.Covid19.bl.util.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import software.Backend.Covid19.shared.dto.DataCsvCityRequest;
import software.Backend.Covid19.shared.dto.DataCsvMunicipalityRequest;

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


    public static Logger LOGGER = LoggerFactory.getLogger(CSVHelper.class);
    public static boolean hasCSVFormat(MultipartFile file) {
        System.out.println(file.getContentType());
        if (TYPE.equals(file.getContentType())
                || file.getContentType().equals("application/vnd.ms-excel")) {
            return true;
        }

        return false;
    }
    public static List<DataCsvCityRequest> CityCsvRequest(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<DataCsvCityRequest> dataCityCsvRequestList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (CSVRecord csvRecord : csvRecords) {
                DataCsvCityRequest dataCityCsvRequest = new DataCsvCityRequest();
                dataCityCsvRequest.setDate(sdf.parse(csvRecord.get("Fecha")));
                //LOGGER.error(String.valueOf(dataCityCsvRequest.getDate()));
                dataCityCsvRequest.setConfirmed(Integer.parseInt(csvRecord.get("Confirm")));
                dataCityCsvRequest.setConfirmedCumulative(Integer.parseInt(csvRecord.get("Confirm_Acum")));
                dataCityCsvRequest.setDeaths(Integer.parseInt(csvRecord.get("Muertes")));
                dataCityCsvRequest.setDeathsCumulative(Integer.parseInt(csvRecord.get("Muertes_Acum")));
                dataCityCsvRequest.setRecovered(Integer.parseInt(csvRecord.get("Recuperados")));
                dataCityCsvRequest.setRecoveredCumulative(Integer.parseInt(csvRecord.get("Recuperados_Acum")));
                dataCityCsvRequest.setVaccinated(Integer.parseInt(csvRecord.get("Vacunados_Prim"))+Integer.parseInt(csvRecord.get("Vacunados_Seg")));
                dataCityCsvRequestList.add(dataCityCsvRequest);
            }

            return dataCityCsvRequestList;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static List<DataCsvMunicipalityRequest> MunicipalityCsvRequest(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<DataCsvMunicipalityRequest> dataCsvMunicipalityRequest = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (CSVRecord csvRecord : csvRecords) {
                DataCsvMunicipalityRequest dataCsvMunicipalityRequestList = new DataCsvMunicipalityRequest();
                dataCsvMunicipalityRequestList.setMunicipality(csvRecord.get("Municipio"));
                dataCsvMunicipalityRequestList.setDate(sdf.parse(csvRecord.get("Fecha")));
                dataCsvMunicipalityRequestList.setConfirmedTotal(Integer.parseInt(csvRecord.get("Total_confirmados")));
                dataCsvMunicipalityRequestList.setAssetsTotal(Integer.parseInt(csvRecord.get("Total_activos")));
                dataCsvMunicipalityRequestList.setRecoveredTotal(Integer.parseInt(csvRecord.get("Total_recuperado")));
                dataCsvMunicipalityRequestList.setDeceasedTotal(Integer.parseInt(csvRecord.get("Total_fallecido")));
                dataCsvMunicipalityRequest.add(dataCsvMunicipalityRequestList);

            }
            return dataCsvMunicipalityRequest;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
