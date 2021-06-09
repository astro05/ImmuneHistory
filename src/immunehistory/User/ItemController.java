/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.User;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Sadman
 */
public class ItemController implements Initializable {
  static String serialno,name,consultid,cat,apdate,condate,stat;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label serial_no;
    @FXML
    private Label consult_id;
    @FXML
    private Label doctorname;
    @FXML
    private Label appointing_date;
    @FXML
    private Label consulting_date;
    @FXML
    private Label category;
    @FXML
    private Button status;
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serial_no.setText(serialno);
        consult_id.setText(consultid);
        doctorname.setText(name);
        appointing_date.setText(apdate);
        consulting_date.setText(condate);
        category.setText(cat);
      String sDate1=condate;  
      try {  
          Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
          Date currentDate = new Date();
          if(date1.compareTo(currentDate)<0)
          {
             status.setText("Expired");
             status.setStyle("-fx-background-color: #FF6961");
          }
          else
          {
               status.setText("Active");
             status.setStyle("-fx-background-color: #90EE90");
          }     
      } catch (ParseException ex) {
          Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
      }
        
    }    
    public void myFunction(String Text)
    {
        
    }
}
