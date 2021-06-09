/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.doctor;

import com.jfoenix.controls.JFXListView;
import static immunehistory.ImmuneHistory.stage;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class Doctor_give_medicine_feedbackController implements Initializable {

    @FXML
    private TextField medicine_name;
    @FXML
    private TextField success_rate;
    @FXML
    private TextField age;
    @FXML
    private JFXListView<String> suggestions;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Pane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        medicine_name.textProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<String> medicine_generic_name = FXCollections.observableArrayList ();
            ResultSet result = Database.select_query( "SELECT * FROM medicine_generic_name WHERE medicine_generic_name LIKE '"+newValue+"%' " );
  
            try {
                
                while( result.next() ){
                    medicine_generic_name.add( result.getString( "medicine_generic_name" ) );                
                }
                suggestions.setItems(medicine_generic_name);
                suggestions.visibleProperty().set(true);
                
            } catch (SQLException ex) {
                Logger.getLogger(Doctor_give_medicine_feedbackController.class.getName()).log(Level.SEVERE, null, ex);
            }  
            
        });
        
        
        
        


    }    

    @FXML
    private void submit(MouseEvent event) {
        
        String mn = medicine_name.getText();
        String sr = success_rate.getText();
        String a = age.getText();
       
        Database.insert_query( "INSERT INTO doc_med_feedback VALUES " +
"        ( '"+a+"', '"+mn+"', '"+sr+"' ) " );
        
    }

    @FXML
    private void back(MouseEvent event) {
        stage.setScene( previous_page.pop() );
        stage.show();
    }

    @FXML
    private void suggestions_selected(MouseEvent event) {
        medicine_name.setText("");
        medicine_name.setText( suggestions.getSelectionModel().getSelectedItem() );
        suggestions.visibleProperty().set(false);
    }

    @FXML
    private void pane_clicked(MouseEvent event) {
        suggestions.visibleProperty().set(false);
    }

    @FXML
    private void anchorpane_clicked(MouseEvent event) {
        suggestions.visibleProperty().set(false);
    }
    
}
