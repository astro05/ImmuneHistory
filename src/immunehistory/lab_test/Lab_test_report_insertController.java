/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.lab_test;

import static immunehistory.ImmuneHistory.stage;
import immunehistory.labib_constants.Constants;
import static immunehistory.labib_constants.Constants.previous_page;
import immunehistory.labib_database.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class Lab_test_report_insertController implements Initializable {

    @FXML
    private TextField ref_id;
    @FXML
    private TextField consult_id;
    @FXML
    private TextField test_name;
    @FXML
    private TextField report;
    @FXML
    private DatePicker date_of_test;
    @FXML
    private DatePicker date_of_result;
    @FXML
    private TextField test_center;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void insert(MouseEvent event) throws Exception {
        String ref_id = this.ref_id.getText();
        int consult_id = Integer.valueOf( this.consult_id.getText() );
        String test_name = this.test_name.getText();
        String report = this.report.getText();
        String date_of_test = this.date_of_test.getValue().toString();
        String date_of_result = this.date_of_result.getValue().toString();
        String test_center = this.test_center.getText();
        
        Lab_Test lab_test = new Lab_Test();
        lab_test.setRef_id(ref_id);
        lab_test.setConsult_id( consult_id );
        lab_test.setAssistant_id( Constants.assistant_id_logged_in );
        lab_test.setTest_name(test_name);
        lab_test.setTest_center(test_center);
        lab_test.setReport(report);
        lab_test.setDate_of_test(date_of_test);
        lab_test.setDate_of_result(date_of_result);
        
        Database.insert_into_lab_test(lab_test);
        
    }

    @FXML
    private void back(MouseEvent event) throws Exception {
        stage.setScene( previous_page.pop() );
        stage.show();
    }
    
   
    
}
