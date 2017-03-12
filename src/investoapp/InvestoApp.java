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
import java.util.HashSet;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
//import org.json.simple.parser.ParseException;


/**
 *
 * @author tesqie
 */
public class InvestoApp extends Application implements Serializable {

    TextField txtUsername;
    TextField txtPassword;
    TextField txtEmail;
        
    public static void main(String[] args) throws MalformedURLException, IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
         BorderPane main = new BorderPane();
         main.setPadding(new Insets(10));     
         
      
         
        Scene scene = new Scene(main, 500, 500);
        scene.setFill(Color.ANTIQUEWHITE);
        
//       setLogin(main); 
//       setCreateAccount(main);
         setHome(main);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Investo Application");
        primaryStage.show();
            
    }
    
    public void setLogin(BorderPane main){
       Circle titleHolder = new Circle();
       titleHolder.setRadius(75);
       titleHolder.setStroke(Color.RED);
       titleHolder.setFill(Color.WHITE);
       titleHolder.setStrokeWidth(10);
       
       Label appTitle = new Label("Investo");
       StackPane holder = new StackPane(titleHolder, appTitle);        
       main.setTop(holder);       
   
        VBox centerPane = new VBox(10);
        centerPane.setAlignment(Pos.CENTER);
        
        GridPane inputPane = new GridPane();
        inputPane.setAlignment(Pos.CENTER);
        inputPane.setHgap(10);
        inputPane.setVgap(10);
        
 
        txtUsername = new TextField();
        txtUsername.setPrefColumnCount(10);

     
        txtPassword = new TextField();
        txtPassword.setPrefColumnCount(10);
        
        inputPane.addRow(0, new Label("Username"), txtUsername);
        inputPane.addRow(1, new Label("Password"), txtPassword);
        centerPane.getChildren().add(inputPane);
        
        Button login = new Button ("Login");
        Button password = new Button ("Password");
        
        HBox buttonPane = new HBox(10, login, password);
        buttonPane.setAlignment(Pos.CENTER);
        
        centerPane.getChildren().add(buttonPane);
        
        
        
        main.setCenter(centerPane);
        
    
       Button createAccBtn = new Button();
       createAccBtn.setText("Create Account");
       createAccBtn.setTextFill(Color.WHITE);
       createAccBtn.getStyleClass().add("button");
       
       BorderPane.setAlignment(createAccBtn, Pos.BOTTOM_RIGHT);
       main.setBottom(createAccBtn); 
    }
    
    public void setCreateAccount(BorderPane main){
       Circle titleHolder = new Circle();
       titleHolder.setRadius(75);
       titleHolder.setStroke(Color.RED);
       titleHolder.setFill(Color.WHITE);
       titleHolder.setStrokeWidth(10);
       
       Label appTitle = new Label("Create \n Account");
       StackPane holder = new StackPane(titleHolder, appTitle);        
       main.setTop(holder);       
   
        VBox centerPane = new VBox(10);
        centerPane.setAlignment(Pos.CENTER);
        
        GridPane inputPane = new GridPane();
        inputPane.setAlignment(Pos.CENTER);
        inputPane.setHgap(10);
        inputPane.setVgap(10);
        
 
        txtUsername = new TextField();
        txtUsername.setPrefColumnCount(10);

     
        txtPassword = new TextField();
        txtPassword.setPrefColumnCount(10);
        
        txtEmail = new TextField();
        txtEmail.setPrefColumnCount(10);
        
        inputPane.addRow(0, new Label("Username"), txtUsername);
        inputPane.addRow(1, new Label("Password"), txtPassword);
        inputPane.addRow(2, new Label("Email"), txtEmail);
        centerPane.getChildren().add(inputPane);
        
        Button login = new Button ("Create");
        Button password = new Button ("Cancel");
        
        HBox buttonPane = new HBox(10, login, password);
        buttonPane.setAlignment(Pos.CENTER);
        
        centerPane.getChildren().add(buttonPane);
        
        
        
        main.setCenter(centerPane);
        

    }
    
    public void setHome(BorderPane main){
        Label heading = new Label("Investo");
        
        VBox centerPane = new VBox(10);
        centerPane.setAlignment(Pos.CENTER);
        
        GridPane selection = new GridPane();
        selection.setAlignment(Pos.CENTER);
        
        
        Rectangle search = new Rectangle();
        search.setHeight(30);
        search.setWidth(30);
        search.setFill(Color.GREEN);
        
        Rectangle portfolio = new Rectangle();
        portfolio.setHeight(30);
        portfolio.setWidth(30);
        portfolio.setFill(Color.BLUE);
        
        Rectangle recent = new Rectangle();
        recent.setHeight(30);
        recent.setWidth(30);
        recent.setFill(Color.YELLOW);
        
        Rectangle settings = new Rectangle();
        settings.setHeight(30);
        settings.setWidth(30);
        settings.setFill(Color.RED);
        
        
        
    
        
        
        
        
        
        
        
    }
    
    
    
//        Stock appleStock = new Stock("AAPL");
//        appleStock.connectAndFetch(appleStock.getTicker());

        //ArrayList<Account> accounts = new ArrayList<>();
        //Account account = new Account();
        //load(accounts);
        //System.out.println(accounts.get(0).getUsername());
        //System.out.println(accounts);
        //System.out.println(appleStock.getIndustry_category());
//        Scanner input = new Scanner(System.in);
//        System.out.println("New account = 1");
        //  int selection = Integer.parseInt(input.nextLine());
//        while (selection == 1){
        //newAccount();
        

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
//    }

    // public Account newAccount() throws IOException  {
//        Scanner input = new Scanner(System.in);
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
//        return account;
//        //System.out.println(account);
//        //save(accounts);
    // }
//    public void save(ArrayList<Account> accounts) throws FileNotFoundException, IOException {
//        FileOutputStream fos = new FileOutputStream("creds.ser");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(accounts);
//        oos.close();
//        fos.close();
//    }
//
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
