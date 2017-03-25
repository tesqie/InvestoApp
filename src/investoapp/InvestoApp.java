package investoapp;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

/**
 *
 * @author tesqie
 *
 */
public class InvestoApp extends Application {

    TextField txtUsername;
    TextField txtPassword;
    TextField txtEmail;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane main = new BorderPane();
        main.setPadding(new Insets(10));

        Scene scene = new Scene(main, 1000, 1000);
        scene.getStylesheets().add("investoapp/InvestoStyle.css");

        setLogin(main);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Investo Application");
        primaryStage.show();

    }

    private void createAccount(String uName, String uPass, String uEmail) {
        SHA256InJava sj = new SHA256InJava();
        String passwordHash = sj.getSHA256Hash(uPass);
        Account newAccount = new Account(uName, passwordHash, uEmail);
        newAccount.save(newAccount);

    }

    public void setLogin(BorderPane main) {
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

        txtPassword = new PasswordField();
        txtPassword.setPrefColumnCount(10);

        inputPane.addRow(0, new Label("Username"), txtUsername);
        inputPane.addRow(1, new Label("Password"), txtPassword);
        centerPane.getChildren().add(inputPane);

        Button login = new Button("Login");
        Button password = new Button("Password");

        login.setOnAction(e -> {
            Account account = new Account();
            ArrayList<Account> accounts = account.load();

            SHA256InJava sj = new SHA256InJava();
            String passwordHash = sj.getSHA256Hash(txtPassword.getText());

//            for (Account users : accounts) {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getUsername().equals(txtUsername.getText()) && accounts.get(i).getPassword().equals(passwordHash)) {
                    System.out.println(i);
                    setHome(main);
                } else {
                    System.out.println("wrong info");
                }
            }

        });
        HBox buttonPane = new HBox(10, login, password);
        buttonPane.setAlignment(Pos.CENTER);

        centerPane.getChildren().add(buttonPane);

        main.setCenter(centerPane);

        Button createAccBtn = new Button();
        createAccBtn.setText("Create Account");
        createAccBtn.setTextFill(Color.WHITE);
        createAccBtn.getStyleClass().add("button");
        createAccBtn.setOnAction(e -> setCreateAccount(main));

        BorderPane.setAlignment(createAccBtn, Pos.BOTTOM_RIGHT);
        main.setBottom(createAccBtn);
    }

    public void setCreateAccount(BorderPane main) {
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

        txtPassword = new PasswordField();
        txtPassword.setPrefColumnCount(10);

        txtEmail = new TextField();
        txtEmail.setPrefColumnCount(10);
        
        Label userError = new Label();
        Label passError = new Label();
        Label emailError = new Label();
        
        HBox userHbox = new HBox(new Label("Username"), txtUsername,userError);
        userHbox.setSpacing(5);
        HBox passHbox = new HBox(new Label("Password"), txtPassword, passError);
        passHbox.setSpacing(10);
        
        HBox emailHbox = new HBox(new Label("Email"), txtEmail, emailError);
        userHbox.setSpacing(15);
        
        
        inputPane.addRow(0, userHbox);
        inputPane.addRow(1, passHbox);
        inputPane.addRow(2, emailHbox);
       
       
        centerPane.getChildren().add(inputPane);

        Button signUp = new Button("Create");
        Button cancel = new Button("Cancel");

        signUp.setOnAction(e -> {
            Account account = new Account();
            ArrayList<Account> accounts = account.load();
            int count = 0;
            if ((txtUsername.getText().matches("^[a-zA-Z0-9]{2,15}$"))
                    && (txtPassword.getText().matches("^[a-zA-Z0-9!@#\\$%\\^\\&*_=+-]{6,36}$"))
                    && txtEmail.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                {

                    createAccount(txtUsername.getText(), txtPassword.getText(), txtEmail.getText());
                    setLogin(main);

                }

            } else {
                System.out.println("error");
            }

        });
        cancel.setOnAction(e -> setLogin(main));
        HBox buttonPane = new HBox(10, signUp, cancel);
        buttonPane.setAlignment(Pos.CENTER);

        centerPane.getChildren().add(buttonPane);

        main.setCenter(centerPane);

    }

    public void setHome(BorderPane main) {
        Label heading = new Label("Investo");

        VBox centerPane = new VBox(10);
        centerPane.setAlignment(Pos.CENTER);

        GridPane selection = new GridPane();
        selection.getStyleClass().add("selectionPane");

        Circle searchCircle = new Circle();
        searchCircle.setRadius(100);
        searchCircle.getStyleClass().add("circle");
        Label searchLbl = new Label("Search");
        searchLbl.getStyleClass().add("homeLabel");
        StackPane searchPane = new StackPane(searchCircle, searchLbl);
        searchPane.getStyleClass().add("searchPane1");

        Circle portfolioCircle = new Circle();
        portfolioCircle.setRadius(100);
        portfolioCircle.getStyleClass().add("circle");
        Label portfolioLbl = new Label("Portfolio");
        portfolioLbl.getStyleClass().add("homeLabel");
        StackPane portfolioPane = new StackPane(portfolioCircle, portfolioLbl);
        portfolioPane.getStyleClass().add("portfolioPane1");
        portfolioPane.setOnMouseClicked(e -> setPortfolio(main));

        Circle recentCircle = new Circle();
        recentCircle.setRadius(100);
        recentCircle.getStyleClass().add("circle");
        Label recentLbl = new Label("Recent");
        recentLbl.getStyleClass().add("homeLabel");
        StackPane recentPane = new StackPane(recentCircle, recentLbl);
        recentPane.getStyleClass().add("recentPane1");

        Circle settingsCircle = new Circle();
        settingsCircle.setRadius(100);
        settingsCircle.getStyleClass().add("circle");
        Label settingsLbl = new Label("Portfolio");
        settingsLbl.getStyleClass().add("homeLabel");
        StackPane settingsPane = new StackPane(settingsCircle, settingsLbl);
        settingsPane.getStyleClass().add("settingsPane1");

        selection.addRow(0, searchPane, portfolioPane);
        selection.addRow(1, recentPane, settingsPane);

        HBox hbox = new HBox();
        Button logoutBtn = new Button();
        logoutBtn.setText("Logout");
        logoutBtn.getStyleClass().add("button");
        hbox.getChildren().add(logoutBtn);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        logoutBtn.setOnAction(e -> setLogin(main));

        centerPane.getChildren().add(selection);
        centerPane.getChildren().add(hbox);
        main.setCenter(centerPane);
    }

    public void setPortfolio(BorderPane main) {
        //setting tabPane at top
        TabPane tabPane = new TabPane();
        Tab newTab = new Tab("Add Portfolio+");
        Tab mainTab = new Tab("Main");
        tabPane.getTabs().add(mainTab);
        tabPane.getTabs().add(newTab);
        main.setCenter(tabPane);

        //adding content
        VBox contentBox = new VBox();
        contentBox.setPadding(new Insets(5));
        HBox searchBox = new HBox();
        searchBox.setPadding(new Insets(5));

        //search bar and modify button
        TextField searchTxtFld = new TextField();
        searchTxtFld.getStyleClass().add("searchTextField");
        searchTxtFld.setPromptText("Search...");

        Button modifyBtn = new Button("Modify");
        modifyBtn.getStyleClass().add("button");

        //table
        TableView table = new TableView();
        TableColumn tickerColumn = new TableColumn("Ticker");
        TableColumn priceColumn = new TableColumn("Price");
        TableColumn quantityColumn = new TableColumn("Quantity");
        TableColumn countryColumn = new TableColumn("Country");
        TableColumn companyColumn = new TableColumn("Company");
        TableColumn categoryColumn = new TableColumn("Category");
        TableColumn descriptColumn = new TableColumn("Description");
        table.getColumns().addAll(tickerColumn, priceColumn, quantityColumn, countryColumn, companyColumn, categoryColumn, descriptColumn);

        searchBox.getChildren().addAll(searchTxtFld, modifyBtn);
        contentBox.getChildren().addAll(searchBox, table);
        mainTab.setContent(contentBox);

        //setting bottom
        HBox backHBox = new HBox();
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> setHome(main));
        backHBox.setAlignment(Pos.BOTTOM_RIGHT);
        backBtn.getStyleClass().add("button");
        backHBox.getChildren().add(backBtn);
        main.setBottom(backHBox);
    }

}
