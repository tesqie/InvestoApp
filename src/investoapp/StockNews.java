package investoapp;

;

import java.io.Serializable;


/**
 *
 * @author tesqie
 */
public class StockNews implements Serializable{
    private final String title;
    private final String url;
    private final String date;

    
    
    

    public StockNews(String title, String url, String date, String summary) {
        this.title = title;
        this.url = url;
        this.date = date;
    }
    

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }


    @Override
    public String toString() {
        String formatNews = "%s\n%s";
        return String.format(formatNews, title,date);
    }

    
}
