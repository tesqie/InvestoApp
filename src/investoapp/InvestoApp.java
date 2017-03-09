package investoapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tesqie
 */ 
public class InvestoApp {

    public static void main(String[] args) throws MalformedURLException, IOException {

        ArrayList<Securities> securities = new ArrayList<>();
        while (true) {
            Scanner input = new Scanner(System.in);

            System.out.println("Enter 1 to continue or 0 to exit");
            int option = Integer.parseInt(input.nextLine());

            if (option == 1) {
                System.out.println("Enter a tag");
                String tag = input.nextLine();
                System.out.println("Enter an operator");
                String operator = input.nextLine();
                System.out.println("Enter a value");
                Double value = Double.parseDouble(input.nextLine());

                securities.add(new Securities(tag, operator, value));

            } else {
                break;
            }
        }
        String condition = "";
        for (int i = 0; i < securities.size(); i++) {
            condition += securities.get(i).getTag() + "~" + securities.get(i).getOperator() + "~" + securities.get(i).getValue() + ",";
        }
        System.out.println(condition);

        URL url = new URL("https://api.intrinio.com/securities/search?conditions=" + condition);

        URLConnection conn = url.openConnection();

        String username = "4acaa831f923cf26dbc2941c4e77ed2f";
        String password = "7af8f3da71dbf7439bdc1f0408918dcb";

        String userpass = username + ":" + password;
        String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());

        conn.setRequestProperty("Authorization", basicAuth);

        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.print(inputLine);
            }
        }
    }

}

