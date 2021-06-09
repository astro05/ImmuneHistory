/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.User;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import animatefx.animation.*;
import static immunehistory.User.ItemController.name;
import immunehistory.home;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Sadman
 */
public class LoginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField phone;
    @FXML
    private Button btn_SignIn;
    @FXML
    private Button btn_SignUp;
    @FXML
    private Label forget_label;
    @FXML
    private TextField username1;
    @FXML
    private TextField phone1;
    @FXML
    private Button btn_SignInFinal;
    @FXML
    private ImageView btn_close;
    @FXML
    private ImageView btn_minimize;
    @FXML
    private ImageView btn_info;
    @FXML
    private Pane pn_signup;
    @FXML
    private Pane pn_signin;
    @FXML
    private ImageView btn_back;
    @FXML
    private Label userlabel;
    @FXML
    private TextField email1;
    @FXML
    private Label phonelabel;
    @FXML
    private Label emaillabel;
    @FXML
    private ImageView icon1;
    @FXML
    private ImageView icon2;
    @FXML
    private ImageView icon3;
    @FXML
    private ImageView loginicon1;
    @FXML
    private ImageView loginicon2;
    @FXML
    private Label loginlabel1;
    @FXML
    private Label loginlabel2;
    @FXML
    private Pane pn_otp;
    @FXML
    private PasswordField passcode;
    @FXML
    private ImageView btn_otp;
    @FXML
    private AnchorPane rootpane;

    String finalName,finalPhone;
    @FXML
    private DatePicker dateBirth;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        
        if(event.getSource().equals(btn_SignUp))
        {
        new ZoomIn(pn_signup).play();
            pn_signup.toFront();
        }
        if(event.getSource().equals(btn_SignInFinal))
        {
            String dateofBirth = dateBirth.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
             ConnectMSSQL cnObj = new ConnectMSSQL();
             cnObj.insertData(username1.getText(), phone1.getText(), email1.getText(),dateofBirth);
        new ZoomIn(pn_signin).play();
            pn_signin.toFront();
        }
        
        if(event.getSource().equals(btn_SignIn))
        {
        String name=username.getText();
        String phoneno=phone.getText();
        String userId;
         ConnectMSSQL cnObj = new ConnectMSSQL();
         if(cnObj.loginValidation(name, phoneno))
         {
              finalName=name;
              finalPhone=phoneno;
              
              FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML.fxml"));;
           FXMLController control=loader.getController();
           control.finalName=finalName;
           control.finalPhone=finalPhone;
           
              loginlabel1.setVisible(true);
               loginlabel1.setText("Username Matches");
              loginlabel1.setTextFill(Color.web("#56ab2f"));
              Image image = new Image(getClass().getResourceAsStream("images/icons8_ok_96px_3.png"));
               loginicon1.setImage(image);
               loginicon1.setVisible(true);
               
               loginlabel2.setVisible(true);
               loginlabel2.setText("Phone Number Matches");
              loginlabel2.setTextFill(Color.web("#56ab2f"));
              Image image2 = new Image(getClass().getResourceAsStream("images/icons8_ok_96px_3.png"));
              loginicon2.setImage(image2);
              loginicon2.setVisible(true);
              
             new SlideInRight(pn_otp).play();
            pn_otp.toFront();
               
         }
         else
         {
             //new animatefx.animation.
             new BounceIn(pn_signin).play();
            pn_signin.toFront();
             loginlabel1.setVisible(true);
              loginlabel1.setText("Username Does Not Match.");
               loginlabel1.setTextFill(Color.web("#ef473a"));
               Image image = new Image(getClass().getResourceAsStream("images/cancel_96px.png"));
               loginicon1.setImage(image);
               loginicon1.setVisible(true);
               
               loginlabel2.setVisible(true);
              loginlabel2.setText("Phone Does Not Match.");
               loginlabel2.setTextFill(Color.web("#ef473a"));
                Image image2 = new Image(getClass().getResourceAsStream("images/cancel_96px.png"));
               loginicon2.setImage(image2);
               loginicon2.setVisible(true);
         }
        }
        
       
    }

    @FXML
    private void handleMouseEvent(MouseEvent event) {
        
        if(event.getSource()==btn_close)
        {
        new animatefx.animation.FadeOut(btn_close.getParent().getParent().getParent().getParent()).play();
        
        
        
        new java.util.Timer().schedule( 
        new java.util.TimerTask() {
            @Override
            public void run() {
                cancel();
                Platform.exit();
                 
            }
        }, 
        1500 
                
                
);
        
      
        }
         if(event.getSource()==btn_otp)
        {
            String password=passcode.getText();
            if(password.equals("123456"))
            {
                new SlideInRight(rootpane).play();
                 rootpane.toBack();
                loginSuccess();
                
            }
        }
        
        
        
         if(event.getSource()==btn_minimize)
        {
            ((Stage)((ImageView)event.getSource()).getScene().getWindow()).setIconified(true);
        }
         
         if(event.getSource()==btn_back)
        {
      new ZoomIn(pn_signin).play();      
      pn_signin.toFront();
        }
         
    }

    @FXML
    private void handleInput(KeyEvent event) {
        
         if(event.getSource()==username)
        {
            
          String name=username.getText();
         
          if(name.equals(""))
          {
              loginlabel1.setVisible(true);
              loginlabel1.setText("Text Field Is Empty");
               loginlabel1.setTextFill(Color.web("#ef473a"));
               Image image = new Image(getClass().getResourceAsStream("images/cancel_96px.png"));
               loginicon1.setImage(image);
               loginicon1.setVisible(true);
          }
          else {
             
              if(name.matches( "[A-Z][a-z]*") || name.contains(" "))
              {
                  loginlabel1.setVisible(true);
               loginlabel1.setText("Looks Good");
              loginlabel1.setTextFill(Color.web("#56ab2f"));
              Image image = new Image(getClass().getResourceAsStream("images/icons8_ok_96px_3.png"));
               loginicon1.setImage(image);
               loginicon1.setVisible(true);
              }
              else
              {loginlabel1.setVisible(true);
              loginlabel1.setText("Username is invalid.");
               loginlabel1.setTextFill(Color.web("#ef473a")); 
               Image image = new Image(getClass().getResourceAsStream("images/cancel_96px.png"));
               loginicon1.setImage(image);
               loginicon1.setVisible(true);
              }
             }
        }
          if(event.getSource()==phone)
        {
            
          String phn=phone.getText();   
          String MobilePattern = "[0-9]{10}";
          if(phn.equals(""))
          {
              loginlabel2.setVisible(true);
              loginlabel2.setText("Phone Field Is Empty");
               loginlabel2.setTextFill(Color.web("#ef473a"));
                Image image = new Image(getClass().getResourceAsStream("images/cancel_96px.png"));
               loginicon2.setImage(image);
               loginicon2.setVisible(true);
         }
          else {
             
              if(isValidMobile(phn))
              {
                  loginlabel2.setVisible(true);
               loginlabel2.setText("Looks Good");
              loginlabel2.setTextFill(Color.web("#56ab2f"));
              Image image = new Image(getClass().getResourceAsStream("images/icons8_ok_96px_3.png"));
              loginicon2.setImage(image);
              loginicon2.setVisible(true);
              }
              else
              {loginlabel2.setVisible(true);
              loginlabel2.setText("Phone number is invalid.");
               loginlabel2.setTextFill(Color.web("#ef473a"));
                Image image = new Image(getClass().getResourceAsStream("images/cancel_96px.png"));
               loginicon2.setImage(image);
               loginicon2.setVisible(true);
                  
              }
             }
    }
         if(event.getSource()==username1)
        {
            
          String name=username1.getText();
           System.out.println(name);
          if(name.equals(""))
          {
              userlabel.setVisible(true);
              userlabel.setText("Text Field Is Empty");
               userlabel.setTextFill(Color.web("#ef473a"));
               Image image = new Image(getClass().getResourceAsStream("images/cancel_96px.png"));
               icon1.setImage(image);
               icon1.setVisible(true);
          }
          else {
             
              if(name.matches( "[A-Z][a-z]*") || name.contains(" "))
              {
                  userlabel.setVisible(true);
               userlabel.setText("Looks Good");
              userlabel.setTextFill(Color.web("#56ab2f"));
              Image image = new Image(getClass().getResourceAsStream("images/icons8_ok_96px_3.png"));
               icon1.setImage(image);
               icon1.setVisible(true);
              }
              else
              {userlabel.setVisible(true);
              userlabel.setText("Username is invalid.");
               userlabel.setTextFill(Color.web("#ef473a")); 
               Image image = new Image(getClass().getResourceAsStream("images/cancel_96px.png"));
               icon1.setImage(image);
               icon1.setVisible(true);
              }
             }
    }
          if(event.getSource()==email1)
        {
            
          String mail=email1.getText();
           System.out.println(name);
           String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
          if(mail.equals(""))
          {
              emaillabel.setVisible(true);
              emaillabel.setText("Email Field Is Empty");
               emaillabel.setTextFill(Color.web("#ef473a"));
                Image image = new Image(getClass().getResourceAsStream("images/cancel_96px.png"));
               icon2.setImage(image);
               icon2.setVisible(true);
         }
          else {
             
              if(mail.matches(regex))
              {
                  emaillabel.setVisible(true);
               emaillabel.setText("Looks Good");
              emaillabel.setTextFill(Color.web("#56ab2f"));
              Image image = new Image(getClass().getResourceAsStream("images/icons8_ok_96px_3.png"));
               icon2.setImage(image);
               icon2.setVisible(true);
              }
              else
              {emaillabel.setVisible(true);
              emaillabel.setText("Email is invalid.");
               emaillabel.setTextFill(Color.web("#ef473a"));
                Image image = new Image(getClass().getResourceAsStream("images/cancel_96px.png"));
               icon2.setImage(image);
               icon2.setVisible(true);
                  
              }
             }
        }
          if(event.getSource()==phone1)
        {
            
          String phn=phone1.getText();
           System.out.println(phn);
          String MobilePattern = "[0-9]{10}";
          if(phn.equals(""))
          {
              phonelabel.setVisible(true);
              phonelabel.setText("Phone Field Is Empty");
               phonelabel.setTextFill(Color.web("#ef473a"));
                Image image = new Image(getClass().getResourceAsStream("images/cancel_96px.png"));
               icon3.setImage(image);
               icon3.setVisible(true);
         }
          else {
             
              if(isValidMobile(phn))
              {
                  phonelabel.setVisible(true);
               phonelabel.setText("Looks Good");
              phonelabel.setTextFill(Color.web("#56ab2f"));
              Image image = new Image(getClass().getResourceAsStream("images/icons8_ok_96px_3.png"));
              icon3.setImage(image);
              icon3.setVisible(true);
              }
              else
              {phonelabel.setVisible(true);
              phonelabel.setText("Phone number is invalid.");
               phonelabel.setTextFill(Color.web("#ef473a"));
                Image image = new Image(getClass().getResourceAsStream("images/cancel_96px.png"));
               icon3.setImage(image);
               icon3.setVisible(true);
                  
              }
             }
    }
         
         
         
    }
     private double x, y;
     
 public void loginSuccess()
 {
     
     
           
        try {
            
            
            Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            
           //control.passeddata("hello", "111");
            
            
            Stage stage = (Stage) pn_otp.getScene().getWindow();
  
               Scene scene=new Scene(root,1024,800);
               stage.setScene(scene);
                stage.show();
            
                
                
                root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
                
            });
            
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
      }
 
private boolean isValidMobile(String phone) {
    if(!Pattern.matches("[a-zA-Z]+", phone)) {
        return phone.length() > 6 && phone.length() <= 13;
    }
    return false;
}
   

    }
    
