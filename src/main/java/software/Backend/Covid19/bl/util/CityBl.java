package software.Backend.Covid19.bl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.Backend.Covid19.dao.CityDao;
import software.Backend.Covid19.dao.DrugstoreDao;
import software.Backend.Covid19.dao.TransactionDao;
import software.Backend.Covid19.shared.dto.CityRequest;
import software.Backend.Covid19.shared.dto.DrugstoreRequest;

import java.util.List;

@Service
public class CityBl {
    private TransactionDao transactionDao;
    private CityDao cityDao;

    @Autowired
    public CityBl(TransactionDao transactionDao, CityDao cityDao) {
        this.transactionDao = transactionDao;
        this.cityDao = cityDao;
    }

    public List<CityRequest> getCityAll() {
        return cityDao.getCityAll();
    }

}
