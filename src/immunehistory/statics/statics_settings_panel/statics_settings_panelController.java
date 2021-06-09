/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.statics.statics_settings_panel;


import static immunehistory.statics.login_registration.Statics_registrationController.statics_email;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
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
public class statics_settings_panelController implements Initializable {

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs;
    @FXML
    private AnchorPane statics_settings_subpanel_id;
    @FXML
    private Label lblstaticsSettingsEmail;

    

     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       con =dba.DBConnection.immunehistoryConnection();
       lblstaticsSettingsEmail.setText(statics_email); 
    }

    @FXML
    private void statics_settings_changePasswordOnAction(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("statics_changepassword.fxml"));
     Scene scene = new Scene(home);
     statics_settings_subpanel_id.getChildren().setAll(home);
    
    }
    
}