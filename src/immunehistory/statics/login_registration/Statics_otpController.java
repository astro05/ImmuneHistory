/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.statics.login_registration;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static com.sun.javaws.ui.SplashScreen.hide;
import static immunehistory.statics.login_registration.Statics_registrationController.statics_email;
import static immunehistory.statics.login_registration.Statics_registrationController.statics_password;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author joyultimates
 */
public class Statics_otpController implements Initializable {

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs;
    
    public static  int otpmatched=0;
    @FXML
    private Label lblOtpError;
    @FXML
    private JFXPasswordField statics_otp_field;
    @FXML
    private Button statics_otpSubmit_btn;
    @FXML
    private JFXTextField hide;
    @FXML
    private JFXTextField emailtest;
    @FXML
    private Label lblotpNotMatched;
    @FXML
    private AnchorPane statics_otp_id;

    
    
    
    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           con =dba.DBConnection.immunehistoryConnection();
           Random();
           sendotp();
    }    
    
    
    public void Random(){
     Random rd=new Random();
     hide.setText(""+rd.nextInt(10000+1));
     emailtest.setText(statics_email);
    }
    public void sendotp(){
      
        Properties props=new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port",465);
        props.put("mail.smtp.user","moniruzzamanjoy05@gmail.com");
        props.put("mail.smtp.auth",true);
        props.put("mail.smtp.starttls.enable",true);
        props.put("mail.smtp.debug",true);
        props.put("mail.smtp.socketFactory.port",465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback",false); 
        
        try {             
                Session session = Session.getDefaultInstance(props, null);
                session.setDebug(true);
                MimeMessage message = new MimeMessage(session);
                message.setText("Your OTP is " + hide.getText());
                message.setSubject("OTP For Immune History");
                message.setFrom(new InternetAddress(""));
                message.addRecipient(RecipientType.TO, new InternetAddress(statics_email.trim()));
                message.saveChanges();
                try
                {
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com","","");
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                
//                jTextField8.setEditable(true);
//                jPanel4.setVisible(false);
//                jPanel6.setVisible(true);
//                
                //JOptionPane.showMessageDialog(null,"OTP has send to your Email id"); 
                }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Please check your internet connection");
                }              
            
        } catch (Exception e) {
            e.printStackTrace();  
            JOptionPane.showMessageDialog(null,e);
        }  
        
    }
    
    
    

    @FXML
    private void statics_otp_home_id(MouseEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("/immunehistory/home.fxml"));
     Scene scene = new Scene(home);
     statics_otp_id.getChildren().setAll(home);
    }

    @FXML
    private void statics_otp_submitOnAction(ActionEvent event) throws IOException {
        if(hide.getText().equals(statics_otp_field.getText())){
    
                     try {
       
       String insert ="INSERT INTO statics_user(statics_user_email,statics_user_password)Values(?,?)" ;
        
                 pst = con.prepareStatement(insert);
            
       pst.setString(1,statics_email);
       pst.setString(2,statics_password);
       
       int i = pst.executeUpdate();
       if(i==1){
         Alert alert = new Alert(Alert.AlertType.INFORMATION.CONFIRMATION,"Registration successfully",ButtonType.OK);
         alert.show();
         
         Parent home = FXMLLoader.load(getClass().getResource("statics_login.fxml"));
         Scene scene = new Scene(home);
         statics_otp_id.getChildren().setAll(home);
         
       }
        } catch (SQLException ex) {
                 Logger.getLogger(Statics_registrationController.class.getName()).log(Level.SEVERE, null, ex);
        Alert alert = new Alert(Alert.AlertType.INFORMATION.WARNING,"Something Wrong",ButtonType.OK);
         alert.show();     
        }
       
           
           
        }else{
   
           lblotpNotMatched.setText("OTP Not Matched");
        }
        
    }
    
}
