package software.Backend.Covid19.controller.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.Backend.Covid19.bl.MunicipalityBl;

@RestController
@RequestMapping(value = "/v1/municipality")
public class MunicipalityApi {
    private MunicipalityBl municipalityBl;

    private static Logger LOGGER = LoggerFactory.getLogger(MunicipalityApi.class);

    @Autowired
    public MunicipalityApi(MunicipalityBl municipalityBl) {
        this.municipalityBl = municipalityBl;
    }




}
