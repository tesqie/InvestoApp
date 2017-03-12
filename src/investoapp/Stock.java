package investoapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tesqie
 */
public class Stock implements ApiConnection {

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
    //private String jsonStock; 
    
    private Price price;

    public Stock(String ticker) {
        this.ticker = ticker;
    }

    public Price getPrice() {
        return price;
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
            }        } catch (MalformedURLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return "error";
    }
    
    @Override
    public void jsonParser(String jsonStock){
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonStock);
            
            
            
            setName(jsonObject.get("name").toString());
            setBusiness_address(jsonObject.get("business_address").toString());
            setCompany_url(jsonObject.get("company_url").toString());
            setCeo(jsonObject.get("ceo").toString());
            setHq_state(jsonObject.get("hq_state").toString());
            setHq_country(jsonObject.get("hq_country").toString());
            setSector(jsonObject.get("sector").toString());
            setIndustry_category(jsonObject.get("industry_category").toString());
            setIndustry_group(jsonObject.get("industry_group").toString());
        } catch (ParseException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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

    public void setName(String name) {
        this.name = name;
    }

    public void setBusiness_address(String business_address) {
        this.business_address = business_address;
    }

    public void setCompany_url(String company_url) {
        this.company_url = company_url;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public void setHq_state(String hq_state) {
        this.hq_state = hq_state;
    }

    public void setHq_country(String hq_country) {
        this.hq_country = hq_country;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setIndustry_category(String industry_category) {
        this.industry_category = industry_category;
    }

    public void setIndustry_group(String industry_group) {
        this.industry_group = industry_group;
    }
}
