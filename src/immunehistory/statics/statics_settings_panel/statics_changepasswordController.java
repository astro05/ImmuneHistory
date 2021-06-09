/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.statics.statics_settings_panel;


import com.jfoenix.controls.JFXPasswordField;
import immunehistory.ImmuneHistory;
import immunehistory.statics.login_registration.Statics_registrationController;
import static immunehistory.statics.login_registration.Statics_registrationController.statics_email;
import static immunehistory.statics.login_registration.Statics_registrationController.statics_password;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author RIfat
 */
public class statics_changepasswordController implements Initializable {

     private Connection con = null;
    private PreparedStatement pst = null;
    
    @FXML
    private AnchorPane statics_changepassword_id;
    @FXML
    private JFXPasswordField statics_Newpassword_id;
    @FXML
    private JFXPasswordField statics_reNewpass_id;
    @FXML
    private Label lblchangePassError;



    

     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       con =dba.DBConnection.immunehistoryConnection();
      
    }

    @FXML
    private void statics_changePasswordOnAction(ActionEvent event) throws IOException {
        if(statics_Newpassword_id.getText().equals(statics_reNewpass_id.getText())){
        
             try {
       
       String update ="Update statics_user set statics_user_password =? where statics_user_email =?";
        
                 pst = con.prepareStatement(update);
            
       pst.setString(1,statics_Newpassword_id.getText());
       pst.setString(2,statics_email);
       
       int i = pst.executeUpdate();
       if(i==1){
         Alert alert = new Alert(Alert.AlertType.INFORMATION.CONFIRMATION,"Password Update successfully",ButtonType.OK);
         alert.show();
         
//         Parent home = FXMLLoader.load(getClass().getResource("statics_login.fxml"));
//         Scene scene = new Scene(home);
//         statics_otp_id.getChildren().setAll(home);

//           FXMLLoader fXMLLoader = new FXMLLoader();
//              fXMLLoader.setLocation(getClass().getResource("/immunehistory/statics/statics_home/statics_home.fxml"));
//        
//                Stage stage = new Stage();
//                Scene scene = new Scene(fXMLLoader.load());
//                stage.setScene(scene);
//                stage.setTitle("Statics");
//                ImmuneHistory.stage.close();
//                stage.show();


     
         
       }
        } catch (SQLException ex) {
                 Logger.getLogger(Statics_registrationController.class.getName()).log(Level.SEVERE, null, ex);
        Alert alert = new Alert(Alert.AlertType.INFORMATION.WARNING,"Something Wrong",ButtonType.OK);
         alert.show();     
        }
       
            
        
        }else{
         lblchangePassError.setText("Password not Matched");
        }
        
        
    }

  
    
}