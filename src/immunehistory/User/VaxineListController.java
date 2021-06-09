/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.User;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Sadman
 */
public class VaxineListController implements Initializable {
  static String no,vId,vName,vDate;
    @FXML
    private Label serial_no;
    @FXML
    private Label vaxine_id;
    @FXML
    private Label vaxine_name;
    @FXML
    private Label vaxination_date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serial_no.setText(no);
        vaxine_id.setText(vId);
        vaxine_name.setText(vName);
        vaxination_date.setText(vDate);
        
    }    
    
}
