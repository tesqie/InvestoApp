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
 *
 * @author tesqie
 */
public final class Stock implements ApiConnection, Serializable {

    private final String ticker;
    private String name;
    private String business_address;
    private String company_url;
    private String ceo;
    private String hq_state;
    private String hq_country;
    private String sector;
    private String industry_category;
    private String industry_group;
    private String stock_exchange;
    private String short_description;
    private String long_description;
    private ArrayList<StockNews> news;
    private StockPrice price;
     

    

    public Stock(String ticker) {
        StringBuilder sb = new StringBuilder();
        sb.append(ticker.toUpperCase());
        
        connectAndFetch(sb.toString());
        this.ticker = sb.toString();
        
        
        
        StockPrice stockprice= new StockPrice();
        stockprice.connectAndFetch(this.ticker);
        
        price = stockprice;
        
        

        
    }

    public StockPrice getPrice() {
        return price;
    }

    public void setPrice(StockPrice price) {
        this.price = price;
    }
    
    public String getStock_exchange() {
        return stock_exchange;
    }

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
        }
    }

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
                String newsSummary = newsObj.get("summary").toString();
                newsList.add(new StockNews(newsTitle, newsUrl, newsDate, newsSummary));
            }
            news = newsList;

        } catch (ParseException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public void jsonParser(String jsonStock) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonStock);

            name = jsonObject.get("name").toString();
            business_address = jsonObject.get("business_address").toString();
            company_url = jsonObject.get("company_url").toString();
            ceo = jsonObject.get("ceo").toString();
            hq_state = jsonObject.get("hq_state").toString();
            hq_country = jsonObject.get("hq_country").toString();
            sector = jsonObject.get("sector").toString();
            industry_category = jsonObject.get("industry_category").toString();
            industry_category = jsonObject.get("industry_group").toString();
            stock_exchange = jsonObject.get("stock_exchange").toString();
            short_description = jsonObject.get("short_description").toString();
            long_description = jsonObject.get("long_description").toString();
        } catch (ParseException ex) {
            System.out.println(ex.toString());
        }

    }

    public String getShort_description() {
        return short_description;
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

    public String getCeo() {
        return ceo;
    }

    public String getHq_state() {
        return hq_state;
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

    public String getIndustry_group() {
        return industry_group;
    }

}
