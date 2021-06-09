/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.statics.login_registration;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static immunehistory.statics.login_registration.Statics_otpController.otpmatched;
import static immunehistory.statics.login_registration.Statics_registrationController.statics_password;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import static javafx.scene.control.ButtonType.OK;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author joyultimates
 */
public class Statics_registrationController implements Initializable {

    private Connection con = null;
    private PreparedStatement pst = null;
    public static String statics_email;
    public static String statics_password;
    
    @FXML
    private AnchorPane statics_registration_id;
    @FXML
    private Label lblEmailError;
    @FXML
    private Label lblPassError;
    @FXML
    private JFXTextField statics_email_id;
    @FXML
    private JFXPasswordField statics_password_id;
    @FXML
    private JFXPasswordField statics_confirmpass_id;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     con =dba.DBConnection.immunehistoryConnection();
    }    

    @FXML
    private void statics_registration_login_id(MouseEvent event) throws IOException {
         Parent home = FXMLLoader.load(getClass().getResource("statics_login.fxml"));
     Scene scene = new Scene(home);
     statics_registration_id.getChildren().setAll(home);
    }

    @FXML
    private void statics_registration_home_id(MouseEvent event) throws IOException {
        
        Parent home = FXMLLoader.load(getClass().getResource("/immunehistory/home.fxml"));
     Scene scene = new Scene(home);
     statics_registration_id.getChildren().setAll(home);
    }

    @FXML
    private void statics_registrationOnAction(ActionEvent event) throws IOException {
         boolean isValidEmail = validation.EmailValidation.isValidEmail(statics_email_id, lblEmailError,"Invalid Email!!");
         boolean isPasswordMatched = validation.EmailValidation.isPasswordMatched(statics_password_id,statics_confirmpass_id,lblPassError,"Password not Matched !!");
         if(isValidEmail &&  isPasswordMatched){
                  
                    lblEmailError.setText("");
                    lblPassError.setText("");
                   
                    statics_email= statics_email_id.getText();
                   statics_password = statics_password_id.getText();
                   
                  Parent home = FXMLLoader.load(getClass().getResource("statics_otp.fxml"));
                  Scene scene = new Scene(home);
                  statics_registration_id.getChildren().setAll(home);
             
                  
             
   
          
                        
         }
            
     } 
    
    
}
