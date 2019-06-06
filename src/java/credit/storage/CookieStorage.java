/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credit.storage;

import credit.entities.ClientRequest;
import credit.util.MapHelper;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieStorage implements Storage {

    private HttpServletRequest request;
    private HttpServletResponse responce;

    public CookieStorage(HttpServletRequest request, HttpServletResponse responce) {
        this.request = request;
        this.responce = responce;
    }
    
    @Override
    public void setData(ClientRequest request) {
        if(request != null) {
            Arrays.stream(MapHelper.getCookies(request)).forEach(responce::addCookie);
        } else {
            Arrays.stream(MapHelper.erasedAllCookies()).forEach(responce::addCookie);
        }
    }

    @Override
    public ClientRequest getData() {
        return MapHelper.getClientRequest(request.getCookies());
    }
    
}
