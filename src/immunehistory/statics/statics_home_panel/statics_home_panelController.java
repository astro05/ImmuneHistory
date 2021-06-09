
package immunehistory.statics.statics_home_panel;


import immunehistory.statics.statics_diseases_panel.Statics_disease_listController;
import immunehistory.statics.statics_diseases_panel.statics_disease_list;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class statics_home_panelController implements Initializable {

     private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<statics_disease_list> data;
    
    @FXML
    private Text lblTotalDiseases;
    @FXML
    private Text lblTotalDrug;
    @FXML
    private Text lblTotalTreatment;
    @FXML
    private Text lblTotalDrugStatics;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         con =dba.DBConnection.immunehistoryConnection();
         lblTotalDiseases();
         lblTotalDrug();
    }
    
    private void lblTotalDrug(){
          try {
              pst = con.prepareStatement("select COUNT(medicine_generic_id) from medicine_generic_name");
              rs = pst.executeQuery();
               if(rs.next()){
            String drug_count = rs.getString(1);
            lblTotalDrug.setText(drug_count);
            }
          } catch (SQLException ex) {
              Logger.getLogger(Statics_disease_listController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
     private void lblTotalDiseases(){
          try {
              pst = con.prepareStatement("select COUNT(disease_id) from disease_name");
              rs = pst.executeQuery();
               if(rs.next()){
            String disease_count = rs.getString(1);
            lblTotalDiseases.setText(disease_count);
            }
          } catch (SQLException ex) {
              Logger.getLogger(Statics_disease_listController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @FXML
    private void addNewDiseases(ActionEvent event) throws IOException {
             FXMLLoader fXMLLoader = new FXMLLoader();
       fXMLLoader.setLocation(getClass().getResource("/immunehistory/statics/statics_diseases_panel/statics_add_new_disease.fxml"));
       
          
         Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        stage.setScene(scene);
        stage.setTitle("Add Disease");
        stage.resizableProperty().setValue(false);
        stage.initModality(Modality.APPLICATION_MODAL);
       
        stage.show();
    
    }

    @FXML
    private void addNewDrugs(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
       fXMLLoader.setLocation(getClass().getResource("/immunehistory/statics/statics_drug_panel/statics_add_new_drug.fxml"));
       
          
         Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        stage.setScene(scene);
        stage.setTitle("Add Drug");
        stage.resizableProperty().setValue(false);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    private void viewTreatment(ActionEvent event) {
    }

    @FXML
    private void viewDrugStatics(ActionEvent event) {
    }

    @FXML
    private void statics_disease_listOnAction(MouseEvent event) throws IOException {
                   FXMLLoader fXMLLoader = new FXMLLoader();
       fXMLLoader.setLocation(getClass().getResource("/immunehistory/statics/statics_diseases_panel/statics_disease_list.fxml"));
       
          
         Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        stage.setScene(scene);
        stage.setTitle("Disease List");
        stage.resizableProperty().setValue(false);
        stage.initModality(Modality.APPLICATION_MODAL);
       
        stage.show();
    }

    @FXML
    private void statics_drug_listOnAction(MouseEvent event) throws IOException {
                   FXMLLoader fXMLLoader = new FXMLLoader();
       fXMLLoader.setLocation(getClass().getResource("/immunehistory/statics/statics_drug_panel/statics_drug_list.fxml"));
       
          
         Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        stage.setScene(scene);
        stage.setTitle("Disease List");
        stage.resizableProperty().setValue(false);
        stage.initModality(Modality.APPLICATION_MODAL);
       
        stage.show();
    }

  

}