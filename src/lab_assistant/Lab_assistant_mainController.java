/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_assistant;

import static immunehistory.ImmuneHistory.stage;
import static immunehistory.labib_constants.Constants.previous_page;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class Lab_assistant_mainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void insert_test_report(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/immunehistory/lab_test/lab_test_report_insert.fxml"));
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
    private void show_test_report(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/immunehistory/lab_test/lab_test_report_show.fxml"));
        Scene scene = new Scene(root);
        previous_page.push( stage.getScene() );
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void edit_test_report(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/immunehistory/lab_test/lab_test_edit_test_report.fxml"));
        Scene scene = new Scene(root);
        previous_page.push( stage.getScene() );
        stage.setScene(scene);
        stage.show();
    }
    
}
