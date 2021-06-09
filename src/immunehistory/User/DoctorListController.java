/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.User;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Sadman
 */
public class DoctorListController implements Initializable {

    static String no,Doctor_Name,Doctor_Age,Degree,Category,Hospital,Email,Phone;
    
    
    @FXML
    private Label serial_no;
    @FXML
    private Label doctor_name;
    @FXML
    private Label doctor_age;
    @FXML
    private Label degree;
    @FXML
    private Label category;
    @FXML
    private Label hospital;
    @FXML
    private Label doc_email;
    @FXML
    private Label doctor_phone;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         serial_no.setText(no);
        doctor_name.setText(Doctor_Name);
        doctor_age.setText(Doctor_Age);
        degree.setText(Degree);
        category.setText(Category);
         hospital.setText(Hospital);
        doc_email.setText(Email);
        doctor_phone.setText(Phone);
    }    
    
}
