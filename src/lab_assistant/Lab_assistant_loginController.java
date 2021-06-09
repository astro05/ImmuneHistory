/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_assistant;

import static immunehistory.ImmuneHistory.stage;
import immunehistory.labib_constants.Constants;
import static immunehistory.labib_constants.Constants.previous_page;
import immunehistory.labib_database.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class Lab_assistant_loginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private Label message_label;
    @FXML
    private PasswordField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    public void sign_up_label_click() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("lab_assistant_registration.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void sign_in(MouseEvent event) throws Exception {
        
        String email = this.email.getText();
        String passowrd = this.password.getText();
        
        Constants.assistant_id_logged_in = Database.authenticate_assistant(email, passowrd);
        if( Constants.assistant_id_logged_in != -1 ){
            message_label.setText("Successfully logged in!");
            message_label.setTextFill( Color.web("#00ff00") );
            
            Parent root = FXMLLoader.load(getClass().getResource("lab_assistant_main.fxml"));
            Scene scene = new Scene(root);
            previous_page.push( stage.getScene() );
            stage.setScene(scene);
            stage.show();            
        }else{
            message_label.setText("Invalid email or password!");
            message_label.setTextFill( Color.web("#ff0000") );
        }
        
        
    }

    @FXML
    private void back(MouseEvent event) {
        stage.setScene( previous_page.pop() );
        stage.show();
    }
    
    
}
