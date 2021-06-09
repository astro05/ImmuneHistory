/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.doctor;

import static immunehistory.ImmuneHistory.stage;
import immunehistory.lab_test.Lab_test_report_showController;
import static immunehistory.labib_constants.Constants.previous_page;
import immunehistory.labib_database.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class Write_prescriptionController implements Initializable {

    @FXML
    private Pane id_pane;
    @FXML
    private Pane data_pane;
    @FXML
    private AnchorPane main_anchor_pane;
    @FXML
    private ScrollPane main_parent_scroll;
    @FXML
    private Button add_more_button;
    @FXML
    private Label error_ap_sl_no;
    @FXML
    private Label error_consult_id;
    @FXML
    private ComboBox<String> symptoms;
    @FXML
    private ComboBox<String> test_name;
    @FXML
    private ComboBox<String> medicine_name;
    @FXML
    private ComboBox<String> test_center;
    @FXML
    private TextField dosage;
    @FXML
    private TextField dosage_duration;
    @FXML
    private TextField remarks;
    @FXML
    private Label error_symptoms;
    @FXML
    private Label error_medicine_name;
    @FXML
    private Label error_test_name;
    @FXML
    private Label error_test_center;
    @FXML
    private Label error_dosage;
    @FXML
    private Label error_dosage_duration;
    @FXML
    private Label error_remarks;

    /**
     * Initializes the controller class.
     */
    
    Thread x;
    @FXML
    private TextField ap_sl_no;
    @FXML
    private TextField consult_id;
    
    ObservableList<String> symptoms_array = FXCollections.observableArrayList ();
    ObservableList<Integer> symptoms_id_array = FXCollections.observableArrayList ();

    ObservableList<String> test_name_array = FXCollections.observableArrayList ();
    ObservableList<String> medicine_name_array = FXCollections.observableArrayList ();

    ObservableList<Integer> test_id_array = FXCollections.observableArrayList ();
    ObservableList<Integer> medicine_id_array = FXCollections.observableArrayList ();

    ObservableList<String> test_center_array = FXCollections.observableArrayList ();
    ObservableList<Integer> test_center_id_array = FXCollections.observableArrayList ();
    @FXML
    private Button add_more_button1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        test_name.setDisable(true);
        medicine_name.setDisable(true);
        test_center.setDisable(true);

        
        
        ResultSet result = Database.select_query( "SELECT * FROM symptom" );
        
        ResultSet result4 = Database.select_query( "SELECT * FROM symptom" );


        try {
            while( result.next() ){

                symptoms_array.add( result.getString("symptom_name") );
                symptoms_id_array.add( result.getInt("symptom_id") );
                symptoms.setItems(symptoms_array );

            }   
        } catch (SQLException ex) {
            Logger.getLogger(Lab_test_report_showController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       
        
    }    

    @FXML
    private void add_more(MouseEvent event) throws Exception {
        
        String ap = ap_sl_no.getText();
        String cid = consult_id.getText();
        String sym = symptoms.getSelectionModel().getSelectedItem();
        String tn = test_name.getSelectionModel().getSelectedItem();
        
        String mn = medicine_name.getSelectionModel().getSelectedItem();
        int mid = medicine_id_array.get( medicine_name.getSelectionModel().getSelectedIndex()  );
        
        String tc = test_center.getSelectionModel().getSelectedItem();
        String dos = dosage.getText();
        String dosd = dosage_duration.getText();
        String rm = remarks.getText();
        
        System.out.println(ap+" "+sym+" "+tn+" "+mid+" "+mn+" "+dos+" "+rm);
        
        Database.insert_query( "INSERT INTO prescription VALUES " +
"        ( '"+ap+"', '"+sym+"', '"+tn+"', '"+mid+"', '"+dos+"', CURRENT_TIMESTAMP, DATEADD( DAY, "+dosd+", CURRENT_TIMESTAMP  ), '"+rm+"')" );              
            
        
         
                        
    }

    @FXML
    private void selected_symptoms(ActionEvent event) {
        
        
        
        ResultSet result2 = Database.select_query( "SELECT DISTINCT test_name FROM lab_test" );
        ResultSet result3 = Database.select_query( "SELECT * FROM medicine_generic_name" );
        
        try {
            while( result2.next() ){

                test_name_array.add( result2.getString("test_name") );
//                symptoms_id_array.add( result.getInt("symptom_id") );
                test_name.setItems(test_name_array );

            }   
        } catch (SQLException ex) {
            Logger.getLogger(Lab_test_report_showController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while( result3.next() ){

                medicine_name_array.add( result3.getString("medicine_generic_name") );
                medicine_id_array.add( result3.getInt( "medicine_generic_id" ) );
                medicine_name.setItems(medicine_name_array );

            }   
        } catch (SQLException ex) {
            Logger.getLogger(Lab_test_report_showController.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        test_name.setDisable(false);
        medicine_name.setDisable(false);
        
    }

    @FXML
    private void selected_test_name(ActionEvent event) {
        
        
        
        ResultSet result4 = Database.select_query( "SELECT DISTINCT test_center FROM lab_test" );
        
        try {
            while( result4.next() ){

                test_center_array.add( result4.getString("test_center") );
//                symptoms_id_array.add( result.getInt("symptom_id") );
                test_center.setItems(test_center_array );

            }   
        } catch (SQLException ex) {
            Logger.getLogger(Lab_test_report_showController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        test_center.setDisable(false);
    }

    @FXML
    private void back(MouseEvent event) {
        stage.setScene( previous_page.pop() );
        stage.show();
    }
    
}
