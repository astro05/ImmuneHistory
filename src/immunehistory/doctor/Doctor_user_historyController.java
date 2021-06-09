/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.doctor;

import com.jfoenix.controls.JFXListView;
import static immunehistory.ImmuneHistory.stage;
import immunehistory.lab_test.Lab_test_report_showController;
import immunehistory.labib_constants.Constants;
import static immunehistory.labib_constants.Constants.previous_page;
import immunehistory.labib_database.Database;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class Doctor_user_historyController implements Initializable {

    @FXML
    private JFXListView<String> duh_col1;
    @FXML
    private JFXListView<String> duh_col2;
    @FXML
    private JFXListView<String> duh_col3;
    @FXML
    private JFXListView<String> duh_col4;
    @FXML
    private JFXListView<String> duh_col5;
    @FXML
    private JFXListView<String> duh_col6;
    @FXML
    private JFXListView<String> duh_col7;
    @FXML
    private JFXListView<String> duh_col8;
    @FXML
    private JFXListView<String> aph_col1;
    @FXML
    private JFXListView<String> aph_col2;
    @FXML
    private JFXListView<String> aph_col3;
    @FXML
    private JFXListView<String> aph_col4;
    @FXML
    private JFXListView<String> aph_col5;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //User details
        show_user_details();
        
        show_appointment_details();

    }    
    
    
    
    public void show_user_details(){
        ObservableList<String> user_name = FXCollections.observableArrayList ();
        
        ObservableList<String> age = FXCollections.observableArrayList ();
        ObservableList<String> gender = FXCollections.observableArrayList ();
        ObservableList<String> date_of_birth = FXCollections.observableArrayList ();
        ObservableList<String> phone = FXCollections.observableArrayList ();
        
        ObservableList<String> email = FXCollections.observableArrayList ();
        ObservableList<String> address = FXCollections.observableArrayList ();
        ObservableList<String> zip_code = FXCollections.observableArrayList ();
         
        System.out.println("running");
        
        ResultSet result = Database.select_query("SELECT * FROM appointment INNER JOIN user_table ON appointment.user_id = user_table.user_id WHERE "
                + "appointment.consult_id = '"+Constants.user_history_consult_id+"' AND appointment.ap_sl_no = '"+Constants.user_history_ap_sl_no+"' ");
        try {
            while( result.next() ){
                System.out.println("running");
                user_name.add( result.getString("user_name") ); 
                age.add( result.getString("age") );
                gender.add( result.getString("gender") );
                date_of_birth.add( result.getString("date_of_birth") );
                phone.add( String.valueOf(result.getInt("phone")) );
                email.add( result.getString("email") );
                
                address.add( result.getString("address") );
                zip_code.add( result.getString("zip_code") );
                
                Constants.user_history_user_id = result.getInt( "user_Id" );
                                       
            }
            
            duh_col1.setItems(user_name);
            duh_col2.setItems(age);
            duh_col3.setItems(gender);
            duh_col4.setItems(date_of_birth);
            duh_col5.setItems(phone);
            duh_col6.setItems(email);
            duh_col7.setItems(address);
            duh_col8.setItems(zip_code);
            
        } catch (SQLException ex) {
            Logger.getLogger(Lab_test_report_showController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void show_appointment_details(){
        ObservableList<String> appointment_sl_no = FXCollections.observableArrayList ();
        
        ObservableList<String> consult_id = FXCollections.observableArrayList ();
        ObservableList<String> date_of_appointment_taken = FXCollections.observableArrayList ();
        ObservableList<String> date_of_consult = FXCollections.observableArrayList ();
        ObservableList<String> appointment_taken = FXCollections.observableArrayList ();
        
        ObservableList<String> show_medications = FXCollections.observableArrayList ();
        ObservableList<String> address = FXCollections.observableArrayList ();
        ObservableList<String> zip_code = FXCollections.observableArrayList ();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);

        
        ResultSet result = Database.select_query("SELECT DISTINCT a.ap_sl_no, a.consult_id, a.date_of_appointment_taken, a.date_of_consult,  ( SELECT DATEDIFF( DAY, a.date_of_consult, '"+currentDate+"' ) ) as appointment_taken_days \n" +
"FROM appointment a INNER JOIN consult c ON a.consult_id = c.consult_id\n" +
"INNER JOIN user_table u ON a.user_id = u.user_id\n" +
"INNER JOIN prescription p ON a.ap_sl_no = p.ap_sl_no\n" +
"WHERE c.doctor_id = '"+Constants.doctor_id+"'  AND u.user_id = '"+Constants.user_history_user_id+"' ");
        try {
            while( result.next() ){
                appointment_sl_no.add( result.getString("ap_sl_no") ); 
                consult_id.add( result.getString("consult_id") );
                date_of_appointment_taken.add( result.getString("date_of_appointment_taken") );
                date_of_consult.add( result.getString("date_of_consult") );
                appointment_taken.add( result.getString("appointment_taken_days") );
                
                
                
//                symtoms.add( result.getString("symptoms")  );
                show_medications.add( "Show Medications" );                             
            }
         

            
            aph_col1.setItems(appointment_sl_no);
            aph_col2.setItems(consult_id);
            aph_col3.setItems(date_of_appointment_taken);
            aph_col4.setItems(appointment_taken);
//            aph_col5.setItems(symtoms);
            aph_col5.setItems(show_medications);
            
            
            
            
//            aph_col6.setItems(email);
//            aph_col7.setItems(address);
//            aph_col8.setItems(zip_code);
            
        } catch (SQLException ex) {
            Logger.getLogger(Lab_test_report_showController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void show_medications(MouseEvent event) throws Exception {
           
           System.out.println( aph_col5.getSelectionModel().getSelectedIndex() );
           System.out.println( aph_col1.getItems().get( aph_col5.getSelectionModel().getSelectedIndex()  ) );
           
           Constants.user_history_medication_ap_sl_no = Integer.valueOf( aph_col1.getItems().get( aph_col5.getSelectionModel().getSelectedIndex()  ) );
           Constants.user_history_medication_consult_id = Integer.valueOf( aph_col2.getItems().get( aph_col5.getSelectionModel().getSelectedIndex()  ) );
           
           aph_col5.getSelectionModel().clearSelection();
           
            Parent root = FXMLLoader.load(getClass().getResource("doctor_show_medications.fxml"));
            Scene scene = new Scene(root);
            previous_page.push( stage.getScene() );
            stage.setScene(scene);
            stage.show(); 
           
        
    }

    @FXML
    private void back(MouseEvent event) {
        stage.setScene( previous_page.pop() );
        stage.show();
    }
    
    
    
}
