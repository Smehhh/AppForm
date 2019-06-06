package credit.analyst;

import credit.entities.ClientRequest;
import credit.storage.Storage;
import credit.util.ApprovalHelper;
import credit.util.JsonHelper;
import credit.util.MapHelper;
import credit.util.ValidationHelper;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;


public class Analyst
{
    private ClientRequest clientRequest; 
    
    private Storage storage;

    public Analyst(Storage storage) {
        this.storage = storage;
    }
    
    public Analyst parseRequest(HttpServletRequest request) {
        clientRequest = MapHelper.getClientRequest(request);
        if(clientRequest != null)
            ApprovalHelper.checkClientRequest(clientRequest);
        if(storage != null)
            storage.setData(clientRequest);
        return this;
    }
    
    public Analyst loadFromStorage() {
        if(storage != null)
            clientRequest = storage.getData();
        else
            clientRequest = null;
        return this;
    }

    public void setClientRequest(ClientRequest clientRequest) {
        this.clientRequest = clientRequest;
    }
    
    public ClientRequest getClientRequest() {
        return clientRequest;
    }
    
    public String[] validate() {
        return clientRequest != null ? ValidationHelper.validate(clientRequest) : new String[0];
    }
    
    public JSONObject toJson() {
        return clientRequest != null ? JsonHelper.toJSON(clientRequest) : null;
    }
    
}
