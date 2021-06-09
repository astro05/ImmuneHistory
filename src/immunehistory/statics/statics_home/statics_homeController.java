
package immunehistory.statics.statics_home;


import immunehistory.ImmuneHistory;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class statics_homeController implements Initializable {

    @FXML
    private Button statics_home_id;
    @FXML
    private Button statics_diseases_id;
    @FXML
    private Button statics_drug_id;
    @FXML
    private Button statics_treatment_id;
    @FXML
    private Button statics_drugstatics;
    @FXML
    private Button statics_setting_id;
    @FXML
    private AnchorPane statics_mainhome;
    @FXML
    private StackPane statics_mainhomePanel;



  
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

   
   
    }

    @FXML
    private void statics_logout_id(ActionEvent event) throws IOException {
         FXMLLoader fXMLLoader = new FXMLLoader();
       fXMLLoader.setLocation(getClass().getResource("/immunehistory/home.fxml"));
       
          
         Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        stage.setScene(scene);
        stage.setTitle("Statics");
        stage.resizableProperty().setValue(false);
        Stage mainhome = (Stage) statics_mainhome.getScene().getWindow();
        mainhome.close();
        
        stage.show();
    }

    @FXML
    private void statics_homeOnAction(ActionEvent event) throws IOException {
        
        Parent home = FXMLLoader.load(getClass().getResource("/immunehistory/statics/statics_home_panel/statics_home_panel.fxml"));
     Scene scene = new Scene(home);
     statics_mainhomePanel.getChildren().setAll(home);
    }

    @FXML
    private void statics_diseasesOnAction(ActionEvent event) throws IOException {
        
        Parent home = FXMLLoader.load(getClass().getResource("/immunehistory/statics/statics_diseases_panel/statics_diseases_panel.fxml"));
     Scene scene = new Scene(home);
     statics_mainhomePanel.getChildren().setAll(home);
    }

    @FXML
    private void statics_drugOnAction(ActionEvent event) throws IOException {
        
         Parent home = FXMLLoader.load(getClass().getResource("/immunehistory/statics/statics_drug_panel/statics_drug_panel.fxml"));
     Scene scene = new Scene(home);
     statics_mainhomePanel.getChildren().setAll(home);
    }

    @FXML
    private void statics_treatmentOnAction(ActionEvent event) throws IOException {
        
           Parent home = FXMLLoader.load(getClass().getResource("/immunehistory/statics/statics_treatment_panel/statics_treatment_feedback.fxml"));
     Scene scene = new Scene(home);
     statics_mainhomePanel.getChildren().setAll(home);
    }

    @FXML
    private void statics_drugstaticsOnAction(ActionEvent event) throws IOException {
        
        Parent home = FXMLLoader.load(getClass().getResource("/immunehistory/statics/statics_drugstatics_panel/statics_drugstatics.fxml"));
     Scene scene = new Scene(home);
     statics_mainhomePanel.getChildren().setAll(home);
    }

    @FXML
    private void statics_settingOnAction(ActionEvent event) throws IOException {
        
        Parent home = FXMLLoader.load(getClass().getResource("/immunehistory/statics/statics_settings_panel/statics_settings_panel.fxml"));
     Scene scene = new Scene(home);
     statics_mainhomePanel.getChildren().setAll(home);
    }

    @FXML
    private void statics_lifeylink(ActionEvent event) throws IOException {
        
        Desktop.getDesktop().browse(URI.create("https://testsitejoy.000webhostapp.com/lifey/loading.html"));
   
    }

    @FXML
    private void statics_about(ActionEvent event) {
    }

    @FXML
    private void statics_feedback(ActionEvent event) {
    }

   


 
 

   
 


}
