package investoapp;

import static investoapp.ApiConnection.API_PASSWORD;
import static investoapp.ApiConnection.API_USERNAME;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tesqie
 */
public class Screener implements ApiConnection {
/*
    Screener conditions:
    EPS
    P/E
    sales
    dividend rate
    dividend payout ratio
    dividend yield 5 year average
    share price
    change %
    country
    sector
    group
    avg Volume
    Shares
    Div/yield
    return on equity
    return on assets
    3 year annual rev growth
    3 year annual income growth
    3 year annual dividend growth 
    5 year annual revenue growth
    5 year annaul income growth
    5 year annual dividend growth
    
    */
    private String jsonTicker;
    private String jsonValue;
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
            }
        } catch (MalformedURLException ex) {
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
            JSONArray jsonArray = (JSONArray) jsonObject.get("data");
            ArrayList<JSONObject> jsonObjArray = new ArrayList<>();
            for (int i = 0;i <jsonArray.size();i++){
                jsonObjArray.add((JSONObject)jsonArray.get(i));
            }
            
            System.out.println(jsonObjArray.get(6));

        } catch (ParseException ex) {
            System.out.println(ex.toString());
        }
    }

}
