package software.Backend.Covid19.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.Backend.Covid19.bl.DrugstoreBl;
import software.Backend.Covid19.shared.dto.DrugstoreRequest;
import software.Backend.Covid19.shared.dto.HospitalRequest;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/drugstore")
public class DrugstoreApi {
    private DrugstoreBl drugstoreBl;

    @Autowired
    public DrugstoreApi(DrugstoreBl drugstoreBl) {
        this.drugstoreBl = drugstoreBl;
    }

    @GetMapping(path = "/list/{cityId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DrugstoreRequest> getDrugstoreCity(@PathVariable String cityId){
        return drugstoreBl.getHospitalCity(Integer.parseInt(cityId));
    }
    @GetMapping(path = "/list/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DrugstoreRequest> getDrugtoreCity(){
        return drugstoreBl.getHospitalAll();
    }
}
