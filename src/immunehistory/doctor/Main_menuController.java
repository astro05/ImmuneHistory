/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.doctor;

import static immunehistory.ImmuneHistory.stage;
import static immunehistory.labib_constants.Constants.previous_page;
import immunehistory.labib_database.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class Main_menuController implements Initializable {

    @FXML
    private Button medicine_feedback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void show_user_history(MouseEvent event) throws Exception {        
        Parent root = FXMLLoader.load(getClass().getResource("user_history_find.fxml"));
        Scene scene = new Scene(root);
        previous_page.push( stage.getScene() );
        stage.setScene(scene);
        stage.show();      
    }

    @FXML
    private void back(MouseEvent event) {
        
        stage.setScene( previous_page.pop() );
        stage.show();
    
    }

    @FXML
    private void write_prescription(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("write_prescription.fxml"));
        Scene scene = new Scene(root);
        previous_page.push( stage.getScene() );
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void edit_profile(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("doctor_edit_profile.fxml"));
        Scene scene = new Scene(root);
        previous_page.push( stage.getScene() );
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void appointments(MouseEvent event) {
    }

    @FXML
    private void medicine_success_feedback(MouseEvent event) {
    }
    
}
