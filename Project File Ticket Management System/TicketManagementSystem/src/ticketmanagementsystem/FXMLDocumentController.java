/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package ticketmanagementsystem;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static jdk.nashorn.internal.objects.NativeDebug.getClass;

/**
 *
 * @author Shipon
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private FontAwesomeIconView close;

    //CREATE A DATABASE FIRST 
    // DATABASE TOOLS
   
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private double x= 0;
    private double y = 0;

    @FXML
    public void login() {
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        connect = Database.connectDb();

        Alert alert;

        try {
            //  CHECK USERNAME & PASSWORD IS FILL OR NOT 

            if (username.getText().isEmpty() || password.getText().isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Fill all blank fields");
                alert.showAndWait();
            }
            
            else 
            {

                prepare = connect.prepareStatement(sql);
                // CALLED THE FXID FROM USERNAME & PASSWORD 
                prepare.setString(1, username.getText());
                prepare.setString(2, password.getText());
                result =prepare.executeQuery();
                
                if (result.next()){
                    //THEN WE WILL PROCEED TO DASHBOARD FORM :
                    alert =new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();
                    
                    
                    login.getScene().getWindow().hide();
                    
                    
                    
                    //LINK TO DASHBOARD FXML FILE FOR NEW PAGE  
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                    
                    Stage stage =new Stage();
                    Scene scene = new Scene(root);
                    
                    root.setOnMousePressed((MouseEvent event) ->{
                    x = event.getSceneX();
                    y = event.getSceneY();
                });
                    
                    root.setOnMouseDragged((MouseEvent event) ->{
                        stage.setX(event.getSceneX()-x);
                        stage.setY(event.getSceneY()-y);
                    });
                    
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                    
                    
                }
                else {
                    // IF INCORRECT THE DATA USER GIVE
                    alert =new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                    
                    
                }

            }

        }
        catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

    // for close the file 
    @FXML
    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
