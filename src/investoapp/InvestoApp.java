package investoapp;


import java.io.IOException;
import java.net.MalformedURLException;
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
import org.json.simple.parser.ParseException;

/**
 *
 * @author tesqie
 *
 */
public class InvestoApp extends Application {

    TextField txtUsername;
    TextField txtPassword;
    TextField txtEmail;

    public static void main(String[] args) throws MalformedURLException, IOException, ParseException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        
//        Account account = new Account();
//        account.newAccount();
        
        
        
        BorderPane main = new BorderPane();
        main.setPadding(new Insets(10));

        Scene scene = new Scene(main, 500, 500);
        scene.setFill(Color.ANTIQUEWHITE);

        setLogin(main);
//       setCreateAccount(main);
        // setHome(main);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Investo Application");
        primaryStage.show();

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

        txtPassword = new TextField();
        txtPassword.setPrefColumnCount(10);

        inputPane.addRow(0, new Label("Username"), txtUsername);
        inputPane.addRow(1, new Label("Password"), txtPassword);
        centerPane.getChildren().add(inputPane);

        Button login = new Button("Login");
        Button password = new Button("Password");

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

        txtPassword = new TextField();
        txtPassword.setPrefColumnCount(10);

        txtEmail = new TextField();
        txtEmail.setPrefColumnCount(10);

        inputPane.addRow(0, new Label("Username"), txtUsername);
        inputPane.addRow(1, new Label("Password"), txtPassword);
        inputPane.addRow(2, new Label("Email"), txtEmail);
        centerPane.getChildren().add(inputPane);

        Button login = new Button("Create");
        Button password = new Button("Cancel");

        HBox buttonPane = new HBox(10, login, password);
        buttonPane.setAlignment(Pos.CENTER);

        centerPane.getChildren().add(buttonPane);

        main.setCenter(centerPane);

    }

    public void setHome(BorderPane main) {
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
}
