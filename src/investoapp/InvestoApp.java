package investoapp;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author tesqie
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

        BorderPane main = new BorderPane();
        main.setPadding(new Insets(10));

        Scene scene = new Scene(main, 1200, 800);
        scene.getStylesheets().add("investoapp/InvestoStyle.css");

        setLogin(main, primaryStage);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Investo Application");
        primaryStage.show();

    }

    public void setLogin(BorderPane main, Stage primaryStage) {
        primaryStage.setHeight(400);
        primaryStage.setWidth(430);

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

        Label invalidLogin = new Label();
        centerPane.getChildren().add(invalidLogin);
        centerPane.getChildren().add(inputPane);

        Button login = new Button("Login");
        Button password = new Button("Password");

        //login.setOnKeyPressed(value);
        HBox buttonPane = new HBox(10, login, password);
        buttonPane.setAlignment(Pos.CENTER);

        centerPane.getChildren().add(buttonPane);

        main.setCenter(centerPane);

        Button createAccBtn = new Button();
        createAccBtn.setText("Create Account");
        createAccBtn.setTextFill(Color.WHITE);
        createAccBtn.getStyleClass().add("button");
        createAccBtn.setOnAction(e -> {

            setCreateAccount(main, primaryStage);
            createAccBtn.setVisible(false);
        });
        login.setOnAction(e -> {
            Account account = new Account();
            ArrayList<Account> accounts = account.load();

            SHA256InJava sj = new SHA256InJava();
            String passwordHash = sj.getSHA256Hash(txtPassword.getText());

            for (int i = 0; i < accounts.size(); i++) {

                if (accounts.get(i).getUsername().equals(txtUsername.getText()) && accounts.get(i).getPassword().equals(passwordHash)) {

                    liveAccount = accounts.get(i);
                    accountIndex = i;

                    invalidLogin.setText("");
                    setHome(main, primaryStage);
                    createAccBtn.setVisible(false);
                } else {
                    invalidLogin.setText("Invalid user/pass combination");
                }
            }
        });
        BorderPane.setAlignment(createAccBtn, Pos.BOTTOM_RIGHT);
        main.setBottom(createAccBtn);
    }

    public void setCreateAccount(BorderPane main, Stage primaryStage) {
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

        signUp.setOnAction(e -> {
            Account account = new Account();
            ArrayList<Account> accounts = account.load();
            int errors = 0;
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
                setLogin(main, primaryStage);
            } else {
                System.out.println("error");
            }

        }
        );
        cancel.setOnAction(e
                -> setLogin(main, primaryStage));
        HBox buttonPane = new HBox(10, signUp, cancel);

        buttonPane.setAlignment(Pos.CENTER);

        centerPane.getChildren()
                .add(buttonPane);

        main.setCenter(centerPane);

    }

    private void newAccount() {
        SHA256InJava sj = new SHA256InJava();
        String passwordHash = sj.getSHA256Hash(txtPassword.getText());
        Account newAccount = new Account(txtUsername.getText(), passwordHash, txtEmail.getText());
        newAccount.save(newAccount, -1);
    }

    public void setHome(BorderPane main, Stage primaryStage) {
        primaryStage.setHeight(800);
        primaryStage.setWidth(1130);
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
        searchPane.setOnMouseClicked(e -> setStockInfo(main, primaryStage));

        Circle portfolioCircle = new Circle();
        portfolioCircle.setRadius(100);
        portfolioCircle.getStyleClass().add("circle");
        Label portfolioLbl = new Label("Portfolio");
        portfolioLbl.getStyleClass().add("homeLabel");
        StackPane portfolioPane = new StackPane(portfolioCircle, portfolioLbl);
        portfolioPane.getStyleClass().add("portfolioPane1");
        portfolioPane.setOnMouseClicked(e -> setPortfolio(main, primaryStage));

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
        logoutBtn.setOnAction(e -> setLogin(main, primaryStage));

        centerPane.getChildren().add(selection);
        centerPane.getChildren().add(hbox);
        main.setCenter(centerPane);
    }

    public void setPortfolio(BorderPane main, Stage primaryStage) {
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
        TableView<Stock> table = new TableView<>();
        TableColumn tickerColumn = new TableColumn("Ticker");
        TableColumn priceColumn = new TableColumn("Price");
        TableColumn quantityColumn = new TableColumn("Quantity");
        TableColumn countryColumn = new TableColumn("Country");
        TableColumn companyColumn = new TableColumn("Company");
        TableColumn categoryColumn = new TableColumn("Category");
        TableColumn descriptColumn = new TableColumn("Description");
        table.getColumns().addAll(tickerColumn, priceColumn, quantityColumn, countryColumn, companyColumn, categoryColumn, descriptColumn);

        table.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {

                selectedStock = table.getSelectionModel().getSelectedItem();
                setStockInfo(main, primaryStage);
            }

        });

        tickerColumn.setCellValueFactory(new PropertyValueFactory<>("ticker"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("hq_country"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("industry_category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        if (!(liveAccount.getPortfolio() == null)) {
            Portfolio portfolioMain = liveAccount.getPortfolio();
            ArrayList<Stock> portfolioStocks = liveAccount.getPortfolio().getStock();
            portfolioMain.setStock(portfolioStocks);
            liveAccount.setPortfolio(portfolioMain);

            table.getItems().addAll(portfolioStocks);
        }

        searchBox.getChildren().addAll(searchTxtFld, modifyBtn);
        contentBox.getChildren().addAll(searchBox, table);
        mainTab.setContent(contentBox);

        //setting bottom
        HBox backHBox = new HBox();
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> {
            setHome(main, primaryStage);
            backBtn.setVisible(false);

        });
        backHBox.setAlignment(Pos.BOTTOM_RIGHT);
        backBtn.getStyleClass().add("button");
        backHBox.getChildren().add(backBtn);
        main.setBottom(backHBox);
    }

    public void setStockInfo(BorderPane main, Stage primaryStage) {
        //set Top
        VBox topBox = new VBox();
        Label title = new Label("Stock Information");
        HBox searchBox = new HBox();
        TextField searchTxtFld = new TextField();
        searchTxtFld.getStyleClass().add("searchTextField");
        searchTxtFld.setPromptText("Search...");

        //Button modifyBtn = new Button("Modify");
        //modifyBtn.getStyleClass().add("button");
        searchBox.getChildren().addAll(searchTxtFld);
        topBox.getChildren().addAll(title, searchBox);
        topBox.setAlignment(Pos.CENTER);

        main.setTop(searchBox);

        //set left
        HBox contentBox = new HBox(10);
        contentBox.setAlignment(Pos.CENTER);
        TableView stockTable = new TableView();

        GridPane stockInfo = new GridPane();
        stockInfo.setAlignment(Pos.CENTER_LEFT);

        stockInfo.getStyleClass().add("stockInfo");

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        RowConstraints row5 = new RowConstraints();
        RowConstraints row7 = new RowConstraints();
        RowConstraints row8 = new RowConstraints();
        RowConstraints row9 = new RowConstraints();
        row1.setMaxHeight(75);
        row2.setMaxHeight(75);
        row3.setMaxHeight(75);
        row4.setMaxHeight(75);
        row5.setMaxHeight(75);
        row7.setMaxHeight(75);
        row8.setMaxHeight(75);
        row9.setMaxHeight(400);
        row9.setMinHeight(400);
        stockInfo.getRowConstraints().addAll(row1, row2, row3, row4, row5, row7, row8, row9);

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setMaxWidth(400);
        col2.setMinWidth(400);
        stockInfo.getColumnConstraints().addAll(col1, col2);

        Label companyName = new Label("Company");
        Label ticker = new Label("Ticker");
        Label openPrice = new Label("Open price");
        Label closePrice = new Label("Close Price");
        Label sector = new Label("Sector");
        Label industry = new Label("Industry");
        Label country = new Label("Country");
        Label description = new Label("Description");

        TextField companyNameInfo = new TextField();
        companyNameInfo.setEditable(false);

        TextField tickerInfo = new TextField();
        tickerInfo.setEditable(false);

        TextField openPriceInfo = new TextField();
        openPriceInfo.setEditable(false);

        TextField closePriceInfo = new TextField();
        closePriceInfo.setEditable(false);

        TextField previousCloseInfo = new TextField();
        previousCloseInfo.setEditable(false);

        TextField sectorInfo = new TextField();
        sectorInfo.setEditable(false);

        TextField industryInfo = new TextField();
        industryInfo.setEditable(false);

        TextField countryInfo = new TextField();
        countryInfo.setEditable(false);

        TextArea descriptionInfo = new TextArea(" ");
        descriptionInfo.setEditable(false);
        descriptionInfo.setMaxWidth(400);
        descriptionInfo.setWrapText(true);
        descriptionInfo.setMaxHeight(400);

        stockInfo.addRow(0, companyName, companyNameInfo);
        stockInfo.addRow(1, ticker, tickerInfo);
        stockInfo.addRow(2, openPrice, openPriceInfo);
        stockInfo.addRow(3, closePrice, closePriceInfo);
        stockInfo.addRow(4, sector, sectorInfo);
        stockInfo.addRow(5, industry, industryInfo);
        stockInfo.addRow(6, country, countryInfo);
        stockInfo.addRow(7, description, descriptionInfo);

        Label newsLbl = new Label("News");
        newsLbl.getStyleClass().add("newsLbl");
        newsLbl.setPrefWidth(600);

        ListView<StockNews> newsArea = new ListView<>();

//        TextArea newsTxtArea = new TextArea();
//        newsTxtArea.setEditable(false);
//        newsTxtArea.setMaxHeight(600);
//        newsTxtArea.setMinHeight(590);
//        newsTxtArea.setMaxWidth(600);
//        newsTxtArea.setMinWidth(600);
        GridPane newsPane = new GridPane();

        newsPane.addRow(0, newsLbl);
        newsPane.addRow(1, newsArea);
        newsPane.setAlignment(Pos.CENTER);

        RowConstraints rowNews = new RowConstraints();
        rowNews.setMaxHeight(75);

        contentBox.getChildren().addAll(stockInfo, newsPane);

        main.setCenter(contentBox);

        //set center
        //set Bottem
        Button backBtn = new Button("Back");
        HBox hboxBtn = new HBox(backBtn);

        hboxBtn.setAlignment(Pos.BOTTOM_RIGHT);
        backBtn.setOnAction(e -> {

            setHome(main, primaryStage);
            searchBox.setVisible(false);
            backBtn.setVisible(false);

        });
        main.setBottom(hboxBtn);
        Button addtoPortfolio = new Button("add to portfolio");
        searchBox.getChildren().add(addtoPortfolio);

        if (selectedStock != null) {
            Stock stock = selectedStock;
            descriptionInfo.setText(stock.getLong_description());
            companyNameInfo.setText(stock.getName());
            tickerInfo.setText(stock.getTicker());
            openPriceInfo.setText("$" + Double.toString(stock.getPrice().getOpen()));
            closePriceInfo.setText("$" + Double.toString(stock.getPrice().getClose()));
            industryInfo.setText(stock.getIndustry_category());

            sectorInfo.setText(stock.getSector());
            countryInfo.setText(stock.getHq_country());

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

            Stock stock = new Stock(searchTxtFld.getText());
            stock.connectAndFetchNews();

            descriptionInfo.setText(stock.getLong_description());
            companyNameInfo.setText(stock.getName());
            tickerInfo.setText(stock.getTicker());
            openPriceInfo.setText("$" + Double.toString(stock.getPrice().getOpen()));
            closePriceInfo.setText("$" + Double.toString(stock.getPrice().getClose()));
            industryInfo.setText(stock.getIndustry_category());

            sectorInfo.setText(stock.getSector());
            countryInfo.setText(stock.getHq_country());

            ArrayList<StockNews> stockNews = stock.getNews();
            Hyperlink link = new Hyperlink();
            TextField t = new TextField();

            newsArea.getItems().setAll(stockNews);
            
            addtoPortfolio.setOnAction(event -> {
                Account account = new Account();
                if (liveAccount.getPortfolio() == null) {
                    liveAccount.setPortfolio(new Portfolio());
                    liveAccount.getPortfolio().setStock(new ArrayList<>());
                    liveAccount.getPortfolio().addStock(stock);
                    account.save(liveAccount, accountIndex);

                } else {
                    liveAccount.getPortfolio().addStock(stock);
                    account.save(liveAccount, accountIndex);
                }
            });

        });

    }
}
