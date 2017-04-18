package investoapp;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * The GUI of the Investo App. Connects pieces of the program together in the
 * main method. n
 *
 * @author Abdul Tesqie
 * @author Tam Huynh
 *
 */
public class InvestoApp extends Application {

    private TextField txtUsername;
    private TextField txtPassword;
    private TextField txtEmail;
    private Account liveAccount;
    private Stock selectedStock;
    private int accountIndex;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        setLogin(primaryStage);
    }

    /**
     * Login page for existing accounts, Checks if USER/PASS combination is
     * valid before logging in.
     *
     * @author Tam
     * @param primaryStage
     */
    public void setLogin(Stage primaryStage) {
        BorderPane main = new BorderPane();
        Scene scene = new Scene(main, 450, 450);

//making logo
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

        //making input areas
        txtUsername = new TextField();
        txtUsername.setPrefColumnCount(10);

        txtPassword = new PasswordField();
        txtPassword.setPrefColumnCount(10);

        inputPane.addRow(0, new Label("Username"), txtUsername);
        inputPane.addRow(1, new Label("Password"), txtPassword);

        Label invalidLogin = new Label();
        centerPane.getChildren().add(invalidLogin);
        centerPane.getChildren().add(inputPane);

        Button login = new Button("Login");

        setButton(login);

        HBox buttonPane = new HBox(10, login);
        buttonPane.setAlignment(Pos.CENTER);

        centerPane.getChildren().add(buttonPane);

        main.setCenter(centerPane);

        Button createAccBtn = new Button("Create Account");
        setButton(createAccBtn);
        createAccBtn.setTextFill(Color.WHITE);

        //Switches Scene to Create Account
        createAccBtn.setOnAction(e -> {

            setCreateAccount(primaryStage);

        });

        // Checks if USER/PASS combiniaiton is valid
        login.setOnAction(e -> {
            Account account = new Account();
            ArrayList<Account> accounts = account.load();

            SHA256InJava sj = new SHA256InJava();
            String passwordHash = sj.getSHA256Hash(txtPassword.getText());
            try {
                for (int i = 0; i < accounts.size(); i++) {

                    if (accounts.get(i).getUsername().equals(txtUsername.getText()) && accounts.get(i).getPassword().equals(passwordHash)) {

                        liveAccount = accounts.get(i);
                        accountIndex = i;

                        invalidLogin.setText("");
                        setHome(primaryStage);

                    } else {
                        invalidLogin.setText("Invalid user/pass combination");
                    }
                }
            } catch (NullPointerException ignoreMe) {
                invalidLogin.setText("Invalid user/pass combination");
            }
        });

        BorderPane.setAlignment(createAccBtn, Pos.BOTTOM_RIGHT);
        main.setBottom(createAccBtn);
        main.getStyleClass().add("root");
        scene.getStylesheets().add("investoapp/InvestoStyle.css");
        primaryStage.setTitle("Investo Application");
        primaryStage.setWidth(450);
        primaryStage.setHeight(450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*
        Creates a new account for the User.
        @author Tam
     */
    public void setCreateAccount(Stage primaryStage) {
        BorderPane main = new BorderPane();
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

        inputPane.addRow(0, new Label("Username"), txtUsername, userError);
        inputPane.addRow(1, new Label("Password"), txtPassword, passError);
        inputPane.addRow(2, new Label("Email"), txtEmail, emailError);

        centerPane.getChildren().add(inputPane);

        Button signUp = new Button("Create");
        Button cancel = new Button("Cancel");
        setButton(signUp);
        setButton(cancel);

        /*Creates a new account and uses RegExp to verify user input. Also checks
          to see if the User exists already.
        
        
        
         */
        signUp.setOnAction(e -> {
            Account account = new Account();
            ArrayList<Account> accounts = account.load();

            int errors = 0;
            boolean taken = false;

            try {
                for (int i = 0; i < accounts.size(); i++) {

                    if (accounts.get(i).getUsername().equals(txtUsername.getText())) {

                        userError.setText("Username is taken");
                        taken = true;
                    }
                }

            } catch (NullPointerException ignoreMe) {
            }

            if (!taken) {
                if (!txtUsername.getText().matches("^[a-zA-Z0-9]{2,15}$")) {
                    userError.setText("Must be 2-15 characters");
                    errors++;
                } else {
                    userError.setText("");
                }

                if (!txtPassword.getText().matches("^[a-zA-Z0-9!@#\\$%\\^\\&*_=+-]{4,36}$")) {
                    passError.setText("Must be 4-36 characters");
                    errors++;
                } else {
                    passError.setText("");
                }
                if (!txtEmail.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {

                    emailError.setText("Enter a valid email");
                    errors++;
                } else {
                    emailError.setText("");
                }
                if (errors == 0) {
                    newAccount();
                    setLogin(primaryStage);
                } else {
                    System.out.println("error");
                }
            }
        }
        );
        cancel.setOnAction(e
                -> setLogin(primaryStage));
        HBox buttonPane = new HBox(10, signUp, cancel);

        buttonPane.setAlignment(Pos.CENTER);

        centerPane.getChildren()
                .add(buttonPane);

        main.setCenter(centerPane);
        Scene createAccountScene = new Scene(main, 450, 450);
        createAccountScene.getStylesheets().add("investoapp/InvestoStyle.css");
        primaryStage.setWidth(450);
        primaryStage.setHeight(450);
        primaryStage.setScene(createAccountScene);

    }

    /**
     * Creating a new user
     *
     * @author Abdul Tesqie
     */
    private void newAccount() {
        SHA256InJava sj = new SHA256InJava();
        String passwordHash = sj.getSHA256Hash(txtPassword.getText());
        Account newAccount = new Account(txtUsername.getText(), passwordHash, txtEmail.getText());
        newAccount.save(newAccount, -1);
    }

    //The homepage of the the Investo App
    //@author Tam
    public void setHome(Stage primaryStage) {
        BorderPane main = new BorderPane();
        Circle titleHolder = new Circle();
        titleHolder.setRadius(75);
        titleHolder.setStroke(Color.RED);
        titleHolder.setFill(Color.WHITE);
        titleHolder.setStrokeWidth(10);

        Label appTitle = new Label("Investo");
        StackPane holder = new StackPane(titleHolder, appTitle);
        main.setTop(holder);

        primaryStage.setHeight(550);
        primaryStage.setWidth(725);
        Label heading = new Label("Investo");

        VBox centerPane = new VBox(10);
        centerPane.setAlignment(Pos.CENTER);

        GridPane selection = new GridPane();
        selection.getStyleClass().add("selectionPane");

        Circle searchCircle = new Circle();
        searchCircle.setRadius(100);
        searchCircle.getStyleClass().add("circle");
        Label searchLbl = new Label("Stock Search");
        searchLbl.getStyleClass().add("homeLabel");
        StackPane searchPane = new StackPane(searchCircle, searchLbl);
        searchPane.getStyleClass().add("searchPane1");
        searchPane.setOnMouseClicked(e -> setStockInfo(primaryStage));

        Circle portfolioCircle = new Circle();
        portfolioCircle.setRadius(100);
        portfolioCircle.getStyleClass().add("circle");
        Label portfolioLbl = new Label("Portfolio");
        portfolioLbl.getStyleClass().add("homeLabel");
        StackPane portfolioPane = new StackPane(portfolioCircle, portfolioLbl);
        portfolioPane.getStyleClass().add("portfolioPane1");
        portfolioPane.setOnMouseClicked(e -> setPortfolio(primaryStage));

        Circle screenerCircle = new Circle();
        screenerCircle.setRadius(100);
        screenerCircle.getStyleClass().add("circle");
        Label screenerLbl = new Label("Screener");
        screenerLbl.getStyleClass().add("homeLabel");
        StackPane screenerPane = new StackPane(screenerCircle, screenerLbl);
        screenerPane.getStyleClass().add("screenerPane");
        screenerPane.setOnMouseClicked(e -> setScreener(primaryStage));

        selection.addRow(0, searchPane, portfolioPane, screenerPane);

        HBox hbox = new HBox();
        Button logoutBtn = new Button("Logout");
        setButton(logoutBtn);
        hbox.getChildren().add(logoutBtn);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        logoutBtn.setOnAction(e -> setLogin(primaryStage));

        centerPane.getChildren().add(selection);
        centerPane.getChildren().add(hbox);
        main.setCenter(centerPane);
        Scene homeScene = new Scene(main);
        homeScene.getStylesheets().add("investoapp/InvestoStyle.css");
        primaryStage.setScene(homeScene);
    }

    /**
     * Porfolio Scene, organizes user's stocks in a watch list.
     *
     * @param primaryStage
     * @author Tam
     */
    public void setPortfolio(Stage primaryStage) {
        BorderPane main = new BorderPane();
        //setting tabPane at top
        Circle titleHolder = new Circle();
        titleHolder.setRadius(75);
        titleHolder.setStroke(Color.RED);
        titleHolder.setFill(Color.WHITE);
        titleHolder.setStrokeWidth(10);

        Label appTitle = new Label("Investo\nPortfolio");
        StackPane holder = new StackPane(titleHolder, appTitle);
        main.setTop(holder);

        TabPane tabPane = new TabPane();
        Tab mainTab = new Tab("Portfolio");
        mainTab.setClosable(false);
        tabPane.getTabs().add(mainTab);
        main.setCenter(tabPane);

        //adding content
        VBox contentBox = new VBox();
        contentBox.setPadding(new Insets(5));
        HBox searchBox = new HBox();
        searchBox.setPadding(new Insets(5));

        Button removeBtn = new Button("Remove Stock");
        setButton(removeBtn);

        //table
        TableView<Stock> table = new TableView<>();

        TableColumn tickerColumn = new TableColumn("Ticker");
        TableColumn priceColumn = new TableColumn("Price");
        TableColumn countryColumn = new TableColumn("Country");
        TableColumn companyColumn = new TableColumn("Company");
        TableColumn categoryColumn = new TableColumn("Category");

        table.getColumns().addAll(tickerColumn, priceColumn,
                countryColumn, companyColumn, categoryColumn);

        table.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && table.getSelectionModel().getSelectedItem() != null) {

                selectedStock = table.getSelectionModel().getSelectedItem();
                setStockInfo(primaryStage);
            }

        });

        tickerColumn.setCellValueFactory(new PropertyValueFactory<>("ticker"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("hq_country"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("industry_category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        if (liveAccount.getPortfolio() != null) {
            Portfolio portfolioMain = liveAccount.getPortfolio();
            ArrayList<Stock> portfolioStocks = liveAccount.getPortfolio().getStocks();
            portfolioMain.setStock(portfolioStocks);
            liveAccount.setPortfolio(portfolioMain);

            table.getItems().addAll(portfolioStocks);
        }

        searchBox.getChildren().add(removeBtn);
        searchBox.setAlignment(Pos.CENTER_RIGHT);
        contentBox.getChildren().addAll(searchBox, table);
        mainTab.setContent(contentBox);

        removeBtn.setOnAction(clickEvent -> {
            liveAccount.getPortfolio().getStocks().remove(table.getSelectionModel().getSelectedItem());
            liveAccount.save(liveAccount, accountIndex);
            setPortfolio(primaryStage);

        });

        //setting bottom
        HBox backHBox = new HBox();
        Button backBtn = new Button("Back");
        setButton(backBtn);
        backBtn.setOnAction(e -> {
            setHome(primaryStage);
            backBtn.setVisible(false);

        });
        backHBox.setAlignment(Pos.BOTTOM_RIGHT);
        backBtn.getStyleClass().add("button");
        backHBox.getChildren().add(backBtn);
        main.setBottom(backHBox);
        Scene portfolioScene = new Scene(main);
        portfolioScene.getStylesheets().add("investoapp/InvestoStyle.css");

        primaryStage.setHeight(600);
        primaryStage.setWidth(720);
        primaryStage.setScene(portfolioScene);
    }

    /**
     * Method to change scene to the stock info page.
     *
     * @param primaryStage
     * @author Tam
     */
    public void setStockInfo(Stage primaryStage) {
        BorderPane main = new BorderPane();
        StackPane logoPane = new StackPane();

        //set Top
        Circle titleHolder = new Circle();
        titleHolder.setRadius(75);
        titleHolder.setStroke(Color.RED);
        titleHolder.setFill(Color.WHITE);
        titleHolder.setStrokeWidth(10);

        Label appTitle = new Label("Investo\nStockInfo");
        StackPane holder = new StackPane(titleHolder, appTitle);
        logoPane.getChildren().add(holder);

        VBox topBox = new VBox();
        Label title = new Label("Stock Information");
        HBox searchBox = new HBox();
        TextField searchTxtFld = new TextField();
        searchTxtFld.getStyleClass().add("searchTextField");
        searchTxtFld.setPromptText("aapl...");

        topBox.getChildren().addAll(title, searchBox);
        topBox.setAlignment(Pos.CENTER);

        main.setTop(searchBox);

        //set left
        HBox contentBox = new HBox(10);
        contentBox.setAlignment(Pos.CENTER);

        GridPane stockInfo = new GridPane();
        stockInfo.setAlignment(Pos.CENTER_LEFT);

        stockInfo.getStyleClass().add("stockInfo");

        RowConstraints[] row = new RowConstraints[8];

        for (int i = 0; i < 8; i++) {
            row[i] = new RowConstraints();
        }
        for (int i = 1; i < 7; i++) {
            row[i].setMinHeight(29.42);
        }

        row[7].setMinHeight(420);
        stockInfo.getRowConstraints().addAll(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7]);

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();

        col1.setMinWidth(100);
        col1.setHalignment(HPos.CENTER);
        col2.setMaxWidth(400);
        col2.setMinWidth(400);
        stockInfo.getColumnConstraints().addAll(col1, col2);

        stockInfo.setStyle("-fx-font: 15px sans-serif");

        Label ticker = new Label("Ticker");
        Label openPrice = new Label("Open price");
        Label closePrice = new Label("Close Price");
        Label sector = new Label("Sector");
        Label industry = new Label("Industry");
        Label country = new Label("Country");
        Label description = new Label("Description");
        description.setPadding(new Insets(5));

        TextField companyNameInfo = new TextField();
        companyNameInfo.setMinHeight(29.42);
        companyNameInfo.setMinWidth(450);
        companyNameInfo.setAlignment(Pos.CENTER);
        companyNameInfo.getStyleClass().add("stockBackground");
        companyNameInfo.setEditable(false);

        TextField tickerInfo = new TextField();
        tickerInfo.setAlignment(Pos.CENTER);
        tickerInfo.setEditable(false);
        tickerInfo.setPrefHeight(29.42);

        TextField openPriceInfo = new TextField();
        openPriceInfo.getStyleClass().add("greyTxtFld");
        openPriceInfo.setEditable(false);
        openPriceInfo.setPrefHeight(29.42);

        TextField closePriceInfo = new TextField();
        closePriceInfo.setAlignment(Pos.CENTER);
        closePriceInfo.setEditable(false);
        closePriceInfo.setPrefHeight(29.42);

        TextField sectorInfo = new TextField();
        sectorInfo.getStyleClass().add("greyTxtFld");
        sectorInfo.setEditable(false);
        sectorInfo.setPrefHeight(29.42);

        TextField industryInfo = new TextField();
        industryInfo.setAlignment(Pos.CENTER);
        industryInfo.setEditable(false);
        industryInfo.setPrefHeight(29.42);

        TextField countryInfo = new TextField();
        countryInfo.getStyleClass().add("greyTxtFld");
        countryInfo.setEditable(false);
        countryInfo.setPrefHeight(29.42);

        TextArea descriptionInfo = new TextArea(" ");
        descriptionInfo.setEditable(false);
        descriptionInfo.setMaxWidth(400);
        descriptionInfo.setWrapText(true);
        descriptionInfo.setMaxHeight(420);
        descriptionInfo.setPrefHeight(29.42);

        //stockInfo.addRow(0, companyNameInfo);
        stockInfo.addRow(1, ticker, tickerInfo);
        stockInfo.addRow(2, openPrice, openPriceInfo);
        stockInfo.addRow(3, closePrice, closePriceInfo);
        stockInfo.addRow(4, sector, sectorInfo);
        stockInfo.addRow(5, industry, industryInfo);
        stockInfo.addRow(6, country, countryInfo);
        stockInfo.addRow(7, description, descriptionInfo);

        Label newsLbl = new Label("News");
        newsLbl.getStyleClass().add("newsLbl");
        newsLbl.setPrefHeight(29.42);
        newsLbl.setPrefWidth(600);

        ListView<StockNews> newsArea = new ListView<>();
        newsArea.setMinHeight(602);

        newsArea.setStyle("-fx-font: 15px sans-serif");

        GridPane newsPane = new GridPane();

        newsPane.addRow(0, newsLbl);
        newsPane.addRow(1, newsArea);
        newsPane.setAlignment(Pos.CENTER);

        RowConstraints rowNews = new RowConstraints();
        rowNews.setMaxHeight(75);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(companyNameInfo, stockInfo);

        contentBox.getChildren().addAll(vBox, newsPane);

        main.setCenter(contentBox);
        main.setPadding(new Insets(12));

        //set center
        //set Bottom
        Button backBtn = new Button("Back");
        setButton(backBtn);
        HBox hboxBtn = new HBox(backBtn);

        hboxBtn.setAlignment(Pos.BOTTOM_RIGHT);

        backBtn.setOnAction(e -> {
            setHome(primaryStage);
        });
        main.setBottom(hboxBtn);
        Pane spacer = new Pane();
        HBox.setHgrow(
                spacer,
                Priority.SOMETIMES
        );
        VBox searchVbox = new VBox();
        searchVbox.setSpacing(5);
        Button searchTicker = new Button("Search Ticker:");
        searchTicker.setStyle("-fx-font: 15px sans-serif;-fx-background-color:rosybrown;-fx-text-fill: white;");
        VBox rightVBox = new VBox();

        Button addtoPortfolio = new Button("add to portfolio");
        setButton(addtoPortfolio);
        Button resetBtn = new Button("Reset");
        setButton(resetBtn);

        Label responseLbl = new Label();
        rightVBox.setSpacing(5);
        rightVBox.setAlignment(Pos.CENTER_RIGHT);

        rightVBox.getChildren().addAll(addtoPortfolio, resetBtn, responseLbl);
        searchVbox.getChildren().addAll(searchTicker, searchTxtFld);
        searchBox.getChildren().addAll(searchVbox, spacer, rightVBox);

        if (selectedStock != null) {
            Stock stock = selectedStock;
            fillStockInfo(descriptionInfo, stock, companyNameInfo, tickerInfo, openPriceInfo, closePriceInfo, industryInfo, sectorInfo, countryInfo);

            ArrayList<StockNews> stockNews = stock.getNews();

            HostServices host = getHostServices();

            newsArea.getItems().setAll(stockNews);

            newsArea.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2) {
                    StockNews selectedNews = newsArea.getSelectionModel().getSelectedItem();
                    host.showDocument(selectedNews.getUrl());
                }
            });

        }

        searchTxtFld.setOnAction(e -> {
            Stock stock = new Stock(searchTxtFld.getText(), true);

            if (stock.getName() != null) {
                selectedStock = stock;

                selectedStock.connectAndFetchNews();

                fillStockInfo(descriptionInfo, selectedStock, companyNameInfo, tickerInfo, openPriceInfo, closePriceInfo, industryInfo, sectorInfo, countryInfo);

                ArrayList<StockNews> stockNews = selectedStock.getNews();
                Hyperlink link = new Hyperlink();
                TextField t = new TextField();

                try {
                    newsArea.getItems().setAll(stockNews);
                } catch (NullPointerException ignoreMe) {
                }

                HostServices host = getHostServices();

                newsArea.setOnMouseClicked(clickEvent -> {
                    if (clickEvent.getClickCount() == 2) {
                        StockNews selectedNews = newsArea.getSelectionModel().getSelectedItem();
                        host.showDocument(selectedNews.getUrl());
                    }
                });

            } else {
                responseLbl.setText("You have entered a wrong ticker.");
            }

        });

        resetBtn.setOnAction(e -> {
            emptyStockInfo(descriptionInfo, companyNameInfo, tickerInfo, openPriceInfo, closePriceInfo, industryInfo, sectorInfo, countryInfo);
            newsArea.getItems().clear();
            selectedStock = null;
            responseLbl.setText("");
        });

        addtoPortfolio.setOnAction(event -> {
            Account account = new Account();
            if (liveAccount.getPortfolio() == null) {
                liveAccount.setPortfolio(new Portfolio());
                liveAccount.getPortfolio().setStock(new ArrayList<>());
                if (selectedStock != null) {
                    liveAccount.getPortfolio().getStocks().add(selectedStock);
                }
                account.save(liveAccount, accountIndex);

            } else if (liveAccount.getPortfolio() != null) {
                boolean exists = false;
                try {
                    for (int i = 0; i < liveAccount.getPortfolio().getStocks().size(); i++) {
                        if (liveAccount.getPortfolio().getStocks().get(i).getTicker().equals(selectedStock.getTicker())) {
                            exists = true;
                            responseLbl.setText("The stock is already in your portfolio.");
                        }
                    }

                    if (!exists && selectedStock != null) {
                        liveAccount.getPortfolio().getStocks().add(selectedStock);
                        account.save(liveAccount, accountIndex);
                        responseLbl.setText("Your stock has been added.");
                    }
                    if (selectedStock == null) {
                        responseLbl.setText("You must select a stock.");
                    }
                } catch (NullPointerException ex) {
                    responseLbl.setText("You must select a stock.");
                }
            }

        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(logoPane, main);
        vbox.setSpacing(7);

        Scene stockScene = new Scene(vbox);
        stockScene.getStylesheets().add("investoapp/InvestoStyle.css");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(975);
        primaryStage.setScene(stockScene);
    }

    /**
     * A method to fill in the Stock Info based on the ticker that is searched.
     *
     * @author Tam
     *
     */
    private void fillStockInfo(TextArea descriptionInfo, Stock stock, TextField companyNameInfo, TextField tickerInfo, TextField openPriceInfo, TextField closePriceInfo, TextField industryInfo, TextField sectorInfo, TextField countryInfo) {
        if (stock.getTicker() != null) {
            descriptionInfo.setText(stock.getLong_description());
            companyNameInfo.setText(stock.getName());
            tickerInfo.setText(stock.getTicker());
            openPriceInfo.setText("$" + Double.toString(stock.getPrice().getOpen()));
            closePriceInfo.setText("$" + Double.toString(stock.getPrice().getClose()));
            industryInfo.setText(stock.getIndustry_category());
            sectorInfo.setText(stock.getSector());
            countryInfo.setText(stock.getHq_country());
        }
    }

    //Emptying the stock Info when reset button is pressed.
    //@author Tam
    private void emptyStockInfo(TextArea descriptionInfo, TextField companyNameInfo, TextField tickerInfo, TextField openPriceInfo, TextField closePriceInfo, TextField industryInfo, TextField sectorInfo, TextField countryInfo) {

        descriptionInfo.setText("");
        companyNameInfo.setText("");
        tickerInfo.setText("");
        openPriceInfo.setText("");
        closePriceInfo.setText("");
        industryInfo.setText("");
        sectorInfo.setText("");
        countryInfo.setText("");

    }

    /**
     * The screener scene to filter stocks
     *
     * @param primaryStage
     * @author Tam
     */
    public void setScreener(Stage primaryStage) {

        GridPane screenerPane = new GridPane();
        screenerPane.setPadding(new Insets(10));
        screenerPane.setGridLinesVisible(true);
        screenerPane.getStyleClass().add("stockInfo");
        screenerPane.setPrefSize(600, 600);
        screenerPane.setAlignment(Pos.CENTER);

        //set top
        StackPane logoPane = new StackPane();

        Circle titleHolder = new Circle();
        titleHolder.setRadius(75);
        titleHolder.setStroke(Color.RED);
        titleHolder.setFill(Color.WHITE);
        titleHolder.setStrokeWidth(10);

        Label appTitle = new Label("Investo\nScreener");
        StackPane holder = new StackPane(titleHolder, appTitle);
        logoPane.getChildren().add(holder);

        Label criteriaLbl = new Label("Criteria");
        Label conditionLbl = new Label("Condition");
        Label valueLbl = new Label("Value");

        //something else
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();

        col1.setMinWidth(200);
        col1.setHalignment(HPos.LEFT);

        col2.setMinWidth(200);
        col2.setHalignment(HPos.CENTER);

        col3.setMinWidth(200);
        col3.setHalignment(HPos.CENTER);

        // ComboBox[] conditionBox = new ComboBox[7];
        ArrayList<ComboBox> conditionBox = new ArrayList<>();
        ArrayList<TextField> valueFld = new ArrayList<>();

        ArrayList<CheckBox> chkBox = new ArrayList<>();
        chkBox.add(new CheckBox("Earnings Per Share"));
        chkBox.add(new CheckBox("Price"));
        chkBox.add(new CheckBox("Dividend Yield"));
        chkBox.add(new CheckBox("Market Cap"));
        chkBox.add(new CheckBox("52 Week High"));
        chkBox.add(new CheckBox("P/E Ratio"));

        String[] conditions = {"Greater than", "Less Than"};

        for (int i = 0; i < 6; i++) {

            conditionBox.add(new ComboBox());
            valueFld.add(new TextField());
            conditionBox.get(i).getItems().addAll(conditions);

            screenerPane.addRow(i + 1, chkBox.get(i), conditionBox.get(i), valueFld.get(i));

        }

        screenerPane.getColumnConstraints().addAll(col1, col2, col3);

        screenerPane.addRow(0, criteriaLbl, conditionLbl, valueLbl);

        screenerPane.getRowConstraints().addAll(row1, row2);

        Label stockLbl = new Label("Screened Stock");
        ListView<Stock> stockList = new ListView();
        stockList.setMinHeight(400);
        stockList.setMinWidth(400);

        GridPane stockListPane = new GridPane();
        stockListPane.addRow(0, stockLbl);
        stockListPane.addRow(1, stockList);
        stockListPane.setAlignment(Pos.CENTER);

        Button backBtn = new Button("Back");
        backBtn.setAlignment(Pos.BOTTOM_LEFT);
        setButton(backBtn);

        backBtn.setOnAction(e -> {
            setHome(primaryStage);
        });
        Pane spacer = new Pane();
        HBox.setHgrow(
                spacer,
                Priority.SOMETIMES
        );

        Button searchBtn = new Button("Search");
        setButton(searchBtn);
        searchBtn.setAlignment(Pos.BOTTOM_RIGHT);

        HBox hboxBtn = new HBox();
        hboxBtn.getChildren().addAll(searchBtn, spacer, backBtn);

        /*Checks to see what user selected for the screener and fetches the 
        the correct filter.
        
        @author Abdul Tesqie
        
         */
        searchBtn.setOnAction(e -> {
            StringBuilder screenerUrl = new StringBuilder();
            for (int i = 0; i < conditionBox.size(); i++) {
                String criteria = null;
                String condition = null;
                String value;
                if (chkBox.get(i).isSelected()) {
                    switch (i) {
                        case 0:
                            criteria = "basiceps";
                            break;
                        case 1:
                            criteria = "close_price";
                            break;
                        case 2:
                            criteria = "dividendyield";
                            break;
                        case 3:
                            criteria = "marketcap";
                            break;
                        case 4:
                            criteria = "52_week_high";
                            break;
                        case 5:
                            criteria = "pricetoearnings";
                            break;
                        default:
                            break;
                    }
                    switch (conditionBox.get(i).getSelectionModel().getSelectedIndex()) {
                        case 0:
                            condition = "gt";
                            break;
                        case 1:
                            condition = "lt";
                            break;

                    }
                    value = valueFld.get(i).getText();
                    screenerUrl.append(criteria);
                    screenerUrl.append("~");
                    screenerUrl.append(condition);
                    screenerUrl.append("~");
                    screenerUrl.append(value);
                    screenerUrl.append(",");
                }

            }

            Screener screener = new Screener(screenerUrl.toString());
            stockList.getItems().clear();
            stockList.getItems().addAll(screener.getStocksFromScreener());

        });

        stockList.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {

                selectedStock = new Stock(stockList.getSelectionModel().getSelectedItem().getTicker(), true);
                selectedStock.connectAndFetchNews();
                setStockInfo(primaryStage);
            }
        });

        BorderPane paneScreener = new BorderPane();
        //screener
        paneScreener.setLeft(screenerPane);
        paneScreener.setBottom(hboxBtn);
        paneScreener.setCenter(stockListPane);
        paneScreener.setTop(logoPane);

        Scene sceneScreener = new Scene(paneScreener);
        sceneScreener.getStylesheets().add("investoapp/InvestoStyle.css");
        primaryStage.setHeight(675);
        primaryStage.setWidth(1050);
        primaryStage.setScene(sceneScreener);
        primaryStage.show();

    }

    /**
     * On hover the button switches colors to blue to indicate the mouse is on
     * the button
     *
     * @param button
     *
     * @author Tam
     */
    public void setButton(Button button) {
        button.getStyleClass().add("button");
        button.setOnMouseEntered((MouseEvent t) -> {
            button.setStyle("-fx-background-color:blue;");
        });
        button.setOnMouseExited((MouseEvent t) -> {
            button.setStyle("-fx-background-color:rosybrown;");
        });
    }
}
