/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.statics.statics_drug_panel;

import immunehistory.statics.statics_diseases_panel.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.PrefixSelectionComboBox;


public class statics_drug_panelController implements Initializable {

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<statics_drug_panel> data;
    private String menubox ="";
    
    @FXML
    private Button btnPrev;
    @FXML
    private Label lblTotalDiseases;
    @FXML
    private Label lblShowingDiseases;
    @FXML
    private Button btnNext;
    @FXML
    private TableView<statics_drug_panel> drugTable;
    @FXML
    private TableColumn<?, ?> clm_drug_id;
    @FXML
    private TableColumn<?, ?> clm_generic_name;
    @FXML
    private TableColumn<?, ?> clm_brand_name;
    @FXML
    private TableColumn<?, ?> clm_apply_onpatient;
    @FXML
    private TableColumn<?, ?> clm_note;
    @FXML
    private TableColumn<?, ?> clmAction;
    @FXML
    private PrefixSelectionComboBox<String> statics_drug_panel_menubox;
    @FXML
    private Button statics_drug_panel_menubtn;
    @FXML
    private TextField statics_drug_panel_search_field;

   
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       con =dba.DBConnection.immunehistoryConnection();
                data = FXCollections.observableArrayList();
                setDrugTable();
                loadDataFromDatabase_collective();  
                 searchDrugPanel();
       //ADD  items to statics_diseases_panel_menubox
       statics_drug_panel_menubox.getItems().add("Indivudual Data");
       statics_drug_panel_menubox.getItems().add("Collective Data");

    }
    
     private void setDrugTable(){

    clm_drug_id.setCellValueFactory(new PropertyValueFactory<>("Drugid"));
    clm_generic_name.setCellValueFactory(new PropertyValueFactory<>("MedGenericName"));
    clm_brand_name.setCellValueFactory(new PropertyValueFactory<>("MedBrandName"));
    clm_apply_onpatient.setCellValueFactory(new PropertyValueFactory<>("Applyonpatient"));
    clm_note.setCellValueFactory(new PropertyValueFactory<>("Note"));
    clmAction.setCellValueFactory(new PropertyValueFactory<>("Action"));
     }
     
        private void loadDataFromDatabase_collective(){
         data.clear();
          try {
              pst = con.prepareStatement("select  \n" +
"        DISTINCT medicine_generic_name.medicine_generic_id,\n" +
"		 \n" +
"	    medicine_generic_name,\n" +
"        \n" +
"		STUFF((select DISTINCT ','+ medicine_local_name.medicine_local_name\n" +
"              from medicine_local_name\n" +
"			  where medicine_local_name.medicine_generic_id = medicine_generic_name.medicine_generic_id\n" +
"			  FOR XML PATH('')),1,1,'') AS med_local,\n" +
" \n" +
"        medicine_generic_note\n" +
"\n" +
"from medicine_generic_name\n" +
"left join medicine_local_name\n" +
"on medicine_generic_name.medicine_generic_id = medicine_local_name.medicine_generic_id");
              rs = pst.executeQuery();
              while(rs.next()){
                data.add(new statics_drug_panel(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4)));
              }
          } catch (SQLException ex) {
              Logger.getLogger(statics_drug_panelController.class.getName()).log(Level.SEVERE, null, ex);
          }
           drugTable.setItems(data);
    }
        
      private void loadDataFromDatabase_indivudual(){
          data.clear();
               try {
              pst = con.prepareStatement("select  \n" +
"        medicine_generic_name.medicine_generic_id,\n" +
"		medicine_generic_name,\n" +
"        medicine_local_name.medicine_local_name,\n" +
"        medicine_generic_note\n" +
"\n" +
"from medicine_generic_name\n" +
"left join medicine_local_name\n" +
"on medicine_generic_name.medicine_generic_id = medicine_local_name.medicine_generic_id");
              rs = pst.executeQuery();
              while(rs.next()){
                data.add(new statics_drug_panel(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4)));
              }
          } catch (SQLException ex) {
              Logger.getLogger(statics_drug_panelController.class.getName()).log(Level.SEVERE, null, ex);
          }
           drugTable.setItems(data);
    
    }    


    @FXML
    private void btnSearchOnAction(ActionEvent event) {
    }

    

    @FXML
    private void handlePrevButton(ActionEvent event) {
    }

    @FXML
    private void handleNextButton(ActionEvent event) {
    }

    @FXML
    private void AddNewTradeNameOnAction(ActionEvent event) throws IOException {
           FXMLLoader fXMLLoader = new FXMLLoader();
       fXMLLoader.setLocation(getClass().getResource("/immunehistory/statics/statics_drug_panel/statics_add_new_trade_name.fxml"));
       
          
         Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        stage.setScene(scene);
        stage.setTitle("Add Drug");
        stage.resizableProperty().setValue(false);
        stage.initModality(Modality.APPLICATION_MODAL);
       
        stage.show();
    }

    @FXML
    private void AddNewDrugOnAction(ActionEvent event) throws IOException {
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
    private void statics_drug_panel_menubtnOnAction(ActionEvent event) {
        
         menubox = statics_drug_panel_menubox.getSelectionModel().getSelectedItem();
        System.out.println(menubox);
        
          if(menubox.equals("Indivudual Data")){
        
           loadDataFromDatabase_indivudual();
       } if(menubox.equals("Collective Data")){
       
         loadDataFromDatabase_collective();
       }
    }

    @FXML
    private void statics_drug_panel_search_fieldOnAction(ActionEvent event) {
    }

   
     private void searchDrugPanel(){
   
      statics_drug_panel_search_field.setOnKeyReleased(e->{
         if(menubox.equals("Collective Data") || menubox.equals(""))
             {     
               if(statics_drug_panel_search_field.getText().equals("")){
          
             loadDataFromDatabase_collective();
         }else{
             data.clear();
          String sql = "with medicine_collective(med_generic_id,med_generic_name,med_local,med_generic_note)AS(\n" +
"select  \n" +
"        DISTINCT medicine_generic_name.medicine_generic_id as med_gemeric_id,\n" +
"		 \n" +
"	    medicine_generic_name as med_generic_name,\n" +
"        \n" +
"		STUFF((select DISTINCT ','+ medicine_local_name.medicine_local_name\n" +
"              from medicine_local_name\n" +
"			  where medicine_local_name.medicine_generic_id = medicine_generic_name.medicine_generic_id\n" +
"			  FOR XML PATH('')),1,1,'') AS med_local,\n" +
" \n" +
"        medicine_generic_note as med_generic_note\n" +
"\n" +
"from medicine_generic_name\n" +
"left join medicine_local_name\n" +
"on medicine_generic_name.medicine_generic_id = medicine_local_name.medicine_generic_id\n" +
")\n" +
"select * from medicine_collective\n" +
"where med_generic_id LIKE '%"+statics_drug_panel_search_field.getText()+"%'\n" +
"   OR med_generic_name LIKE '%"+statics_drug_panel_search_field.getText()+"%'\n" +
"   OR med_local LIKE '%"+statics_drug_panel_search_field.getText()+"%'\n" +
"   OR med_generic_note LIKE '%"+statics_drug_panel_search_field.getText()+"%'";
          try {
              pst = con.prepareStatement(sql);
              rs = pst.executeQuery();
              while(rs.next()){
                    data.add(new statics_drug_panel(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4)));
           
              }
              drugTable.setItems(data);
          } catch (SQLException ex) {
              Logger.getLogger(Statics_drug_listController.class.getName()).log(Level.SEVERE, null, ex);
          }
         }
               
         }else if(menubox.equals("Indivudual Data"))
         {
             if(statics_drug_panel_search_field.getText().equals("")){
          
             loadDataFromDatabase_indivudual();
         }else{
             data.clear();
          String sql = "with medicine_individual (med_generic_id,med_generic_name,med_local_name,med_generic_note) AS(\n" +
"select  \n" +
"        medicine_generic_name.medicine_generic_id as med_generic_id,\n" +
"		medicine_generic_name as med_generic_name,\n" +
"        medicine_local_name.medicine_local_name as med_local_name,\n" +
"        medicine_generic_note as med_generic_note\n" +
"\n" +
"from medicine_generic_name\n" +
"left join medicine_local_name\n" +
"on medicine_generic_name.medicine_generic_id = medicine_local_name.medicine_generic_id\n" +
")\n" +
"select * from medicine_individual\n" +
"where med_generic_id LIKE '%"+statics_drug_panel_search_field.getText()+"%'\n" +
"   OR med_generic_name LIKE '%"+statics_drug_panel_search_field.getText()+"%'\n" +
"   OR med_local_name LIKE '%"+statics_drug_panel_search_field.getText()+"%'\n" +
"   OR med_generic_note LIKE '%"+statics_drug_panel_search_field.getText()+"%'";
          try {
              pst = con.prepareStatement(sql);
              rs = pst.executeQuery();
              while(rs.next()){
                     data.add(new statics_drug_panel(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4)));
           
              }
              drugTable.setItems(data);
          } catch (SQLException ex) {
              Logger.getLogger(Statics_drug_listController.class.getName()).log(Level.SEVERE, null, ex);
          }
         }
         
         }
         
         
      });
   } 
    
    
    
}


 
    