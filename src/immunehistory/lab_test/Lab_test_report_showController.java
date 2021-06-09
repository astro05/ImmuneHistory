/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.lab_test;

import com.jfoenix.controls.JFXListView;
import static immunehistory.ImmuneHistory.stage;
import immunehistory.labib_constants.Constants;
import static immunehistory.labib_constants.Constants.previous_page;
import immunehistory.labib_database.Database;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class Lab_test_report_showController implements Initializable {

    @FXML
    private JFXListView<String> col1;
    @FXML
    private JFXListView<Integer> col2;
    @FXML
    private JFXListView<Integer> col3;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> ref_id = FXCollections.observableArrayList ();
        ObservableList<Integer> consult_id = FXCollections.observableArrayList ();
        ObservableList<Integer> assistant_id = FXCollections.observableArrayList ();
        
        ObservableList<String> test_name = FXCollections.observableArrayList ();
        ObservableList<String> report = FXCollections.observableArrayList ();
        ObservableList<String> test_center = FXCollections.observableArrayList ();
        ObservableList<String> date_of_test = FXCollections.observableArrayList ();
        ObservableList<String> date_of_result = FXCollections.observableArrayList ();
       
        ResultSet result = Database.select_query( "SELECT * FROM lab_test WHERE assistant_id = '"+Constants.assistant_id_logged_in+"' " );
        try {
            while( result.next() ){
                
                ref_id.add( result.getString("ref_id") );
                consult_id.add( result.getInt("consult_id") );
                assistant_id.add( result.getInt("assistant_id") );
                
                test_name.add( result.getString("test_name") );
                report.add( result.getString("report") );
                test_center.add( result.getString("test_center") );
                date_of_test.add( result.getString("date_of_test") );
                date_of_result.add( result.getString("date_of_result") );
                        
            }
            col1.setItems(ref_id);
            col2.setItems(consult_id);
            col3.setItems(assistant_id);
            col4.setItems(test_name);
            col5.setItems(report);
            col6.setItems(test_center);
            col7.setItems(date_of_test);
            col8.setItems(date_of_result);
        } catch (SQLException ex) {
            Logger.getLogger(Lab_test_report_showController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void back(MouseEvent event) throws Exception {
        stage.setScene( previous_page.pop() );
        stage.show();
    }
    
}
