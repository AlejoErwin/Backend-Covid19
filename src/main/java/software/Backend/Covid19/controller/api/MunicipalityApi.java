package software.Backend.Covid19.controller.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.Backend.Covid19.bl.MunicipalityBl;
import software.Backend.Covid19.bl.util.TransactionUtil;
import software.Backend.Covid19.bl.util.csv.CSVHelper;
import software.Backend.Covid19.shared.model.Transaction;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/v1/municipality")
public class MunicipalityApi {
    private MunicipalityBl municipalityBl;

    private static Logger LOGGER = LoggerFactory.getLogger(MunicipalityApi.class);

    @Autowired
    public MunicipalityApi(MunicipalityBl municipalityBl) {
        this.municipalityBl = municipalityBl;
    }


    @PostMapping(path = "/{isoCity}/admin/{id}")
    public HttpStatus uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String isoCity,
                                 @PathVariable Integer id, HttpServletRequest request) {

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                Transaction transaction = TransactionUtil.createTransactionUtil(request);

                municipalityBl.saveData(file, isoCity, id, transaction);

                return HttpStatus.OK;
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                return HttpStatus.EXPECTATION_FAILED;
            }
        }
        return HttpStatus.BAD_REQUEST;
    }

}
