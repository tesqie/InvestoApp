package investoapp;

import static investoapp.ApiConnection.API_PASSWORD;
import static investoapp.ApiConnection.API_USERNAME;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author tesqie
 */
public class Screener implements ApiConnection{
    private final String Tag;
    private final String operator;
    private final Double value;

    public Screener(String Tag, String operator, Double value) {
        this.Tag = Tag;
        this.operator = operator;
        this.value = value;
    }

    public String getTag() {
        return Tag;
    }

    public String getOperator() {
        return operator;
    }

    public Double getValue() {
        return value;
    }
 

    @Override
    public void connectAndFetch(String symbol) {
        try {

            URL url = new URL("https://api.intrinio.com/securities/search?conditions=" + symbol);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
