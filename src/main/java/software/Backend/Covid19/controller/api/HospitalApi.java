package software.Backend.Covid19.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import software.Backend.Covid19.bl.HospitalBl;
import software.Backend.Covid19.bl.util.TransactionUtil;
import software.Backend.Covid19.shared.model.Hospital;
import software.Backend.Covid19.shared.model.Transaction;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/v1/hospital")

public class HospitalApi {
    private HospitalBl hospitalBl;

    @Autowired
    public HospitalApi(HospitalBl hospitalBl){
        this.hospitalBl = hospitalBl;
    }


    @RequestMapping( method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Hospital insertHospital(@RequestBody Hospital hospital, HttpServletRequest request) {
        TransactionUtil transactionUtil= new TransactionUtil();
        Transaction transaction = transactionUtil.createTransactionUtil(request);
        hospitalBl.addHospital(hospital,transaction);
        return hospital;
    }
}
