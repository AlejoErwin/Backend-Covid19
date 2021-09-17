package software.Backend.Covid19.dao;

import org.apache.ibatis.annotations.Mapper;
import software.Backend.Covid19.shared.model.Transaction;

@Mapper
public interface TransactionDao {
    public Integer getLastInsertId();
}