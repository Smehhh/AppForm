package credit.util;

import credit.entities.Client;
import credit.entities.ClientRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class MapHelper {
    
    public static List<String> clientFields(boolean optionalToo) {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(new String[] {"age", "income","mail", "number", "surname", "forename", "patronym"}));
        return list;
    }
    
    public static List<String> clientRequestFields(boolean optionalToo) {
        List<String> list = clientFields(optionalToo);
        list.addAll(Arrays.asList(new String[] {"amount", "termInDays"}));
        if(optionalToo) {
            list.add("approved");
        }
        return list;
    }
    
    public static Client getClient(Map<String, String> map) {
               
        if(clientFields(false).stream().anyMatch(key -> !map.containsKey(key)))
            return null;
        
        Client client = new Client();
        
        try {
            client.setAge(Integer.parseInt(map.get("age")));
            client.setIncome(Integer.parseInt(map.get("income")));
            client.setSurname(Objects.requireNonNull(map.get("mail")));
            client.setSurname(Objects.requireNonNull(map.get("number")));
            client.setSurname(Objects.requireNonNull(map.get("surname")));
            client.setForename(Objects.requireNonNull(map.get("forename")));
            client.setPatronym(Objects.requireNonNull(map.get("patronym")));
        } catch(Exception e) {
            return null;
        }
        
        return client;
    }

    public static ClientRequest getClientRequest(Map<String, String> map) {
     
        if(clientRequestFields(false).stream().anyMatch(key -> !map.containsKey(key)))
            return null;
        
        ClientRequest request = new ClientRequest();
        
        request.setClient(getClient(map));
        
        if(request.getClient() == null)
            return null;
        
        try {
            request.setAmount(Integer.parseInt(map.get("amount")));
        
            if(map.get("approved") != null) {
                request.setApproved(Boolean.parseBoolean(map.get("approved")));
            } else {
                request.setApproved(false);
            }
            
            request.setTermInDays(Integer.parseInt(map.get("termInDays")));
        } catch(Exception e) {
            return null;
        }
        
        return request;
    }
    
    public static Client getClient(Cookie[] cookies) {
        return getClient(getMap(cookies));
    }
    
    public static ClientRequest getClientRequest(Cookie[] cookies) {
        return getClientRequest(getMap(cookies));
    }
    
    public static HashMap<String, String> getMap(Cookie[] cookies) {
        return Arrays.stream(cookies).collect(Collectors.toMap(c -> c.getName(), c -> decode(c.getValue()), 
                (a,b) -> a +"," + b, HashMap<String, String>::new));
    }
    
    public static HashMap<String, String> getMap(Client client) {
        HashMap<String, String> map = new HashMap<>();
        
        map.put("age", "" + client.getAge());
        map.put("income", "" + client.getIncome());
        map.put("mail", client.getMail());
        map.put("number", client.getNumber());
        map.put("surname", client.getSurname());
        map.put("forename", client.getForename());
        map.put("patronym", client.getPatronym());
        
        return map;
    }
    
    public static HashMap<String, String> getMap(ClientRequest request) {
        HashMap<String, String> map = getMap(request.getClient());
        
        map.put("amount", "" + request.getAmount());
        map.put("approved", "" + request.isApproved());
        map.put("termInDays", "" + request.getTermInDays());
        
        return map;
    }
    
    public static Cookie[] getCookies(Map<String, String> map) {
        return map.entrySet().stream().map(e -> new Cookie(e.getKey(), encode(e.getValue()))).toArray(Cookie[]::new);
    }
     
    public static Cookie[] getCookies(Client client) {
        return getCookies(getMap(client));
    }
    
    public static Cookie[] getCookies(ClientRequest request) {
        return getCookies(getMap(request));
    }
    
    public static String encode(String text) {
        try {
            return URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static String decode(String text) {
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static Client getClient(HttpServletRequest request){
        return getClient(convert(request.getParameterMap()));
    }
    
    public static ClientRequest getClientRequest(HttpServletRequest request){
        return getClientRequest(convert(request.getParameterMap()));
    }
    
    public static Cookie[] erasedAllCookies() {
        return erasedAllCookies(clientRequestFields(true));
    }
    
    public static Cookie[] erasedAllCookies(Collection<String> collection) {
        return collection.stream().map(n -> {
            Cookie cookie = new Cookie(n, "");
            cookie.setMaxAge(0);
            return cookie;
        }).toArray(Cookie[]::new);
    }
    
    public static Map<String, String> convert(Map<String, String[]> map) {
        HashMap<String, String> result = new HashMap<>();
        for (Iterator<Map.Entry<String, String[]>> it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String,String[]> entry = it.next();
            result.put(entry.getKey(), String.join(",", entry.getValue()));
        }
        return result;
    }
}
