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
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class User_history_findController implements Initializable {

    @FXML
    private TextField ap_sl_no;
    @FXML
    private Label message_label;
    @FXML
    private TextField consult_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       
        
        
        
    }    

    @FXML
    private void search(MouseEvent event) {
        
        ResultSet result = Database.select_query(" SELECT * FROM appointment a \n" +
"INNER JOIN consult c ON a.consult_id = c.consult_id \n" +
"WHERE c.doctor_id = '"+Constants.doctor_id+"' AND a.ap_sl_no = '"+ap_sl_no.getText()+"'  AND c.consult_id = '"+consult_id.getText()+"' ");
        
        System.out.println("111");
        
        try {
            if( result.next() == true ){
                System.out.println("111");
                
                Constants.user_history_ap_sl_no = Integer.valueOf( ap_sl_no.getText() );
                Constants.user_history_consult_id = Integer.valueOf( consult_id.getText() );
                
                Parent root = FXMLLoader.load(getClass().getResource("doctor_user_history.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception ex) {
            Logger.getLogger(User_history_findController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void back(MouseEvent event) {
        stage.setScene( previous_page.pop() );
        stage.show();
    }
    
}
