package investoapp;

import static investoapp.ApiConnection.API_PASSWORD;
import static investoapp.ApiConnection.API_USERNAME;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tesqie
 */
public class Price implements ApiConnection {
    private String date;
    private double open;
    private double high;
    private double low;
    private double close;
    
    @Override
    public void connectAndFetch(String symbol) {
        try {
            DateFormat df = new SimpleDateFormat("20yy-MM-10");
            Date dateobj = new Date();  

            URL url = new URL("https://api.intrinio.com/prices?identifier=" + symbol +"&"+dateobj);
            URLConnection conn = url.openConnection();
            String userpass = API_USERNAME + ":" + API_PASSWORD;
            String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
            conn.setRequestProperty("Authorization", basicAuth);

            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            conn.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    jsonParser(inputLine);
                }
            }        } catch (MalformedURLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void jsonParser(String jsonStock) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonStock);
            
            
            
            setDate(jsonObject.get("date").toString());
            setOpen(Double.parseDouble(jsonObject.get("open").toString()));
            setHigh(Double.parseDouble(jsonObject.get("high").toString()));
            setLow(Double.parseDouble(jsonObject.get("low").toString()));
            setClose(Double.parseDouble(jsonObject.get("close").toString()));
            
            
           
        } catch (ParseException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDate() {
        return date;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setClose(double close) {
        this.close = close;
    }
    
    
}
