package software.Backend.Covid19.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.Backend.Covid19.bl.DrugstoreBl;
import software.Backend.Covid19.bl.util.CityBl;
import software.Backend.Covid19.bl.util.TransactionUtil;
import software.Backend.Covid19.bl.util.csv.CSVHelper;
import software.Backend.Covid19.shared.dto.CityRequest;
import software.Backend.Covid19.shared.dto.DrugstoreRequest;
import software.Backend.Covid19.shared.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/city")
public class CityApi {
    private CityBl cityBl;

    @Autowired
    public CityApi(CityBl cityBl) {
        this.cityBl = cityBl;
    }

    private static Logger LOGGER = LoggerFactory.getLogger(CityApi.class);

    @PostMapping(path = "/{isoCity}/admin/{id}")
    public HttpStatus uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String isoCity,
                                 @PathVariable Integer id,HttpServletRequest request) {

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                Transaction transaction = TransactionUtil.createTransactionUtil(request);

                LOGGER.error("entre xd 1");
                cityBl.saveData(file, isoCity, id, transaction);

                return HttpStatus.OK;
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                return HttpStatus.EXPECTATION_FAILED;
            }
        }
        return HttpStatus.BAD_REQUEST;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CityRequest> getCityAll(){
        return cityBl.getCityAll();
    }
}
