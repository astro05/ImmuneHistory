/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.statics.statics_treatment_panel;

import immunehistory.statics.statics_drug_panel.Statics_drug_listController;
import immunehistory.statics.statics_drug_panel.statics_drug_list;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author joyultimates
 */
public class Statics_treatment_feedbackController implements Initializable {

        private Connection con = null;
        private Connection con2 = null;
    private PreparedStatement pst = null;
     private PreparedStatement pst2 = null;
    private ResultSet rs = null;
    private ResultSet rs2 = null;
    private ObservableList<Statics_treatment_feedback> data;
    private ObservableList<Statics_treatment_feedback> data2;
    
        @FXML
    private TextField statics_treatment_feedback_field;
    @FXML
    private Button statics_treatment_feedback_menubtn;
    @FXML
    private TextField statics_diseases_panel_search_field;
    @FXML
    private TableView<Statics_treatment_feedback> treatmentFeedbackCommulativeTable;
    @FXML
    private TableColumn<?, ?> CtreatmentFeedbackId;
    @FXML
    private TableColumn<?, ?> CconsultId;
    @FXML
    private TableColumn<?, ?> CsuccessRate;
    @FXML
    private TableColumn<?, ?> CcommulativeRate;
    private TableView<Statics_treatment_feedbackDependent> treatmentFeedbackDependentTable;
    private TableColumn<?, ?> DtreatmentFeedbackId;
    private TableColumn<?, ?> DconsultId;
    private TableColumn<?, ?> DsuccessRate;
    private TableColumn<?, ?> DcommulativeRate;
    @FXML
    private TableView<Statics_treatment_feedback> treatmentFeedbackCommulativeTable1;
    @FXML
    private TableColumn<?, ?> CtreatmentFeedbackId1;
    @FXML
    private TableColumn<?, ?> CconsultId1;
    @FXML
    private TableColumn<?, ?> CsuccessRate1;
    @FXML
    private TableColumn<?, ?> CcommulativeRate1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           con =dba.DBConnection.immunehistoryConnection();
           con2 =dba.DBConnection.immunehistoryConnection();
                data = FXCollections.observableArrayList();
                 data2 = FXCollections.observableArrayList();
               settreatmentFeedbackCommulativeTable();
             // settreatmentFeedbackDependentTable();
              settreatmentFeedbackCommulativeTable1();

               
              //  searchDrugList();
        
    }   
    
       private void settreatmentFeedbackCommulativeTable(){

    CtreatmentFeedbackId.setCellValueFactory(new PropertyValueFactory<>("TID"));
    CconsultId.setCellValueFactory(new PropertyValueFactory<>("CID"));
    CsuccessRate.setCellValueFactory(new PropertyValueFactory<>("SuccessRate"));
    CcommulativeRate.setCellValueFactory(new PropertyValueFactory<>("CDRate"));
    }
     
//          private void settreatmentFeedbackDependentTable(){
//
//    DtreatmentFeedbackId.setCellValueFactory(new PropertyValueFactory<>("dTID"));
//    DconsultId.setCellValueFactory(new PropertyValueFactory<>("dCID"));
//    DsuccessRate.setCellValueFactory(new PropertyValueFactory<>("dSuccessRate"));
//    DcommulativeRate.setCellValueFactory(new PropertyValueFactory<>("dCDRate"));
//    }   
       
           private void settreatmentFeedbackCommulativeTable1(){

    CtreatmentFeedbackId1.setCellValueFactory(new PropertyValueFactory<>("TID"));
    CconsultId1.setCellValueFactory(new PropertyValueFactory<>("CID"));
    CsuccessRate1.setCellValueFactory(new PropertyValueFactory<>("SuccessRate"));
    CcommulativeRate1.setCellValueFactory(new PropertyValueFactory<>("CDRate"));
    }
        
    
    private void loadDataFromtreatmentFeedbackCommulativeTable(){
          data.clear();
          try {
              pst = con.prepareStatement("select treatment_feedback_id,consult_id, success_rate,\n" +
"       ROUND( AVG(success_rate) OVER(ORDER BY success_rate),3) AS average_success_rate\n" +
"from treatment_feedback\n" +
"where consult_id ="+statics_treatment_feedback_field.getText()+"");
              rs = pst.executeQuery();
              while(rs.next()){
                data.add(new Statics_treatment_feedback(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
              }
          } catch (SQLException ex) {
              Logger.getLogger(Statics_treatment_feedbackController.class.getName()).log(Level.SEVERE, null, ex);
          }
           treatmentFeedbackCommulativeTable.setItems(data);
           
           // loadDataFromtreatmentFeedbackDependentTable();
    }
    
    
     private void loadDataFromtreatmentFeedbackDependentTable(){
          data2.clear();
         try {
              pst = con.prepareStatement("select treatment_feedback_id,consult_id, success_rate,\n" +
"        ROUND(AVG(success_rate) OVER(ORDER BY treatment_feedback_id\n" +
"        ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING),3) AS average_success_rate\n" +
"from treatment_feedback\n" +
"where consult_id ="+statics_treatment_feedback_field.getText()+"");
              rs = pst.executeQuery();
              while(rs.next()){
                data2.add(new Statics_treatment_feedback(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
              }
          } catch (SQLException ex) {
              Logger.getLogger(Statics_treatment_feedbackController.class.getName()).log(Level.SEVERE, null, ex);
          }
           //treatmentFeedbackDependentTable.setItems(data2);
            treatmentFeedbackCommulativeTable1.setItems(data2);
    }
     
     
     
     
     


    
    
    
    @FXML
    private void statics_treatment_feedback_menubtnOnAction(ActionEvent event) {
        
       loadDataFromtreatmentFeedbackCommulativeTable();
        loadDataFromtreatmentFeedbackDependentTable();
    }

    @FXML
    private void statics_diseases_panel_search_fieldOnAction(ActionEvent event) {
           
    }
    

    @FXML
    private void btnSearchOnAction(ActionEvent event) {
        
        
        
    }
    
}

