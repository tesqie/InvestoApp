package investoapp;


import java.io.IOException;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tesqie
 */ 
public class InvestoApp {

    public static void main(String[] args) throws MalformedURLException, IOException, ParseException {

        Stock appleStock = new Stock("AAPL");
        appleStock.connectAndFetch(appleStock.getTicker());
       
        
        System.out.println(appleStock.getIndustry_category());
        
        
        
    }

}

