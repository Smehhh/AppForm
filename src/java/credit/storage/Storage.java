package credit.storage;

import credit.entities.ClientRequest;


public interface Storage { 
    void setData(ClientRequest request);
    ClientRequest getData();
}
