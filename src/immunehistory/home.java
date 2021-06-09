/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author joyultimates
 */
public class home implements Initializable {

     private double x, y;
    @FXML
    private AnchorPane home_id;
    @FXML
    private Button usericon;
   
     
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Parent home = FXMLLoader.load(getClass().getResource("statics/login_registration/statics_login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }  
    
    
    
    
     @FXML
    public void clicked()
     {
          try {
              Stage stage = (Stage) usericon.getScene().getWindow();
  
               stage.close();
            Stage primaryStage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("User/Login.fxml"));
            primaryStage.setScene(new Scene(root));
            //set stage borderless
            primaryStage.initStyle(StageStyle.UNDECORATED);
            
            //drag it here
            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                
                primaryStage.setX(event.getScreenX() - x);
                primaryStage.setY(event.getScreenY() - y);
                
            });
            new animatefx.animation.FadeIn(root).play();
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
     public void clicked1()
     {
        try {
            Stage primaryStage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("User/Login.fxml"));
            primaryStage.setScene(new Scene(root));
            //set stage borderless
            primaryStage.initStyle(StageStyle.UNDECORATED);
            
            //drag it here
            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                
                primaryStage.setX(event.getScreenX() - x);
                primaryStage.setY(event.getScreenY() - y);
                
            });
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    
    
    
    
    
    

    @FXML
    private void onClickStaticsHome(ActionEvent event) throws IOException {
      
   
//     FXMLLoader fXMLLoader = new FXMLLoader();
//       fXMLLoader.setLocation(getClass().getResource("statics/login_registration/statics_login.fxml"));
//       
//          
//           Stage stage = new Stage();
//        Scene scene = new Scene(fXMLLoader.load());
//        stage.setScene(scene);
//        stage.setTitle("Static login");
//        stage.show();
    
     Parent home = FXMLLoader.load(getClass().getResource("statics/login_registration/statics_login.fxml"));
     Scene scene = new Scene(home);
     home_id.getChildren().setAll(home);
    }

    
   
 

 

    
   
}
