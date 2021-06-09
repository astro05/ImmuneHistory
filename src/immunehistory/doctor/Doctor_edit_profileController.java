/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.doctor;

import static immunehistory.ImmuneHistory.stage;
import immunehistory.labib_constants.Constants;
import static immunehistory.labib_constants.Constants.previous_page;
import immunehistory.labib_database.Database;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class Doctor_edit_profileController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private Button done;
    @FXML
    private Label error_name;
    @FXML
    private TextField mobile;
    @FXML
    private Label error_mobile;
    @FXML
    private Label error_password;
    @FXML
    private PasswordField password;
    @FXML
    private Label error_re_enter_password;
    @FXML
    private PasswordField re_enter_password;
    @FXML
    private TextField hospital;
    @FXML
    private Label error_hospital;
    @FXML
    private TextField address;
    @FXML
    private Label error_address;
    @FXML
    private TextField zipcode;
    @FXML
    private Label error_zipcode;
    @FXML
    private ComboBox<String> degree;
    @FXML
    private ComboBox<String> category;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<String> degree_options = 
        FXCollections.observableArrayList(
            "MBBS",
            "MD",
            "DO",
            "Student"
        );
        degree.setItems(degree_options);
        
        ObservableList<String> category_options = 
        FXCollections.observableArrayList(
            "Diabetics and Hypertension",
            "Medicine",
            "Liver",
            "Food and Nutritionist",
            "Cancer",
            "Cardiologists",
            "Critical Care Medicine Specialists",
            "Endocrinologists",
            "Gastroenterologists"
        );
        category.setItems(category_options);
        
        try {
            load_data();
        } catch (Exception ex) {
            Logger.getLogger(Doctor_edit_profileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void done_button(MouseEvent event) {
        
            String n = name.getText();
            String m = mobile.getText();
            String p = password.getText();
            String rp = re_enter_password.getText();
            String hos = hospital.getText();
            String add = address.getText();
            String zip = zipcode.getText();            
            String deg = degree.getValue();
            String cat = category.getValue();
            
            Database.insert_query("UPDATE doctor SET "
            + " doctor_name = '"+n+"',"
            + " phone = '"+m+"', "
            + " password = '"+p+"', "
            + " hospital = '"+hos+"', "
            + " address = '"+add+"', "
            + " zipcode = '"+zip+"', "
            + " degree = '"+deg+"', "
            + " category = '"+cat+"' WHERE doctor_id = '"+Constants.doctor_id+"' " );
            

    }
    
    public void load_data() throws Exception {
        ResultSet result = Database.select_query( "SELECT * FROM doctor WHERE doctor_id = '"+Constants.doctor_id+"' " );
        
        while( result.next() ){
            
            name.setText( result.getString("doctor_name") );
            mobile.setText( String.valueOf( result.getInt("phone") ) );
            password.setText( result.getString("password") );
            re_enter_password.setText( result.getString("password") );
            hospital.setText( result.getString("hospital") );
            address.setText( result.getString("address") );
            zipcode.setText( String.valueOf( result.getInt("zipcode")) );
            
            degree.setValue(result.getString("degree") );
            category.setValue( result.getString("category") );
        }
        
        
    }
    
    @FXML
    private void back(MouseEvent event) {
        stage.setScene( previous_page.pop() );
        stage.show();
    }
    
}
