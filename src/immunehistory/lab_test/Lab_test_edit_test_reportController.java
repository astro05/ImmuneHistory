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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class Lab_test_edit_test_reportController implements Initializable {

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
          
        try {
            ResultSet result = Database.select_query( "SELECT * FROM lab_test WHERE assistant_id = '"+Constants.assistant_id_logged_in+"' " );
            while( result.next() ){
                
                ref_id.setText( result.getString("ref_id") );
                consult_id.setText( String.valueOf( result.getInt("consult_id") ) );
                test_name.setText( result.getString("test_name") );
                report.setText( result.getString("report") );
                test_center.setText( String.valueOf( result.getString("test_center")) );
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                
                String date1 = result.getString("date_of_test").split(" ")[0];
                String date2 = result.getString("date_of_result").split(" ")[0];
                
                System.out.println(date1+" "+date2);
                
                LocalDate localDate = LocalDate.parse( date1, formatter );
                LocalDate localDate2 = LocalDate.parse( date2, formatter );
                
                date_of_test.setValue( localDate );
                date_of_result.setValue( localDate2 );
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Lab_test_edit_test_reportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void insert(MouseEvent event) {
        
        String r = ref_id.getText();
        String cid = consult_id.getText();
        String tn = test_name.getText();
        String rep = report.getText();
        String tc = test_center.getText();

        String dot = date_of_test.getValue().toString();
        String dor = date_of_result.getValue().toString();
        
        Database.insert_query("UPDATE lab_test SET "
            + " ref_id = '"+r+"',"
            + " consult_id = '"+cid+"', "
            + " test_name = '"+tn+"', "
            + " report = '"+rep+"', "
            + " test_center = '"+tc+"', "
            + " date_of_test = '"+dot+"', "
            + " date_of_result = '"+dor+"' WHERE consult_id = '"+cid+"' AND ref_id = '"+r+"' ");
        
    }

    @FXML
    private void back(MouseEvent event) {
        stage.setScene( previous_page.pop() );
        stage.show();
    }
    
}
