
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.sql.*;

public class CustomerInserter extends Application
{
    //Input fields
    private TextField numField;
    private TextField nameField;
    private TextField addrField;
    private TextField cityField;
    private TextField stateField;
    private TextField zipField;
    
    private final static String DB_URL = "jdbc:derby:CoffeeDB";
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage)
    {
        buildGUI(stage);
        
        stage.show();
    }
    
    private void buildGUI(Stage stage) {
        Label numLabel = new Label("Customer Number");
        numField = new TextField();
        Label nameLabel = new Label("Name");
        nameField = new TextField();
        Label addrLabel = new Label("Address");
        addrField = new TextField();
        Label cityLabel = new Label("City");
        cityField = new TextField();
        Label stateLabel = new Label("State");
        stateField = new TextField();
        Label zipLabel = new Label("ZIP Code");
        zipField = new TextField();
        Button addBtn = new Button("Add");
        HBox btnHbox = new HBox(addBtn);
        btnHbox.setAlignment(Pos.CENTER);
        
        addBtn.setOnAction(event -> {
            insertCustomer();
            numField.clear();
            nameField.clear();
            addrField.clear();
            cityField.clear();
            stateField.clear();
            zipField.clear();
        });
        
        VBox mainVBox = new VBox(10, numLabel, numField, nameLabel, nameField,
                                addrLabel, addrField, cityLabel, cityField,
                                stateLabel, stateField, zipLabel, zipField,
                                btnHbox);
        mainVBox.setPadding(new Insets(10));
        Scene scene = new Scene(mainVBox);
        stage.setScene(scene);
    }
    
    private void insertCustomer(){
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            
            String sqlString = "INSERT INTO Customer" + 
                               " VALUES('" + 
                               numField.getText() + "', '" + 
                               nameField.getText()+ "', '" +
                               addrField.getText()+ "', '" +
                               cityField.getText()+ "', '" +
                               stateField.getText()+ "', '" +
                               zipField.getText()+ "')";
            
            System.out.println("DEBUG-------------\n" + sqlString +
                               "\nEND-DEBUG-----------");
            int rows = stmt.executeUpdate(sqlString);
            conn.close();
        }
        catch(SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
