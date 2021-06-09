/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author joyultimates
 */
public class ImmuneHistory extends Application  {
    
    public static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        ImmuneHistory.stage=stage;
        Parent home = FXMLLoader.load(getClass().getResource("home.fxml"));
      //  Parent home = FXMLLoader.load(getClass().getResource("/immunehistory/statics/statics_home/statics_home.fxml"));
        
        Scene scene = new Scene(home);
        
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
       // stage.initStyle(StageStyle.UTILITY);
       // stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
