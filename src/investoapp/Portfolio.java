package investoapp;

import java.util.ArrayList;

/**
 *
 * @author tesqie
 */
public class Portfolio {
    private ArrayList<Stock> stock;

    public Portfolio(ArrayList<Stock> stock) {
        this.stock = stock;
    }

    public ArrayList<Stock> getStock() {
        return stock;
    }

    public void setStock(ArrayList<Stock> stock) {
        this.stock = stock;
    }    
}
