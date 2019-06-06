/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credit.storage;

import credit.entities.ClientRequest;
import javax.servlet.http.HttpSession;


public class SessionStorage implements Storage{

    private HttpSession session;

    public SessionStorage(HttpSession session) {
        this.session = session;
    }
    
    @Override
    public void setData(ClientRequest request) {
        session.setAttribute("clientRequest", request);
    }

    @Override
    public ClientRequest getData() {
        return (ClientRequest)session.getAttribute("clientRequest");
    }
}
