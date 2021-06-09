/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.doctor;

import static immunehistory.ImmuneHistory.stage;
import static immunehistory.labib_constants.Constants.previous_page;
import immunehistory.labib_database.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class Doctor_registrationController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField mobile;
    @FXML
    private TextField hospital;
    @FXML
    private TextField email;
    @FXML
    private ComboBox<String> degree;
    @FXML
    private TextField address;
    @FXML
    private TextField zipcode;
    @FXML
    private ComboBox<String> category;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField re_enter_password;
    @FXML
    private Label message_label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<String> degree_options = 
        FXCollections.observableArrayList(
            "MBBS",
            "MD",
            "DO",
            "Student"
        );
        degree.setItems(degree_options);
        
        ObservableList<String> category_options = 
        FXCollections.observableArrayList(
            "Diabetics and Hypertension",
            "Medicine",
            "Liver",
            "Food and Nutritionist",
            "Cancer",
            "Cardiologists",
            "Critical Care Medicine Specialists",
            "Endocrinologists",
            "Gastroenterologists"
        );
        category.setItems(category_options);

    }    
    
    @FXML
    public void sign_in_label_clicked() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("doctor_login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void sign_up(){
        Doctor doctor = new Doctor();
        doctor.setDoctor_name(name.getText() );
        doctor.setMobile( mobile.getText() );
        doctor.setHospital( hospital.getText() );
        doctor.setEmail( email.getText() );
        doctor.setDegree( (String) degree.getValue() );
        doctor.setAddress( address.getText() );
        doctor.setZipcode( zipcode.getText() );
        doctor.setCategory( (String) category.getValue() );
        if( password.getText().equals( re_enter_password.getText() ) ){
            doctor.setPassword( password.getText() );
        }
      
        Database.insert_into_doctor(doctor);
    }   

    @FXML
    private void back(MouseEvent event) {
        stage.setScene( previous_page.pop() );
        stage.show();
    }
}
