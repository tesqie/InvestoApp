package investoapp;



/**
 *
 * @author tesqie
 */
public interface ApiConnection {    
    final String API_USERNAME = "4acaa831f923cf26dbc2941c4e77ed2f";
    final String API_PASSWORD = "7af8f3da71dbf7439bdc1f0408918dcb";
   // final String apiUrl = "https://api.intrinio.com";
    
    public void connectAndFetch(String symbol);
    public void jsonParser(String jsonStock);
    
    
}
