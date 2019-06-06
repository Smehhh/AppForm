package credit.util;

import credit.entities.Client;
import credit.entities.ClientRequest;
import org.json.simple.JSONObject;

public class JsonHelper {
    public static JSONObject toJSON(Client client) {
        JSONObject object = new JSONObject();
        
        object.put("age", client.getAge());
        object.put("forename", client.getForename());
        object.put("patronym", client.getPatronym());
        object.put("mail", client.getMail());
        object.put("number", client.getNumber());
        object.put("surname", client.getSurname());
        object.put("income", client.getIncome());
        
        return object;
    }
    
    public static JSONObject toJSON(ClientRequest request) {
        JSONObject object = new JSONObject();
        
        object.put("client", toJSON(request.getClient()));
        object.put("amount", request.getAmount());
        object.put("terminDays", request.getTermInDays());
        object.put("approved", request.isApproved());
        
        return object;
    }
}
