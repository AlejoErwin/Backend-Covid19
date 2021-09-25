package software.Backend.Covid19.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.Backend.Covid19.bl.DrugstoreBl;
import software.Backend.Covid19.bl.util.CityBl;
import software.Backend.Covid19.shared.dto.CityRequest;
import software.Backend.Covid19.shared.dto.DrugstoreRequest;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/city")
public class CityApi {
    private CityBl cityBl;

    @Autowired
    public CityApi(CityBl cityBl) {
        this.cityBl = cityBl;
    }


    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CityRequest> getCityAll(){
        return cityBl.getCityAll();
    }
}
