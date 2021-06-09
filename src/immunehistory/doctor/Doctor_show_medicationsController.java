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
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class Doctor_show_medicationsController implements Initializable {

    @FXML
    private JFXListView<String> col1;
    @FXML
    private JFXListView<String> col2;
    @FXML
    private JFXListView<String> col3;
    @FXML
    private JFXListView<String> col4;
    @FXML
    private JFXListView<String> col5;
    @FXML
    private JFXListView<String> col6;
   
    
    @FXML
    private JFXListView<String> col7;
    @FXML
    private JFXListView<String> col8;
    @FXML
    private JFXListView<String> col9;
    @FXML
    private JFXListView<String> col11;
    @FXML
    private JFXListView<String> col12;
    @FXML
    private JFXListView<String> col13;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        show_medications_details();
        show_test_details();
    }    
 
    public void show_medications_details(){
        
        ObservableList<String> symptoms = FXCollections.observableArrayList ();  
        ObservableList<String> test_name = FXCollections.observableArrayList ();
        ObservableList<String> medicine_generic_name = FXCollections.observableArrayList ();
        ObservableList<String> dosage = FXCollections.observableArrayList ();
        ObservableList<String> dosage_duration_days = FXCollections.observableArrayList ();
        ObservableList<String> remarks = FXCollections.observableArrayList ();
       
 
        ResultSet result = Database.select_query("SELECT p.symptoms, p.test_name, (SELECT medicine_generic_name FROM medicine_generic_name WHERE medicine_generic_id = p.medicine_id) as medicine_generic_name ,\n" +
"p.dosage, (SELECT DATEDIFF( DAY, p.dosage_taking_date, p.dosage_ending_date ) ) as dosage_duration_days , p.remarks \n" +
"FROM appointment a INNER JOIN consult c ON a.consult_id = c.consult_id\n" +
"INNER JOIN user_table u ON a.user_id = u.user_id\n" +
"INNER JOIN prescription p ON a.ap_sl_no = p.ap_sl_no\n" +
"WHERE c.doctor_id = '"+Constants.doctor_id+"'  AND u.user_id = '"+Constants.user_history_user_id+"' AND a.consult_id = '"+Constants.user_history_medication_consult_id+"'"
        + " AND p.ap_sl_no = '"+Constants.user_history_medication_ap_sl_no+"' ");
        
        
        try {
            while( result.next() ){
                symptoms.add( result.getString("symptoms") ); 
                test_name.add( result.getString("test_name") );
                medicine_generic_name.add( result.getString("medicine_generic_name") );
                dosage.add( result.getString("dosage") );
                dosage_duration_days.add( result.getString("dosage_duration_days") );   
                remarks.add( result.getString("remarks") );   
            }
         
            
            
            col1.setItems(symptoms);
            col2.setItems(test_name);
            col3.setItems(medicine_generic_name);
            col4.setItems(dosage);
            col5.setItems(dosage_duration_days);
            col6.setItems(remarks);
            
        } catch (SQLException ex) {
            Logger.getLogger(Lab_test_report_showController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
public void show_test_details(){

        
        ObservableList<String> ref_id = FXCollections.observableArrayList ();
        ObservableList<String> test_name = FXCollections.observableArrayList ();
        ObservableList<String> test_center = FXCollections.observableArrayList ();
        ObservableList<String> date_of_test = FXCollections.observableArrayList ();
        ObservableList<String> date_of_result = FXCollections.observableArrayList ();
        ObservableList<String> report = FXCollections.observableArrayList ();
       
 
        ResultSet result = Database.select_query("SELECT DISTINCT l.ref_id, l.test_name , l.test_center, l.date_of_test, l.date_of_result, l.report\n" +
"FROM appointment a INNER JOIN consult c ON a.consult_id = c.consult_id\n" +
"INNER JOIN user_table u ON a.user_id = u.user_id\n" +
"INNER JOIN lab_test l ON l.consult_id = a.consult_id\n" +
"WHERE c.doctor_id =  1 AND u.user_id = 1 AND l.consult_id = '"+Constants.user_history_medication_consult_id+"'"
        + " AND a.ap_sl_no = '"+Constants.user_history_medication_ap_sl_no+"' ");
        
        
        try {
            while( result.next() ){
                ref_id.add( result.getString("ref_id") ); 
                test_name.add( result.getString("test_name") );
                test_center.add( result.getString("test_center") );
                date_of_test.add( result.getString("date_of_test") );
                date_of_result.add( result.getString("date_of_result") );   
                report.add( result.getString("report") );   
            }
         
            
            
            col7.setItems(ref_id);
            col8.setItems(test_name);
            col9.setItems(test_center);
            col11.setItems(date_of_test);
            col12.setItems(date_of_result);
            col13.setItems(report);
            
        } catch (SQLException ex) {
            Logger.getLogger(Lab_test_report_showController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void back(MouseEvent event) {
        stage.setScene( previous_page.pop() );
        stage.show();
    }



}

