package software.Backend.Covid19.bl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.Backend.Covid19.dao.*;

@Service
public class MunicipalityBl {

    private TransactionDao transactionDao;
    private MunicipalityDao municipalityDao;

    private static Logger LOGGER = LoggerFactory.getLogger(CityBl.class);
    @Autowired
    public MunicipalityBl(TransactionDao transactionDao, MunicipalityDao municipalityDao) {
        this.transactionDao = transactionDao;
        this.municipalityDao = municipalityDao;
    }
}
