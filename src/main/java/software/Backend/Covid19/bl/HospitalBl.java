package software.Backend.Covid19.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.Backend.Covid19.dao.HospitalDao;
import software.Backend.Covid19.dao.TransactionDao;
import software.Backend.Covid19.shared.model.Hospital;
import software.Backend.Covid19.shared.model.Transaction;
@Service
public class HospitalBl {
    private HospitalDao hospitalDao;
    private TransactionDao transactionDao;

    @Autowired
    public HospitalBl(HospitalDao hospitalDao, TransactionDao transactionDao){
        this.hospitalDao = hospitalDao;
        this.transactionDao = transactionDao;
    }

    public Hospital addHospital(Hospital hospital, Transaction transaction) {
        hospital.setTransaction(transaction);

        hospitalDao.addHospital(hospital);
        Integer hospitalId = transactionDao.getLastInsertId();
        hospital.setHospitalId(hospitalId);
        return hospital;
    }
}
