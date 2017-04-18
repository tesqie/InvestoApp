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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class represents the stock screener that will return stocks based on 
 * the user's choice.
 *
 * @author Abdul Tesqie
 */
public class Screener implements ApiConnection {

    
    private final String screenerUrl;
    private final ArrayList<Stock> stocksFromScreener = new ArrayList<>();

    public Screener(String screenerUrl) {
        this.screenerUrl = screenerUrl;

        connectAndFetch(screenerUrl);
    }

    public ArrayList<Stock> getStocksFromScreener() {
        return stocksFromScreener;
    }
    
    /**
     * Connects to the API using the URL with the user's requested filter.
     * @param screenerUrl The user's filter for stock to return.
     */

    @Override
    public void connectAndFetch(String screenerUrl) {
        try {

            URL url = new URL("https://api.intrinio.com/securities/search?conditions=" + screenerUrl);
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
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    /**
     * Parses the JSON string into the corresponding variables.
     * @param jsonStock The JSON string fetched by the connect and fetch method
     */

    @Override
    public void jsonParser(String jsonStock) {
        try {

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonStock);
            int total_pages = Integer.parseInt(jsonObject.get("total_pages").toString());
            int current_page = Integer.parseInt(jsonObject.get("current_page").toString());
            JSONArray jsonArray = (JSONArray) jsonObject.get("data");
            //ArrayList<JSONObject> jsonObjArray = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                //
                JSONObject stockFromArray = (JSONObject) jsonArray.get(i);
                stocksFromScreener.add(
                        new Stock(stockFromArray.get("ticker").toString(), false));
            }

            if (total_pages > 1 && current_page < total_pages) {
                connectAndFetch(screenerUrl + "&page_number=" + ++current_page);
            }

        } catch (ParseException ex) {
            System.out.println(ex.toString());
        }
    }

}
