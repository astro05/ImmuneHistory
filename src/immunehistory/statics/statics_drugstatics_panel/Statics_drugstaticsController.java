/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.statics.statics_drugstatics_panel;

import immunehistory.statics.statics_drug_panel.statics_drug_panel;
import immunehistory.statics.statics_drug_panel.statics_drug_panelController;
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
public class Statics_drugstaticsController implements Initializable {
    
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<Statics_drugstatics> data;
    private String menubox ="";

    @FXML
    private Button statics_medicine_feedback_menubtn;
    @FXML
    private TextField statics_diseases_panel_search_field;
    @FXML
    private TableView<Statics_drugstatics> medicinefeedbackTable;
    @FXML
    private TableColumn<?, ?> drugName;
    @FXML
    private TableColumn<?, ?> age1;
    @FXML
    private TableColumn<?, ?> age2;
    @FXML
    private TableColumn<?, ?> age3;
    @FXML
    private TableView<?> treatmentFeedbackDependentTable;
    @FXML
    private TableColumn<?, ?> DtreatmentFeedbackId;
    @FXML
    private TableColumn<?, ?> DconsultId;
    @FXML
    private TableColumn<?, ?> DsuccessRate;
    @FXML
    private TableColumn<?, ?> DcommulativeRate;
    @FXML
    private TextField inputage1;
    @FXML
    private TextField inputage2;
    @FXML
    private TextField inputage3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          con =dba.DBConnection.immunehistoryConnection();
                data = FXCollections.observableArrayList();
                setDrugFeedbackTable();
               
    }    
    
     private void setDrugFeedbackTable(){

    drugName.setCellValueFactory(new PropertyValueFactory<>("DrugName"));
    age1.setCellValueFactory(new PropertyValueFactory<>("age1"));
    age2.setCellValueFactory(new PropertyValueFactory<>("age2"));
    age3.setCellValueFactory(new PropertyValueFactory<>("age3"));
    }
     
        private void loadDataFromDatabase(){
         data.clear();
          try {
              pst = con.prepareStatement("Select [medicine_generic_name], ["+inputage1.getText()+"], ["+inputage2.getText()+"], ["+inputage3.getText()+"]\n" +
"from \n" +
"(\n" +
"   Select medicine_generic_name,age,med_success_rate from doc_med_feedback\n" +
") as SourceTable\n" +
"Pivot\n" +
"(\n" +
" AVG(med_success_rate)\n" +
" for age in ([20],[30],[40])\n" +
") as PivotTable");
              rs = pst.executeQuery();
              while(rs.next()){
                data.add(new Statics_drugstatics(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4)));
              }
          } catch (SQLException ex) {
              Logger.getLogger(Statics_drugstaticsController.class.getName()).log(Level.SEVERE, null, ex);
          }
           medicinefeedbackTable.setItems(data);
    }
      

    @FXML
    private void statics_medicine_feedback_menubtnOnAction(ActionEvent event) {
         loadDataFromDatabase();  
        
    }

    @FXML
    private void statics_diseases_panel_search_fieldOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSearchOnAction(ActionEvent event) {
    }
    
}
