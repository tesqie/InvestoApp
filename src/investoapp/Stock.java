package investoapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This is a class representing information about an individual Stock
 *
 * @author Abdul Tesqie
 */
public final class Stock implements ApiConnection, Serializable {

    private String ticker;
    private String name;
    private String business_address;
    private String company_url;
    private String hq_country;
    private String sector;
    private String industry_category;
    private String long_description;
    private ArrayList<StockNews> news;
    private StockPrice price;

    /**
     * Create an instance of the Stock and fetch information live from the API
     *
     * @param ticker Stock ticker to be fetched
     * @param fetch
     */
    public Stock(String ticker, boolean fetch) {

        StringBuilder sb = new StringBuilder();
        sb.append(ticker.toUpperCase());
        this.ticker = sb.toString();

        if (fetch) {

            connectAndFetch(sb.toString());
            StockPrice stockprice = new StockPrice();
            stockprice.connectAndFetch(sb.toString());

            price = stockprice;
        }

    }

    public StockPrice getPrice() {
        return price;
    }

    public void setPrice(StockPrice price) {
        this.price = price;
    }

   

    /**
     * Connects to an external API to fetch a JSON formatted String that
     * contains a stocks information.
     *
     * @param symbol passing in the ticker that we'll be fetching
     */
    @Override
    public void connectAndFetch(String symbol) {

        try {

            URL url = new URL("https://api.intrinio.com/companies?identifier=" + symbol);
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
     * Parsing the JSON string and setting each stock info accordingly
     *
     * @param jsonStock the JSON string to parse
     */
    @Override
    public void jsonParser(String jsonStock) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonStock);

            ticker = jsonObject.get("ticker").toString();
            name = jsonObject.get("name").toString();
            business_address = jsonObject.get("business_address").toString();
            company_url = jsonObject.get("company_url").toString();
           
           
            hq_country = jsonObject.get("hq_country").toString();
            sector = jsonObject.get("sector").toString();
            industry_category = jsonObject.get("industry_category").toString();
            industry_category = jsonObject.get("industry_group").toString();
            
 
            long_description = jsonObject.get("long_description").toString();
        } catch (ParseException ex) {
            System.out.println(ex.toString());
        } catch (IndexOutOfBoundsException ignoreMe) {
            System.out.println(ignoreMe.toString());
        }catch (NullPointerException ignoreMe){
            
        }

    }

    /**
     * A slightly different URL to fetch stock news information in a JSON
     * formatted string
     *
     *
     */
    public void connectAndFetchNews() {

        try {

            URL urlFetch = new URL("https://api.intrinio.com/news?identifier=" + ticker);
            URLConnection conn = urlFetch.openConnection();
            String userpass = API_USERNAME + ":" + API_PASSWORD;
            String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
            conn.setRequestProperty("Authorization", basicAuth);

            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            conn.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    newsParse(inputLine);
                }
            }
        } catch (MalformedURLException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } catch (IndexOutOfBoundsException ignoreMe) {
        }
    }

    /**
     * This method is used to parse the JSON string into multiple StockNews
     * objects
     *
     * @param jsonStock
     */
    public void newsParse(String jsonStock) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonStock);

            JSONArray jsonArray = (JSONArray) jsonObject.get("data");
            ArrayList<StockNews> newsList = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                JSONObject newsObj = (JSONObject) jsonArray.get(i);
                String newsTitle = newsObj.get("title").toString();
                String newsDate = newsObj.get("publication_date").toString();
                String newsUrl = newsObj.get("url").toString();
                newsList.add(new StockNews(newsTitle, newsUrl, newsDate));
            }
            news = newsList;

        } catch (ParseException ex) {
            System.out.println(ex.toString());
        } catch (IndexOutOfBoundsException ignoreMe) {
        }
    }

    

    public String getLong_description() {
        return long_description;
    }

    public ArrayList<StockNews> getNews() {
        return news;
    }

    public void setNews(ArrayList<StockNews> news) {
        this.news = news;
    }

    public String getTicker() {
        return ticker;
    }

    public String getName() {
        return name;
    }

    public String getBusiness_address() {
        return business_address;
    }

    public String getCompany_url() {
        return company_url;
    }


    public String getHq_country() {
        return hq_country;
    }

    public String getSector() {
        return sector;
    }

    public String getIndustry_category() {
        return industry_category;
    }

    @Override
    public String toString() {
        return ticker;
    }

}
