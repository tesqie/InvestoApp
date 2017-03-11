package investoapp;


import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tesqie
 */
public class Screener implements ApiConnection{
    private final String Tag;
    private final String operator;
    private final Double value;

    public Screener(String Tag, String operator, Double value) {
        this.Tag = Tag;
        this.operator = operator;
        this.value = value;
    }

    public String getTag() {
        return Tag;
    }

    public String getOperator() {
        return operator;
    }

    public Double getValue() {
        return value;
    }

//    @Override
//    public String connectAndFetch() {
//         URL url = new URL("https://api.intrinio.com/securities/search?conditions=" + condition);
//
//        URLConnection conn = url.openConnection();
//
//        String userpass = apiUsername + ":" + apiPassword;
//        return String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
//
//        conn.setRequestProperty("Authorization", basicAuth);
//
//        try (
//                BufferedReader in = new BufferedReader(new InputStreamReader(
//                        conn.getInputStream()))) {
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                System.out.print(inputLine);
//            }
//        }
//    }
    
//    ArrayList<Stocks> securities = new ArrayList<>();
//        while (true) {
//            Scanner input = new Scanner(System.in);
//
//            System.out.println("Enter 1 to continue or 0 to exit");
//            int option = Integer.parseInt(input.nextLine());
//
//            if (option == 1) {
//                System.out.println("Enter a tag");
//                String tag = input.nextLine();
//                System.out.println("Enter an operator");
//                String operator = input.nextLine();
//                System.out.println("Enter a value");
//                Double value = Double.parseDouble(input.nextLine());
//
//                securities.add(new Stocks(tag, operator, value));
//
//            } else {
//                break;
//            }
//        }
//        String condition = "";
//        for (int i = 0; i < securities.size(); i++) {
//            condition += securities.get(i).getTag() + "~" + securities.get(i).getOperator() + "~" + securities.get(i).getValue() + ",";
//        }
//        System.out.println(condition);

    @Override
    public void connectAndFetch(String symbol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void jsonParser(String jsonStock) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
