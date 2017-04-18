package investoapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representing the User's portfolio of stocks.
 *
 * @author Abdul Tesqie
 */
public class Portfolio implements Serializable {

    private ArrayList<Stock> stocks;

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public void setStock(ArrayList<Stock> stock) {
        this.stocks = stock;
    }

}
