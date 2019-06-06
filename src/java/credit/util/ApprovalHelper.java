package credit.util;

import credit.entities.Client;
import credit.entities.ClientRequest;


public class ApprovalHelper {
    public static boolean checkClientRequest(ClientRequest request) {
        double monthIncome = request.getClient().getIncome() / 12.0;
        double averageMonth = 365.25 / 12.0;
        double monthPay = request.getAmount() * averageMonth / request.getTermInDays();
        double limit = 3.5;
        
        boolean approved = monthIncome / monthPay >= limit;
        
        request.setApproved(approved);
        
        return approved;
    }
}
