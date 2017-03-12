package investoapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tesqie
 */
public class InvestoApp extends Application implements Serializable {

    public static void main(String[] args) throws MalformedURLException, IOException, ParseException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
//        Stock appleStock = new Stock("AAPL");
//        appleStock.connectAndFetch(appleStock.getTicker());
//        
//        Screener screen = new Screener("netincome","gt",90000.0);
//        
//        String condition = "netincome~gt~90000.0";
//        screen.connectAndFetch(condition);
        ArrayList<Account> accounts = new ArrayList<>();
        
        
        

        //ArrayList<Account> accounts = new ArrayList<>();
        //Account account = new Account();
        //load(accounts);
        //System.out.println(accounts.get(0).getUsername());
        //System.out.println(accounts);
        //System.out.println(appleStock.getIndustry_category());
//        Scanner input = new Scanner(System.in);
//        System.out.println("New account = 1");
//        //  int selection = Integer.parseInt(input.nextLine());
////        while (selection == 1){
//        //newAccount();
//        
//
//        System.out.println("Enter Username");
//        String username = input.nextLine();
//        System.out.println("Enter Password");
//        String password = input.nextLine();
//        SHA256InJava sj = new SHA256InJava();
//        String passwordHash = sj.getSHA256Hash(password);
//        System.out.println("Enter Email");
//        String email = input.nextLine();
//
//        //System.out.println(accounts.get(0).getUsername());
//        Account account = new Account(username, passwordHash, email);
        //return account;
        //System.out.println(account);
        //save(accounts);
//            System.out.println("New account = 1");
//            selection = Integer.parseInt(input.nextLine());
//            load(accounts);
//        }

        //input.close();
//        Properties loadProps = new Properties();
//        InputStream is = new FileInputStream("users.properties");
//        loadProps.load(is);
//
//        int users = Integer.parseInt(loadProps.get("#users").toString());
//
//        System.out.println(users);
//
//        Properties saveAccount = new Properties();
//
//        String username1 = account.getUsername();
//        String password1 = account.getPassword();
//        saveAccount.setProperty(users + "user.", username1 + "=" + password1);
//        File creds = new File("cred.properties");
//        OutputStream out1 = new FileOutputStream(creds, true);
//        saveAccount.store(out1, "User creds");
//
//        users += 1;
//        String userss = String.format("" + users);
//
//        Properties props = new Properties();
//        props.setProperty("#users", userss);
//        File userNumber = new File("users.properties");
//        OutputStream out = new FileOutputStream(userNumber);
//        props.store(out, "#users");

        //System.out.println(hash);
//        Properties props = new Properties();
//        props.setProperty("username", accounts.get(0).getUsername());
//        props.setProperty("Password", accounts.get(0).getPassword());
//        File creds = new File("cred.properties");
//        OutputStream out = new FileOutputStream(creds);
//        props.store(out, "User creds");
//
//        //InputStream is = null;
//        InputStream is = new FileInputStream(creds);
//        props.load(is);
//        System.out.println(props.getProperty("Username"));
//        System.out.println(props.getProperty("Password"));
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
    }

     public void newAccount(ArrayList<Account> accounts) throws IOException  {
        Scanner input = new Scanner(System.in);
        //ArrayList<Account> accounts = new ArrayList<>();
        System.out.println("Enter Username");
        String uname = input.nextLine();
        System.out.println("Enter Password");
        String pass = input.nextLine();
        SHA256InJava sj = new SHA256InJava();
        String passwordHash = sj.getSHA256Hash(pass);
        System.out.println("Enter Email");
        String inputEmail = input.nextLine();
        
        accounts.add(new Account(uname,passwordHash,inputEmail));
        
     }
//    public void save(ArrayList<Account> accounts) throws FileNotFoundException, IOException {
//        FileOutputStream fos = new FileOutputStream("creds.ser");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(accounts);
//        oos.close();
//        fos.close();
//    }

//    public void load() throws FileNotFoundException, IOException, ClassNotFoundException {
//        try (FileInputStream fis = new FileInputStream("creds.ser"); ObjectInputStream ois = new ObjectInputStream(fis)) {
//            ArrayList<Account> accounts2 = (ArrayList<Account>) ois.readObject();
//
//        } catch (IOException ioe) {
//        } catch (ClassNotFoundException c) {
//            System.out.println("Class not found");
//        }
//        //  System.out.println(accounts);
//        // return accounts;
//    }
    
    

    public void login(String username, String password) {

    }

}
