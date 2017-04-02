package investoapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author tesqie
 */
public class Portfolio implements Serializable {
    private ArrayList<Stock> stock;

    public Portfolio() {
        
    }

    public ArrayList<Stock> getStock() {
        return stock;
    }

    public void setStock(ArrayList<Stock> stock) {
        this.stock = stock;
    }    
    public void addStock(Stock stock) {
        this.stock.add(stock);
    }    
    
}
