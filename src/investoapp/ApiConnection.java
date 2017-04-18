package investoapp;

/**
 * Interface representing the connection to the Api
 *
 * @author Abdul Tesqie
 */

public interface ApiConnection {

    final String API_USERNAME = "4acaa831f923cf26dbc2941c4e77ed2f";
    final String API_PASSWORD = "7af8f3da71dbf7439bdc1f0408918dcb";
    // final String apiUrl = "https://api.intrinio.com";

    /**
     * Connect to the API and fetch JSON data back
     *
     * @param symbol URL extention used to fetch JSON data back from the server
     */
    public void connectAndFetch(String symbol);

    /**
     * To parse the JSON data fetched by the connectAndFetch method
     *
     * @param jsonStock A string representation of the JSON to be parsed
     */
    public void jsonParser(String jsonStock);

}
