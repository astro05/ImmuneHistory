/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.statics.statics_drug_panel;

import immunehistory.ImmuneHistory;
import immunehistory.statics.login_registration.Statics_login;
import immunehistory.statics.login_registration.Statics_registrationController;
import static immunehistory.statics.login_registration.Statics_registrationController.statics_email;
import static immunehistory.statics.login_registration.Statics_registrationController.statics_password;
import static java.awt.SystemColor.window;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joyultimates
 */
public class Statics_add_new_drugController implements Initializable {

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs;
    
    @FXML
    private TextField statics_add_new_generic_name_field;
    @FXML
    private TextArea statics_add_new_generic_note_field;
    @FXML
    private Label lblDataError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        con =dba.DBConnection.immunehistoryConnection();
    }    


    @FXML
    private void saveOnAction(ActionEvent event) {
        
         if(statics_add_new_generic_name_field.getText().toUpperCase().equals(getData())){
           
             lblDataError.setText("Already Added");
         }else{
             
             try {
       
       String insert ="insert into medicine_generic_name(medicine_generic_name,medicine_generic_note) values(UPPER(?),UPPER(?))" ;
        
                 pst = con.prepareStatement(insert);
            
       pst.setString(1,statics_add_new_generic_name_field.getText());
       pst.setString(2,statics_add_new_generic_note_field.getText());
       
       int i = pst.executeUpdate();
       if(i==1){
         Alert alert = new Alert(Alert.AlertType.INFORMATION.CONFIRMATION,"Added successfully",ButtonType.OK);
         alert.show();
         statics_add_new_generic_name_field.setText("");
         statics_add_new_generic_note_field.setText("");
       }
        } catch (SQLException ex) {
                 Logger.getLogger(Statics_add_new_drugController.class.getName()).log(Level.SEVERE, null, ex);
        Alert alert = new Alert(Alert.AlertType.INFORMATION.WARNING,"Something Wrong",ButtonType.OK);
         alert.show();     
        }
        
         }
    }
    
     private String getData(){
  String insertData = "";
        try {
            pst= con.prepareStatement("select medicine_generic_name "
                    + "from medicine_generic_name where medicine_generic_name=?");
            pst.setString(1,statics_add_new_generic_name_field.getText().toUpperCase());
            rs = pst.executeQuery();
            if(rs.next()){
            insertData = rs.getString(1); 
            }
            rs.close();
              
        } catch (SQLException ex) {
            Logger.getLogger(Statics_add_new_drugController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
return insertData;
  
 } 
    
}
