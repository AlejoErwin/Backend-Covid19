package software.Backend.Covid19.bl.util;

import software.Backend.Covid19.shared.model.Transaction;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

public class TransactionUtil {
    public static Transaction createTransactionUtil(HttpServletRequest request){
        Transaction transaction = new Transaction();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        Date tempDate = cal.getTime();
        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR)- 4);
        tempDate = cal.getTime();

        transaction.setTxDate(tempDate);
        transaction.setTxUpdate(tempDate);
        transaction.setTxHost(request.getRemoteHost());
        transaction.setTxId(0);
        return transaction;
    }
}
