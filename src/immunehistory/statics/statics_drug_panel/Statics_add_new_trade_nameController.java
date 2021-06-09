/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.statics.statics_drug_panel;

import immunehistory.statics.login_registration.Statics_registrationController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.controlsfx.control.PrefixSelectionComboBox;

/**
 * FXML Controller class
 *
 * @author joyultimates
 */
public class Statics_add_new_trade_nameController implements Initializable {

      private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private String GenericId;
    private ObservableList<Statics_add_new_trade_name>Statics_add_new_trade_name;
    
    
    @FXML
    private TextField statics_add_new_medicine_local_name_field;
    @FXML
    private PrefixSelectionComboBox<Statics_add_new_trade_name> statics_select_generic_name;
    @FXML
    private Label lblDataError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        con =dba.DBConnection.immunehistoryConnection();
        Statics_add_new_trade_name = FXCollections.observableArrayList();
          try {
              pst = con.prepareStatement("select medicine_generic_id,medicine_generic_name from medicine_generic_name");
               rs = pst.executeQuery();
               while(rs.next()){
                
                   Statics_add_new_trade_name.add(new Statics_add_new_trade_name(rs.getString(1),rs.getString(2)));
                   
               }
            statics_select_generic_name.setItems(Statics_add_new_trade_name);
               
          } catch (SQLException ex) {
              Logger.getLogger(Statics_add_new_trade_nameController.class.getName()).log(Level.SEVERE, null, ex);
          }
          statics_select_generic_name.setConverter(new StringConverter<Statics_add_new_trade_name>() {
            @Override
            public String toString(immunehistory.statics.statics_drug_panel.Statics_add_new_trade_name object) {
                  return object.getGenericName();
            }

            @Override
            public immunehistory.statics.statics_drug_panel.Statics_add_new_trade_name fromString(String string) {
                  return null;           
            }
            
        });
        statics_select_generic_name.valueProperty().addListener((obs, oldVaue, newValue) ->{
                if(newValue != null){
                    /*Alert alter = new Alert(Alert.AlertType.INFORMATION, newValue.getRoleID(), ButtonType.OK);
                    alter.show();*/
                    GenericId = newValue.getGenericId();
                }
        });
 
       
    }    

    @FXML
    private void saveOnAction(ActionEvent event) {
       
          if(statics_add_new_medicine_local_name_field.getText().toUpperCase().equals(getData())){
           
             lblDataError.setText("Already Added");
         }else{
             
             try {
       
       String insert ="insert into medicine_local_name(medicine_generic_id,medicine_local_name) values(UPPER(?),UPPER(?))" ;
        
                 pst = con.prepareStatement(insert);
            
       pst.setString(1,GenericId);
       pst.setString(2,statics_add_new_medicine_local_name_field.getText());
       
       int i = pst.executeUpdate();
       if(i==1){
         Alert alert = new Alert(Alert.AlertType.INFORMATION.CONFIRMATION,"Added successfully",ButtonType.OK);
         alert.show();
         statics_add_new_medicine_local_name_field.setText("");
       
       }
        } catch (SQLException ex) {
                 Logger.getLogger(Statics_add_new_trade_nameController.class.getName()).log(Level.SEVERE, null, ex);
        Alert alert = new Alert(Alert.AlertType.INFORMATION.WARNING,"Something Wrong",ButtonType.OK);
         alert.show();     
        }
        
         }
    }
    
    
     private String getData(){
  String insertData = "";
        try {
            pst= con.prepareStatement("select medicine_local_name from medicine_local_name where medicine_local_name = UPPER(?)");
            pst.setString(1,statics_add_new_medicine_local_name_field.getText().toUpperCase());
            rs = pst.executeQuery();
            if(rs.next()){
            insertData = rs.getString(1); 
            }
            rs.close();
              
        } catch (SQLException ex) {
            Logger.getLogger(Statics_add_new_trade_nameController.class.getName()).log(Level.SEVERE, null, ex);
        }
         System.out.println(insertData);     
return insertData;
  



 } 
    
    
}
