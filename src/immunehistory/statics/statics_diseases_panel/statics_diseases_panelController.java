/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.statics.statics_diseases_panel;

import immunehistory.statics.statics_drug_panel.Statics_drug_listController;
import immunehistory.statics.statics_drug_panel.statics_drug_list;
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


public class statics_diseases_panelController implements Initializable {

     private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<statics_diseases_panel> data;
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
    private TableView<statics_diseases_panel> diseaseTable;
    @FXML
    private TableColumn<?, ?> clm_disease_id;
    @FXML
    private TableColumn<?, ?> clm_disease_name;
    @FXML
    private TableColumn<?, ?> clm_min_period;
    @FXML
    private TableColumn<?, ?> clm_max_period;
    @FXML
    private TableColumn<?, ?> clm_symptom;
    @FXML
    private TableColumn<?, ?> clm_medicine;
    @FXML
    private TableColumn<?, ?> clm_reactive_medicine;
    @FXML
    private TableColumn<?, ?> clmAction;
    @FXML
    private TableColumn<?, ?> clm_disease_type;
    @FXML
    private PrefixSelectionComboBox<String> statics_diseases_panel_menubox;
    @FXML
    private Button statics_diseases_panel_menubtn;
    @FXML
    private TextField statics_diseases_panel_search_field;
    

    
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
                setDiseaseListTable();
                loadDataFromDatabase_collective();   
                searchDiseasesPanel();
       //ADD  items to statics_diseases_panel_menubox
       statics_diseases_panel_menubox.getItems().add("Indivudual Data");
       statics_diseases_panel_menubox.getItems().add("Collective Data");
       
       
     
    }
    
     private void setDiseaseListTable(){

    clm_disease_id.setCellValueFactory(new PropertyValueFactory<>("Diseaseid"));
    clm_disease_name.setCellValueFactory(new PropertyValueFactory<>("DiseaseName"));
    clm_disease_type.setCellValueFactory(new PropertyValueFactory<>("DiseaseType"));
    clm_min_period.setCellValueFactory(new PropertyValueFactory<>("MinPeriod"));
    clm_max_period.setCellValueFactory(new PropertyValueFactory<>("MaxPeriod"));
    clm_symptom.setCellValueFactory(new PropertyValueFactory<>("Symptom")); 
    clm_medicine.setCellValueFactory(new PropertyValueFactory<>("Medicine")); 
    clm_reactive_medicine.setCellValueFactory(new PropertyValueFactory<>("ReactiveMedicine"));
    clmAction.setCellValueFactory(new PropertyValueFactory<>("Action"));
     }
    
    private void loadDataFromDatabase_collective(){
         data.clear();
          try {
              pst = con.prepareStatement("select \n" +
"       DISTINCT  difo2.disease_id,\n" +
"       \n" +
"	   (select disease_name from disease_name where disease_name.disease_id = difo2.disease_id) as diseaseName,\n" +
"\n" +
"	   STUFF((select DISTINCT ','+  (select disease_type from disease_type where disease_type.disease_type_id = difo1.disease_type_id)\n" +
"              from disease_info difo1\n" +
"			  where difo1.disease_id = difo2.disease_id\n" +
"			  FOR XML PATH('')),1,1,'') AS dis_type,\n" +
"\n" +
"	   STUFF((select DISTINCT ','+  CAST(AVG(min_period) AS NVARCHAR)\n" +
"              from disease_info difo1\n" +
"			  where difo1.disease_id = difo2.disease_id\n" +
"			  FOR XML PATH('')),1,1,'') AS dis_min_period,\n" +
"		\n" +
"	   STUFF((select DISTINCT ','+  CAST(AVG(max_period) AS NVARCHAR)\n" +
"              from disease_info difo1\n" +
"			  where difo1.disease_id = difo2.disease_id\n" +
"			  FOR XML PATH('')),1,1,'') AS dis_max_period,\n" +
"\n" +
"       STUFF((select DISTINCT ','+ (select symptom_name from symptom where symptom.symptom_id = difo1.symptom_id)\n" +
"	          from disease_info difo1\n" +
"			  where difo1.disease_id = difo2.disease_id\n" +
"			  FOR XML PATH('')),1,1,'') AS symptom_list,\n" +
"\n" +
"       STUFF((select DISTINCT ','+  (select medicine_generic_name from medicine_generic_name where medicine_generic_name.medicine_generic_id = difo1.medicine_generic_id)\n" +
"              from disease_info difo1\n" +
"			  where difo1.disease_id = difo2.disease_id\n" +
"			  FOR XML PATH('')),1,1,'') AS med_generic_name,\n" +
"	   \n" +
"	   STUFF((select DISTINCT ','+  (select medicine_generic_name from medicine_generic_name where medicine_generic_name.medicine_generic_id = difo1.anti_medicine_generic_name)\n" +
"              from disease_info difo1\n" +
"			  where difo1.disease_id = difo2.disease_id\n" +
"			  FOR XML PATH('')),1,1,'') AS antimed_generic_name\n" +
"	   \n" +
"	   \n" +
"from disease_info difo2\n" +
"group by disease_id,min_period,max_period");
              rs = pst.executeQuery();
              while(rs.next()){
                data.add(new statics_diseases_panel(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8)));
              }
          } catch (SQLException ex) {
              Logger.getLogger(statics_diseases_panelController.class.getName()).log(Level.SEVERE, null, ex);
          }
           diseaseTable.setItems(data);
    }
    
    private void loadDataFromDatabase_indivudual(){
          data.clear();
               try {
              pst = con.prepareStatement("select \n" +
"         difo2.disease_id,\n" +
"       \n" +
"	   (select disease_name from disease_name where disease_name.disease_id = difo2.disease_id) as diseaseName,\n" +
"\n" +
"	   (select disease_type from disease_type where disease_type.disease_type_id = difo2.disease_type_id) as dis_type,\n" +
"              \n" +
"	    difo2.min_period,\n" +
"\n" +
"		difo2.max_period,\n" +
"	   \n" +
"       (select symptom_name from symptom where symptom.symptom_id = difo2.symptom_id) as symp,\n" +
"	   \n" +
"       (select medicine_generic_name from medicine_generic_name where medicine_generic_name.medicine_generic_id = difo2.medicine_generic_id) as med_generic_name,\n" +
"       \n" +
"	  (select medicine_generic_name from medicine_generic_name where medicine_generic_name.medicine_generic_id = difo2.anti_medicine_generic_name) as antimed_generic_name\n" +
"             \n" +
"	   \n" +
"from disease_info difo2");
              rs = pst.executeQuery();
              while(rs.next()){
                data.add(new statics_diseases_panel(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8)));
              }
          } catch (SQLException ex) {
              Logger.getLogger(statics_diseases_panelController.class.getName()).log(Level.SEVERE, null, ex);
          }
           diseaseTable.setItems(data);
    
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
    private void AddNewDiseaseOnAction(ActionEvent event) throws IOException {
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
    private void statics_diseases_panel_menubtnOnAction(ActionEvent event) {
       
        menubox = statics_diseases_panel_menubox.getSelectionModel().getSelectedItem();
        System.out.println(menubox);
        
          if(menubox.equals("Indivudual Data")){
        
           loadDataFromDatabase_indivudual();
       } if(menubox.equals("Collective Data")){
       
         loadDataFromDatabase_collective();
       }
    }

    @FXML
    private void statics_diseases_panel_search_fieldOnAction(ActionEvent event) {
    }
    
    
     private void searchDiseasesPanel(){
   
      statics_diseases_panel_search_field.setOnKeyReleased(e->{
         if(menubox.equals("Collective Data") || menubox.equals(""))
             {     
               if(statics_diseases_panel_search_field.getText().equals("")){
          
             loadDataFromDatabase_collective();
         }else{
             data.clear();
          String sql = "WITH diseaseInfo_collective (disId,diseaseName,dis_type,dis_min_period,\n" +
"                  dis_max_period,symptom_list,med_generic_name,antimed_generic_name) AS(\n" +
"select \n" +
"       DISTINCT  difo2.disease_id as disId,\n" +
"       \n" +
"	   (select disease_name from disease_name where disease_name.disease_id = difo2.disease_id) as diseaseName,\n" +
"\n" +
"	   STUFF((select DISTINCT ','+  (select disease_type from disease_type where disease_type.disease_type_id = difo1.disease_type_id)\n" +
"              from disease_info difo1\n" +
"			  where difo1.disease_id = difo2.disease_id\n" +
"			  FOR XML PATH('')),1,1,'') AS dis_type,\n" +
"\n" +
"	   STUFF((select DISTINCT ','+  CAST(AVG(min_period) AS NVARCHAR)\n" +
"              from disease_info difo1\n" +
"			  where difo1.disease_id = difo2.disease_id\n" +
"			  FOR XML PATH('')),1,1,'') AS dis_min_period,\n" +
"		\n" +
"	   STUFF((select DISTINCT ','+  CAST(AVG(max_period) AS NVARCHAR)\n" +
"              from disease_info difo1\n" +
"			  where difo1.disease_id = difo2.disease_id\n" +
"			  FOR XML PATH('')),1,1,'') AS dis_max_period,\n" +
"\n" +
"       STUFF((select DISTINCT ','+ (select symptom_name from symptom where symptom.symptom_id = difo1.symptom_id)\n" +
"	          from disease_info difo1\n" +
"			  where difo1.disease_id = difo2.disease_id\n" +
"			  FOR XML PATH('')),1,1,'') AS symptom_list,\n" +
"\n" +
"       STUFF((select DISTINCT ','+  (select medicine_generic_name from medicine_generic_name where medicine_generic_name.medicine_generic_id = difo1.medicine_generic_id)\n" +
"              from disease_info difo1\n" +
"			  where difo1.disease_id = difo2.disease_id\n" +
"			  FOR XML PATH('')),1,1,'') AS med_generic_name,\n" +
"	   \n" +
"	   STUFF((select DISTINCT ','+  (select medicine_generic_name from medicine_generic_name where medicine_generic_name.medicine_generic_id = difo1.anti_medicine_generic_name)\n" +
"              from disease_info difo1\n" +
"			  where difo1.disease_id = difo2.disease_id\n" +
"			  FOR XML PATH('')),1,1,'') AS antimed_generic_name\n" +
"	   \n" +
"	   \n" +
"from disease_info difo2\n" +
"group by disease_id,min_period,max_period\n" +
"\n" +
")\n" +
"select disId,diseaseName,dis_type,dis_min_period,\n" +
"dis_max_period,symptom_list,med_generic_name,antimed_generic_name \n" +
"from diseaseInfo_collective \n" +
"where disId LIKE '%"+statics_diseases_panel_search_field.getText()+"%'	 \n" +
"   OR diseaseName LIKE '%"+statics_diseases_panel_search_field.getText()+"%'\n" +
"   OR dis_type LIKE '%"+statics_diseases_panel_search_field.getText()+"%'\n" +
"   OR dis_min_period LIKE '%"+statics_diseases_panel_search_field.getText()+"%'\n" +
"   OR dis_max_period LIKE '%"+statics_diseases_panel_search_field.getText()+"%'\n" +
"   OR symptom_list LIKE '%"+statics_diseases_panel_search_field.getText()+"%'\n" +
"   OR med_generic_name LIKE '%"+statics_diseases_panel_search_field.getText()+"%'\n" +
"   OR antimed_generic_name LIKE '%"+statics_diseases_panel_search_field.getText()+"%'";
          try {
              pst = con.prepareStatement(sql);
              rs = pst.executeQuery();
              while(rs.next()){
                   data.add(new statics_diseases_panel(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8)));
             
              }
              diseaseTable.setItems(data);
          } catch (SQLException ex) {
              Logger.getLogger(Statics_disease_listController.class.getName()).log(Level.SEVERE, null, ex);
          }
         }
               
         }else if(menubox.equals("Indivudual Data"))
         {
             if(statics_diseases_panel_search_field.getText().equals("")){
          
             loadDataFromDatabase_indivudual();
         }else{
             data.clear();
          String sql = "WITH diseaseInfo_individual (disId,diseaseName,dis_type,dis_min_period,\n" +
"                  dis_max_period,symptom_list,med_generic_name,antimed_generic_name) AS(\n" +
"select \n" +
"         difo2.disease_id as disId,\n" +
"       \n" +
"	   (select disease_name from disease_name where disease_name.disease_id = difo2.disease_id) as diseaseName,\n" +
"\n" +
"	   (select disease_type from disease_type where disease_type.disease_type_id = difo2.disease_type_id) as dis_type,\n" +
"              \n" +
"	    difo2.min_period as dis_min_period,\n" +
"\n" +
"		difo2.max_period as dis_max_period,\n" +
"	   \n" +
"       (select symptom_name from symptom where symptom.symptom_id = difo2.symptom_id) as symptom_list,\n" +
"	   \n" +
"       (select medicine_generic_name from medicine_generic_name where medicine_generic_name.medicine_generic_id = difo2.medicine_generic_id) as med_generic_name,\n" +
"       \n" +
"	  (select medicine_generic_name from medicine_generic_name where medicine_generic_name.medicine_generic_id = difo2.anti_medicine_generic_name) as antimed_generic_name\n" +
"             \n" +
"	   \n" +
"from disease_info difo2\n" +
"          \n" +
")\n" +
"select disId,diseaseName,dis_type,dis_min_period,\n" +
"dis_max_period,symptom_list,med_generic_name,antimed_generic_name \n" +
"from diseaseInfo_individual \n" +
"where disId LIKE '%"+statics_diseases_panel_search_field.getText()+"%'	 \n" +
"   OR diseaseName LIKE '%"+statics_diseases_panel_search_field.getText()+"%'\n" +
"   OR dis_type LIKE '%"+statics_diseases_panel_search_field.getText()+"%'\n" +
"   OR dis_min_period LIKE '%"+statics_diseases_panel_search_field.getText()+"%'\n" +
"   OR dis_max_period LIKE '%"+statics_diseases_panel_search_field.getText()+"%'\n" +
"   OR symptom_list LIKE '%"+statics_diseases_panel_search_field.getText()+"%'\n" +
"   OR med_generic_name LIKE '%"+statics_diseases_panel_search_field.getText()+"%'\n" +
"   OR antimed_generic_name LIKE '%"+statics_diseases_panel_search_field.getText()+"%'";
          try {
              pst = con.prepareStatement(sql);
              rs = pst.executeQuery();
              while(rs.next()){
                   data.add(new statics_diseases_panel(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8)));
             
              }
              diseaseTable.setItems(data);
          } catch (SQLException ex) {
              Logger.getLogger(Statics_disease_listController.class.getName()).log(Level.SEVERE, null, ex);
          }
         }
         
         }
         
         
      });
   } 
    
    
    
}


 
    