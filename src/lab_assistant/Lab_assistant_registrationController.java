/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_assistant;

import static immunehistory.ImmuneHistory.stage;
import immunehistory.doctor.Doctor;
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

/**
 * FXML Controller class
 *
 * @author labib
 */
public class Lab_assistant_registrationController implements Initializable {

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
    private Label message_label;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField re_enter_password;

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
    }    
    
    @FXML
    public void sign_up(){
        Assistant assistant = new Assistant();
        assistant.setAssistant_name( name.getText() );
        assistant.setMobile( mobile.getText() );
        assistant.setHospital( hospital.getText() );
        assistant.setEmail( email.getText() );
        assistant.setDegree( (String) degree.getValue() );
        assistant.setAddress( address.getText() );
        assistant.setZipcode( zipcode.getText() );
        if( password.getText().equals( re_enter_password.getText() ) ){
            assistant.setPassword( password.getText() );
        }
        Database.insert_into_assistant(assistant);
    }
    
    @FXML
    public void sign_in_label_clicked() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("lab_assistant_login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void back(MouseEvent event) {
        stage.setScene( previous_page.pop() );
        stage.show();
    }
    
}
