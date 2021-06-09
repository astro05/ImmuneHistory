/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.statics.statics_diseases_panel;

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
public class Statics_disease_listController implements Initializable {

      private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<statics_disease_list> data;
    
    @FXML
    private TextField statics_disease_search_field;
    @FXML
    private Button btnPrev;
    @FXML
    private Label lblTotalDiseases;
    @FXML
    private Label lblShowingDiseases;
    @FXML
    private Button btnNext;
    @FXML
    private TableView<statics_disease_list> statics_disease_list_table;
    @FXML
    private TableColumn<?, ?> clm_disease_id;
    @FXML
    private TableColumn<?, ?> clm_disease_name;
    @FXML
    private TableColumn<?, ?> clm_disease_type;
    @FXML
    private TableColumn<?, ?> clm_disease_action;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                con =dba.DBConnection.immunehistoryConnection();
                data = FXCollections.observableArrayList();
                setDiseaseListTable();
                loadDataFromDatabase();
                searchDiseaseList();
    }    
    
    private void setDiseaseListTable(){
    clm_disease_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    clm_disease_name.setCellValueFactory(new PropertyValueFactory<>("DiseaseName"));
    clm_disease_type.setCellValueFactory(new PropertyValueFactory<>("DiseaseType"));
    clm_disease_action.setCellValueFactory(new PropertyValueFactory<>("Action"));
    }
    
    private void loadDataFromDatabase(){
          try {
              pst = con.prepareStatement("select disease_id,disease_name,disease_type\n" +
                      "from disease_name\n" + 
                      "left join disease_type on disease_name.disease_type_id = disease_type.disease_type_id");
              rs = pst.executeQuery();
              while(rs.next()){
                  data.add(new statics_disease_list(rs.getString(1), rs.getString(2), rs.getString(3)));
              }
          } catch (SQLException ex) {
              Logger.getLogger(Statics_disease_listController.class.getName()).log(Level.SEVERE, null, ex);
          }
          statics_disease_list_table.setItems(data);
    }

    @FXML
    private void statics_disease_search_fieldOnAction(ActionEvent event) {
    }


    @FXML
    private void handlePrevButton(ActionEvent event) {
    }

    @FXML
    private void handleNextButton(ActionEvent event) {
    }
    
   private void searchDiseaseList(){
   
      statics_disease_search_field.setOnKeyReleased(e->{
         if(statics_disease_search_field.getText().equals("")){
          
             loadDataFromDatabase();
         }else{
             data.clear();
          String sql = "select disease_id,disease_name,disease_type\n" +
"from disease_name\n" +
"left join disease_type on disease_name.disease_type_id = disease_type.disease_type_id\n" +
"where disease_id LIKE '%"+statics_disease_search_field.getText()+"%'\n" +
"  OR  disease_name LIKE '%"+statics_disease_search_field.getText()+"%'\n" +
"  OR  disease_type LIKE '%"+statics_disease_search_field.getText()+"%'";
          try {
              pst = con.prepareStatement(sql);
              rs = pst.executeQuery();
              while(rs.next()){
                   data.add(new statics_disease_list(rs.getString(1), rs.getString(2), rs.getString(3)));
             
              }
               statics_disease_list_table.setItems(data);
          } catch (SQLException ex) {
              Logger.getLogger(Statics_disease_listController.class.getName()).log(Level.SEVERE, null, ex);
          }
         }
      });
   } 
    
    
}
