/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.statics.login_registration;

import static immunehistory.statics.login_registration.Statics_registrationController.statics_email;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import immunehistory.ImmuneHistory;
import immunehistory.home;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joyultimates
 */
public class Statics_login implements Initializable  {

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs;
    @FXML
    private AnchorPane statics_login_id;
    @FXML
    private JFXTextField statics_login_email_id;
    @FXML
    private JFXPasswordField statics_login_pass_id;
    @FXML
    private Label lblLoginError;
    @FXML
    private JFXButton statics_login_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         con =dba.DBConnection.immunehistoryConnection();
         
    }    

    @FXML
    private void statics_login_signup_id(ActionEvent event) throws IOException {
   
         Parent home = FXMLLoader.load(getClass().getResource("statics_registration.fxml"));
     Scene scene = new Scene(home);
     statics_login_id.getChildren().setAll(home);
    }

    @FXML
    private void statics_login_home_id(MouseEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("/immunehistory/home.fxml"));
     Scene scene = new Scene(home);
     statics_login_id.getChildren().setAll(home);
    }

    @FXML
    private void statics_login_btn(ActionEvent event) throws IOException {
          
         if(statics_login_pass_id.getText().equals(getPassword()) && statics_login_email_id.getText().equals(getEmail()) && !statics_login_email_id.getText().equals("")){
              statics_email = statics_login_email_id.getText();
               
              FXMLLoader fXMLLoader = new FXMLLoader();
              fXMLLoader.setLocation(getClass().getResource("/immunehistory/statics/statics_home/statics_home.fxml"));
        
                Stage stage = new Stage();
                Scene scene = new Scene(fXMLLoader.load());
                stage.setScene(scene);
                stage.setTitle("Statics");
                ImmuneHistory.stage.close();
                Stage statics_login = (Stage) statics_login_id.getScene().getWindow();
        statics_login.close();
        
                stage.show();
          
         }else{
         lblLoginError.setText("Invalid Attempt");
         }
        
    }
 private String getEmail(){
  String email = "";
        try {
            pst= con.prepareStatement("select statics_user_email from statics_user where statics_user_email=?");
            pst.setString(1,statics_login_email_id.getText());
            rs = pst.executeQuery();
            if(rs.next()){
            email = rs.getString(1); 
            }
            rs.close();
              
        } catch (SQLException ex) {
            Logger.getLogger(Statics_login.class.getName()).log(Level.SEVERE, null, ex);
        }
       
return email;
  
 }  
private String getPassword(){
  String password = "";
        try {
            pst= con.prepareStatement("select statics_user_password from statics_user where statics_user_password=?");
            pst.setString(1,statics_login_pass_id.getText());
            rs = pst.executeQuery();
            if(rs.next()){
            password = rs.getString(1); 
            }
            rs.close();
              
        } catch (SQLException ex) {
            Logger.getLogger(Statics_login.class.getName()).log(Level.SEVERE, null, ex);
        }
return password;
  
 }    

   
    
   
}
