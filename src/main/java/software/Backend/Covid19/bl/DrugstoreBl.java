package software.Backend.Covid19.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.Backend.Covid19.dao.DrugstoreDao;
import software.Backend.Covid19.dao.HospitalDao;
import software.Backend.Covid19.dao.TransactionDao;
import software.Backend.Covid19.shared.dto.DrugstoreRequest;
import software.Backend.Covid19.shared.dto.HospitalRequest;

import java.util.List;

@Service
public class DrugstoreBl {
    private TransactionDao transactionDao;
    private DrugstoreDao drugstoreDao;
    
    @Autowired
    public DrugstoreBl(TransactionDao transactionDao, DrugstoreDao drugstoreDao) {
        this.transactionDao = transactionDao;
        this.drugstoreDao = drugstoreDao;
    }

    public List<DrugstoreRequest> getHospitalCity(Integer cityId) {
        return drugstoreDao.getHospitalCityId(cityId);
    }

    public List<DrugstoreRequest> getHospitalAll() {
        return drugstoreDao.getHospitalAll();
    }
}
