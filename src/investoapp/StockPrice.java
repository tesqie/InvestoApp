/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investoapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tesqie
 */
public class StockPrice implements ApiConnection,Serializable{
    private double close;
    private double open;

    public StockPrice() {
    }

    public double getClose() {
        return close;
    }
    public double getOpen() {
        return open;
    }
    
    

    @Override
    public void connectAndFetch(String symbol) {
        try {

            URL urlFetch = new URL("https://api.intrinio.com/prices?identifier=" + symbol);
            URLConnection conn = urlFetch.openConnection();
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

    @Override
    public void jsonParser(String jsonStock) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonStock);
            JSONArray jsonArray = (JSONArray) jsonObject.get("data");
            JSONObject priceObject = (JSONObject)jsonArray.get(0);

            close = Double.parseDouble(priceObject.get("close").toString());
            open = Double.parseDouble(priceObject.get("open").toString());
           
        } catch (ParseException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public String toString() {
        return Double.toString(close);
    }

    
}
