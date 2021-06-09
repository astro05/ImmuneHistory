/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.doctor;

import com.jfoenix.controls.JFXListView;
import immunehistory.lab_test.Lab_test_report_showController;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class Doctor_todays_appointmentsController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
//        ObservableList<Integer> ap_sl_no = FXCollections.observableArrayList ();  
//        ObservableList<Integer> consult_id = FXCollections.observableArrayList ();
//        ObservableList<String> user_name = FXCollections.observableArrayList ();
//        ObservableList<Integer> age = FXCollections.observableArrayList ();
//        ObservableList<String> phone = FXCollections.observableArrayList ();
//        ObservableList<String> email = FXCollections.observableArrayList ();
//        ObservableList<String> date_of_consult = FXCollections.observableArrayList ();
//        
//        ResultSet result = "  ";
//        
//        try {
//            while( result.next() ){
//                symptoms.add( result.getString("symptoms") ); 
//                test_name.add( result.getString("test_name") );
//                medicine_generic_name.add( result.getString("medicine_generic_name") );
//                dosage.add( result.getString("dosage") );
//                dosage_duration_days.add( result.getString("dosage_duration_days") );   
//                remarks.add( result.getString("remarks") );   
//            }
//         
//            
//            
//            col1.setItems(symptoms);
//            col2.setItems(test_name);
//            col3.setItems(medicine_generic_name);
//            col4.setItems(dosage);
//            col5.setItems(dosage_duration_days);
//            col6.setItems(remarks);
//            col7.setItems(remarks);
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(Lab_test_report_showController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }    
    
}
