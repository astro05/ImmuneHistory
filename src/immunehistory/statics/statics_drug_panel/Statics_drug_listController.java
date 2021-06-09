/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.statics.statics_drug_panel;

import immunehistory.statics.statics_diseases_panel.Statics_disease_listController;
import immunehistory.statics.statics_diseases_panel.statics_disease_list;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author joyultimates
 */
public class Statics_drug_listController implements Initializable {

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<statics_drug_list> data;
    
    @FXML
    private TextField statics_drug_search_field;
    @FXML
    private Button btnPrev;
    @FXML
    private Label lblTotalDiseases;
    @FXML
    private Label lblShowingDiseases;
    @FXML
    private Button btnNext;
    @FXML
    private TableView<statics_drug_list> statics_drug_list_table;
    @FXML
    private TableColumn<?, ?> clm_drug_id;
    @FXML
    private TableColumn<?, ?> clm_drug_name;
    @FXML
    private TableColumn<?, ?> clm_drug_type;
    @FXML
    private TableColumn<?, ?> clm_drug_action;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        con =dba.DBConnection.immunehistoryConnection();
                data = FXCollections.observableArrayList();
                setDrugListTable();
                loadDataFromDatabase();
                searchDrugList();
    }  
    
     private void setDrugListTable(){

    clm_drug_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    clm_drug_name.setCellValueFactory(new PropertyValueFactory<>("MedicineGenericName"));
    clm_drug_type.setCellValueFactory(new PropertyValueFactory<>("MedicineType"));
    clm_drug_action.setCellValueFactory(new PropertyValueFactory<>("Action"));
    }
    
    private void loadDataFromDatabase(){
          try {
              pst = con.prepareStatement("select medicine_generic_id,medicine_generic_name from medicine_generic_name");
              rs = pst.executeQuery();
              while(rs.next()){
                data.add(new statics_drug_list(rs.getString(1), rs.getString(2)));
              }
          } catch (SQLException ex) {
              Logger.getLogger(Statics_drug_listController.class.getName()).log(Level.SEVERE, null, ex);
          }
           statics_drug_list_table.setItems(data);
    }

    @FXML
    private void statics_drug_search_fieldOnAction(ActionEvent event) {
    }


    @FXML
    private void handlePrevButton(ActionEvent event) {
    }

    @FXML
    private void handleNextButton(ActionEvent event) {
    }
    
      private void searchDrugList(){
   
      statics_drug_search_field.setOnKeyReleased(e->{
         if(statics_drug_search_field.getText().equals("")){
          
             loadDataFromDatabase();
         }else{
             data.clear();
          String sql = "select * from medicine_generic_name\n" +
"where medicine_generic_id LIKE '%"+statics_drug_search_field.getText()+"%'\n" +
" OR   medicine_generic_name LIKE '%"+statics_drug_search_field.getText()+"%'\n" +
" OR  medicine_generic_note LIKE  '%"+statics_drug_search_field.getText()+"%'";
          try {
              pst = con.prepareStatement(sql);
              rs = pst.executeQuery();
              while(rs.next()){
                  data.add(new statics_drug_list(rs.getString(1), rs.getString(2)));
             
              }
               statics_drug_list_table.setItems(data);
          } catch (SQLException ex) {
              Logger.getLogger(Statics_disease_listController.class.getName()).log(Level.SEVERE, null, ex);
          }
         }
      });
   } 
    
    
}
