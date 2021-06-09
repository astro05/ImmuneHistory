/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.User;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import immunehistory.User.ItemController;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Sadman
 */
public class FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private String userId;
     Image image = null;
    static String  finalName,finalPhone;
    @FXML
    private VBox appointmentlist=null;
    @FXML
    private Button overviewbtn;

    @FXML
    private Button makebtn;

    @FXML
    private Button appointmentbtn;

    @FXML
    private Button prescriptionbtn;

    @FXML
    private Button historybtn;

    @FXML
    private Button signoutbtn;

    @FXML
    private Pane overviewPane;

    @FXML
    private Pane makeAppointmentPane;
    
     @FXML
    private Pane appointmentPane;

    @FXML
    private Pane prescriptionPane;

   
    
   
    public Connection connection;
    @FXML
    private Label UserName;
    @FXML
    private TextField usernamefield;
    @FXML
    private TextField emailfield;
    @FXML
    private TextField mobilefield;
    @FXML
    private TextField dateofbirthfield;
    @FXML
    private TextArea addressfield;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_update;
    @FXML
    private TextField zipfield;
    @FXML
    private TextField genderfield;
    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private StackPane sidePane;
    @FXML
    private VBox navBar;
    @FXML
    private Ellipse userPhoto;
    @FXML
    private Button btn_upload;
    static TextField problem_field;
    @FXML
    private VBox tagBox;
    @FXML
    private TextField doctor_field;
    @FXML
    private CheckBox checkSuggestion;
    
    
    //ArrayList<String>  doctorSuggestion=new ArrayList<String>();
    Set<String> doctorSuggestion = new HashSet<>();
SuggestionProvider<String> provider;

    Set<String> doctorSuggestionOnDisease = new HashSet<>();
 
    String docSearch="";
    URL URL;
    ResourceBundle RB;
    boolean alertShow=true;
    
    
    @FXML
    private CheckBox checkProblems;
    @FXML
    private Button btn_details;
    @FXML
    private Pane doctorViewPane;
    @FXML
    private VBox doctorList;
    @FXML
    private TextField docSearchField;
    @FXML
    private ComboBox comboCategory;
    @FXML
    private ComboBox comboTime;
    @FXML
    private DatePicker consultDatePicker;
    @FXML
    private Button submit_btn;
    @FXML
    private Pane user_history_pane;
    @FXML
    private VBox vaxineList;
    @FXML
    private Button feedbackBtn;
    @FXML
    private Pane feedbackPane;
    @FXML
    private CheckBox checkExperience;
    @FXML
    private Label total_doctors;
    @FXML
    private Label special_doctors;
    @FXML
    private Label total_hospital;
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
       URL=url;
       RB=rb;
      
 
 
        UserName.setText(finalName);
        
        
       ConnectMSSQL cnObj = new ConnectMSSQL();
       
       
      Object[] docObj=cnObj.fetchDoctorList();
       ArrayList<String> doctorName = new ArrayList<String>();
       ArrayList<String> degree = new ArrayList<String>();
        ArrayList<String>doctorAge  = new ArrayList<String>();
       ArrayList<String> doctorCategory = new ArrayList<String>();
       ArrayList<String> hospital= new ArrayList<String>();
        ArrayList<String> doctorEmail = new ArrayList<String>();
       ArrayList<String> doctorPhone = new ArrayList<String>();
       
       doctorName=(ArrayList)docObj[0];
       degree=(ArrayList)docObj[1];
       doctorAge=(ArrayList)docObj[2];
       doctorCategory=(ArrayList)docObj[3];  
       hospital=(ArrayList)docObj[4];
       doctorEmail=(ArrayList)docObj[5];
       doctorPhone=(ArrayList)docObj[6];

       
      
        for(int i=0;i<doctorName.size();i++)
        {
            doctorSuggestion.add(doctorName.get(i)+" "+degree.get(i));
        }
         //autoCompletions=(Set<String>) doctorSuggestion;
        
        //TextFields.bindAutoCompletion(doctor_field, doctorSuggestion);
        
       
       
      /* JFXButton button=new JFXButton("Ok");
       button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{
        dialog.close();   
       }
       );
       JFXDialogLayout dialoglayout=new JFXDialogLayout();
       dialoglayout.setBody(new Text("PLEASE FILL UP YOUR DATA"));
       JFXDialog dialog =new JFXDialog(sidePane, dashboardPane, JFXDialog.DialogTransition.CENTER);*/
       ArrayList<String> data=cnObj.fetchData(finalPhone);
       
       userId=data.get(0);
       usernamefield.setText(data.get(1));
       emailfield.setText(data.get(2));
       mobilefield.setText(data.get(3));
       dateofbirthfield.setText(data.get(4));
       genderfield.setText(data.get(5));
       addressfield.setText(data.get(6));
       zipfield.setText(data.get(7));
       String image_url=data.get(8);
       Image userImage = new Image(getClass().getResourceAsStream(image_url));
       userPhoto.setStroke(Color.web("#818a84"));
       userPhoto.setFill(new ImagePattern(userImage));
       userPhoto.setEffect(new DropShadow(+25d,0d,2d,Color.web("#818a84")));
       //Query 1
       
     if((data.get(5)==null || data.get(6)==null || data.get(7)==null)  && alertShow)  
     {
       BoxBlur blur=new BoxBlur(3,3,3);
 JFXDialogLayout content= new JFXDialogLayout();
content.setHeading(new Text("Info"));
content.setBody(new Text("Please Set Up The Form"));
StackPane stackpane = new StackPane();
JFXDialog dialog =new JFXDialog(sidePane, content, JFXDialog.DialogTransition.CENTER);
JFXButton button=new JFXButton("Okay");
button.setOnAction(new EventHandler<ActionEvent>(){
    @Override
    public void handle(ActionEvent event){
        dialog.close();
       overviewPane.setEffect(null);
    }
});
content.setActions(button);
dialog.show();
dialog.setOnDialogClosed((JFXDialogEvent event1) ->
        {
        overviewPane.setEffect(null);
        }
);
overviewPane.setEffect(blur);
       
alertShow=false;
     } 
       
       
       
       
       int rowNum=cnObj.rowList("SELECT COUNT (ap_sl_no) as row FROM appointment INNER JOIN consult ON consult.consult_id=appointment.consult_id INNER JOIN doctor ON doctor.doctor_id=consult.doctor_id where user_id="+userId);
       
       //Query2
      Object[] obj ;
      obj= cnObj.showAppointment("SELECT ap_sl_no,appointment.consult_id,doctor_name,category,FORMAT (date_of_appointment_taken, 'dd/MM/yyyy')AS date_of_appointment_taken,FORMAT (date_of_consult, 'dd/MM/yyyy')AS date_of_consult,appoint_active FROM appointment INNER JOIN consult ON consult.consult_id=appointment.consult_id INNER JOIN doctor ON doctor.doctor_id=consult.doctor_id where user_id="+userId);
      
      
      ArrayList<String> SLno = new ArrayList<String>();
       SLno=(ArrayList)obj[0];
      ArrayList<String> ConsultId = new ArrayList<String>();
      ConsultId=(ArrayList)obj[1];
     ArrayList<String> DoctorName = new ArrayList<String>();
     DoctorName=(ArrayList)obj[2];
     ArrayList<String>  AppointmentDate= new ArrayList<String>();
     AppointmentDate=(ArrayList)obj[3];
     ArrayList<String> ConsultDate = new ArrayList<String>();
     ConsultDate=(ArrayList)obj[4];
     ArrayList<String> Status = new ArrayList<String>();
     Status=(ArrayList)obj[5];   
     ArrayList<String> Category = new ArrayList<String>();
     Category=(ArrayList)obj[6];           
     
     appointmentlist.getChildren().clear();
             Node[] nodes= new Node[rowNum];
            int i=0;
            for(i=0;i<rowNum;i++)
            {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("Item.fxml"));
                try {
                    Parent root = (Parent) loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }

            ItemController secController=loader.getController();
            secController.serialno=SLno.get(i);
            secController.name=DoctorName.get(i);
            secController.consultid=ConsultId.get(i);
            secController.apdate=AppointmentDate.get(i);
            secController.condate=ConsultDate.get(i);
            secController.stat=Status.get(i);
            secController.cat=Category.get(i);
            secController.initialize(url, rb);
                try {
                    nodes[i]=(Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
                    final int j = i;
                     nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #9C9AA6");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #FFF");
                });
                } catch (IOException ex) {
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                appointmentlist.getChildren().add(nodes[i]);
            }
           
            if(docSearch.isEmpty())
             { 
                 doctorList.getChildren().clear();
            rowNum=cnObj.rowList("SELECT COUNT (doctor_id) as row from doctor");
        System.out.println(rowNum);
             Node[] nodes2= new Node[rowNum];
             
            for(i=0;i<rowNum;i++)
            {
                FXMLLoader loader2=new FXMLLoader(getClass().getResource("DoctorList.fxml"));
                try {
                    Parent root = (Parent) loader2.load();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
             
             DoctorListController docController=loader2.getController();
            docController.no=Integer.toString(i+1);
            docController.Doctor_Name=doctorName.get(i);
            docController.Doctor_Age=doctorAge.get(i);
            docController.Degree=degree.get(i);
            docController.Category=doctorCategory.get(i);
            docController.Hospital=hospital.get(i);
            docController.Email=doctorEmail.get(i);
            docController.Phone=doctorPhone.get(i);
            docController.initialize(url, rb);
             
                try {
                    
                    nodes2[i]=(Node)FXMLLoader.load(getClass().getResource("DoctorList.fxml"));
                    final int j = i;
                    final String doctor=doctorName.get(j);
                    final String category=doctorCategory.get(j);
                     nodes2[i].setOnMouseEntered(event -> {
                    nodes2[j].setStyle("-fx-background-color : #9C9AA6");
                });
                nodes2[i].setOnMouseExited(event -> {
                    nodes2[j].setStyle("-fx-background-color : #FFF");
                });
                nodes2[i].setOnMouseClicked(event -> {
                   makeAppointmentPane.toFront();
                    comboCategory.setValue(category);
                   doctor_field.setText(doctor);
                });
                
                } catch (IOException ex) {
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                doctorList.getChildren().add(nodes2[i]);
                total_doctors.setText(Integer.toString(rowNum));
                special_doctors.setText(Integer.toString(cnObj.specializedDoctorNumber()));
            }
             }
            else{
                doctorList.getChildren().clear();
                if(checkExperience.isSelected())
                {
                    docObj=cnObj.fetchDoctorList(docSearch,"11");
                }
                else
                {
                    docObj=cnObj.fetchDoctorList(docSearch);
                }
               
                ArrayList<String> docname,docage,docdegree,doccat,dochospital,docemail,docphone=new ArrayList();
                 docname=(ArrayList)docObj[0];
                 docage=(ArrayList)docObj[2];
                 doccat=(ArrayList)docObj[3];
                 docdegree=(ArrayList)docObj[1];
                 dochospital=(ArrayList)docObj[4];
                 docemail=(ArrayList)docObj[5];
                 docphone=(ArrayList)docObj[6];
                 
               rowNum=docname.size();
        System.out.println(rowNum);
             Node[] nodes2= new Node[rowNum];
             
            for(i=0;i<rowNum;i++)
            {
                FXMLLoader loader2=new FXMLLoader(getClass().getResource("DoctorList.fxml"));
                try {
                    Parent root = (Parent) loader2.load();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
             
             DoctorListController docController=loader2.getController();
            docController.no=Integer.toString(i+1);
            docController.Doctor_Name=docname.get(i);
            docController.Doctor_Age=docage.get(i);
            docController.Degree=docdegree.get(i);
            docController.Category=doccat.get(i);
            docController.Hospital=dochospital.get(i);
            docController.Email=docemail.get(i);
            docController.Phone=docphone.get(i);
            docController.initialize(url, rb);
             
                try {
                    
                    nodes2[i]=(Node)FXMLLoader.load(getClass().getResource("DoctorList.fxml"));
                    final int j = i;
                    final String doctor=docname.get(j);
                    final String category=doccat.get(j);
                     nodes2[i].setOnMouseEntered(event -> {
                    nodes2[j].setStyle("-fx-background-color : #9C9AA6");
                });
                nodes2[i].setOnMouseExited(event -> {
                    nodes2[j].setStyle("-fx-background-color : #FFF");
                });
                nodes2[i].setOnMouseClicked(event -> {
                   makeAppointmentPane.toFront();
                   doctor_field.setText(doctor);
                });
                
                  nodes2[i].setOnMouseClicked(event -> {
                   makeAppointmentPane.toFront();
                    comboCategory.setValue(category);
                   doctor_field.setText(doctor);
                   
                });
                
                } catch (IOException ex) {
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                doctorList.getChildren().add(nodes2[i]);
            }
            }
             vaxineList.getChildren().clear();
            
            Object[] vaxineDetails=cnObj.fetchVaxinationDetails();
            ArrayList<String> vaxineId=(ArrayList)vaxineDetails[0];
             ArrayList<String> vaxineName=(ArrayList)vaxineDetails[1];
              ArrayList<String> vaxinationDate=(ArrayList)vaxineDetails[2];
              int row=vaxineId.size();
              Node[] nodes3= new Node[row];
             i=0;
            for(i=0;i<row;i++)
            {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("VaxineList.fxml"));
                try {
                    Parent root = (Parent) loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                VaxineListController vacController=loader.getController();
            vacController.no=Integer.toString(i+1);
            vacController.vId=vaxineId.get(i);
            vacController.vName=vaxineName.get(i);
            vacController.vDate=vaxinationDate.get(i);
           
            vacController.initialize(url, rb);

                try {
                    nodes3[i]=(Node)FXMLLoader.load(getClass().getResource("VaxineList.fxml"));
                    final int j = i;
                     nodes3[i].setOnMouseEntered(event -> {
                    nodes3[j].setStyle("-fx-background-color : #9C9AA6");
                });
                nodes3[i].setOnMouseExited(event -> {
                    nodes3[j].setStyle("-fx-background-color : #FFF");
                });
                } catch (IOException ex) {
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                vaxineList.getChildren().add(nodes3[i]);
            }
            /* Node[] nodes= new Node[rowNum];
            int i=0;
            while (resultSet.next()) {
                
                
                 try {
                 try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Item.fxml"));
            Parent root = (Parent) loader.load();

            ItemController secController=loader.getController();
            secController.serialno=resultSet.getString("ap_sl_no");
            secController.name=resultSet.getString("doctor_name");
            secController.consultid=resultSet.getString("consult_id");
            secController.apdate=resultSet.getString("date_of_appointment_taken");
            secController.condate=resultSet.getString("date_of_consult");
            secController.stat=resultSet.getString("appoint_active");
            secController.initialize(url, rb);

            
        } catch (IOException e) {
            e.printStackTrace();
        }
                nodes[i]=(Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
                appointmentlist.getChildren().add(nodes[i]);
                i++;
            } catch (IOException ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    tagBox.getChildren().addAll(tagBar);
        
     
     comboCategory.setValue("Choose Doctor Category");
  comboTime.setValue("Choose Consult Time");
  ArrayList<String> categories=new ArrayList();
  categories=cnObj.fetchDoctorCategories();
  for(i=0;i<categories.size();i++)
  comboCategory.getItems().addAll(categories.get(i));   
    }    
    @FXML
     public void handleClicks(ActionEvent actionEvent) throws MalformedURLException, IOException, SQLException, ClassNotFoundException {
        if (actionEvent.getSource() == prescriptionbtn ) {
            prescriptionPane.toFront();

        }
        if (actionEvent.getSource() == feedbackBtn ) {
            feedbackPane.toFront();

        }
        if (actionEvent.getSource() == overviewbtn ) {
            overviewPane.toFront();
        }
        if (actionEvent.getSource() == appointmentbtn ) {
            appointmentPane.setStyle("-fx-background-color : #FFFF");
            appointmentPane.toFront();
        }
         if (actionEvent.getSource() == makebtn ) {
            makeAppointmentPane.toFront();
        }
        
         if (actionEvent.getSource() == signoutbtn ) {
            logoutSuccess();
        }
          if (actionEvent.getSource() == btn_details ) {
            doctorViewPane.toFront();
        }
          if (actionEvent.getSource() == historybtn ) {
            user_history_pane.toFront();
        }
         
          if (actionEvent.getSource() == submit_btn ) {
              
              String doctor=doctor_field.getText();
              String category=comboCategory.getValue().toString();
              String time=comboTime.getValue().toString();
              //String consult_date=consultDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
              System.out.println(doctor);
              System.out.println(category);
            if(doctor.equals("")||category.equals("Choose Doctor Category") || time.equals("Choose Doctor Time")||consultDatePicker.getValue()==null)
            {
               showAlert("Empty Appointment Submission Please Try  Again",false);
              
             }
            else 
            {
                
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();  
                String appointment_date=now.toString();
                String[] doc = doctor.split(" ");
                
                String[] parts = time.split("-");
                String part1 = parts[0]; 
                String part2 = parts[1]; 
                String splittedTime=parts[0]+":00 "+parts[1].substring(parts[1].length()-2,parts[1].length());
                
                String consult_date=consultDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
                System.out.println(consult_date);
                String concated_consult_date=consult_date+" "+splittedTime;
                  System.out.println(concated_consult_date);
                System.out.println(category);
                System.out.println(time);
                ConnectMSSQL cnObj=new ConnectMSSQL();
               String doctorid=cnObj.fetchDoctorId(doc[0]+" "+doc[1], category);
                cnObj.insertAppointment(Integer.parseInt(userId),Integer.parseInt(doctorid),appointment_date,concated_consult_date);
                showAlert("Congratulation You have successfully made an Appointment",true);
               
            }
          }
         
         if (actionEvent.getSource() == btn_edit )
         {
             btn_edit.setVisible(false);
              btn_update.setVisible(true);
              btn_upload.setVisible(true);
              usernamefield.setEditable(true);
       emailfield.setEditable(true);
       mobilefield.setEditable(true);
       dateofbirthfield.setEditable(true);
       genderfield.setEditable(true);
       addressfield.setEditable(true);
       zipfield.setEditable(true);
         }
        
         if (actionEvent.getSource() == btn_upload )
         {
                 FileChooser chooser = new FileChooser();
    chooser.setTitle("Open File");
    chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif")); 
    File file = chooser.showOpenDialog(new Stage());
    if(file != null) {
        String imagepath = file.toURI().toURL().toString();
        System.out.println("file:"+imagepath);
         image = new Image(imagepath);
       userPhoto.setStroke(Color.web("#818a84"));
       userPhoto.setFill(new ImagePattern(image));
       userPhoto.setEffect(new DropShadow(+25d,0d,2d,Color.web("#818a84")));
    }
    else
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Please Select a File");
        /*alert.setContentText("You didn't select a file!");*/
        alert.showAndWait();
    }

         }
          if (actionEvent.getSource() == btn_update )
         {
       btn_edit.setVisible(true);
       btn_update.setVisible(false);
       btn_upload.setVisible(false);
       usernamefield.setEditable(false);
       emailfield.setEditable(false);
       mobilefield.setEditable(false);
       dateofbirthfield.setEditable(false);
       genderfield.setEditable(false);
       addressfield.setEditable(false);
       zipfield.setEditable(false);
       
       File outputfile = new File("src/immunehistory/User/userimages/"+userId+".png");
         BufferedImage BI=SwingFXUtils.fromFXImage(image, null);
          ImageIO.write(BI, "png",outputfile);
       String imgURL="userimages/"+userId+".png";
       ConnectMSSQL cnObj = new ConnectMSSQL();
            try {
                cnObj.updateData(usernamefield.getText(), emailfield.getText(), mobilefield.getText(), dateofbirthfield.getText(), genderfield.getText(), addressfield.getText(), zipfield.getText(),imgURL,finalPhone);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        
    }
       private double x, y;
 void logoutSuccess()
 {
      try {
            
           
            
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            
           //control.passeddata("hello", "111");
            
            
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
  
               Scene scene=new Scene(root);
               stage.setScene(scene);
               new animatefx.animation.BounceInRight(root).play();
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
TagBar tagBar = new TagBar();
    private void keyPressCheck(KeyEvent event) {
         String problems=tagBar.getText();
        if(event.getCode()==KeyCode.ENTER)
         tagBar.getTags().add(problems);
        ConnectMSSQL cnObj=new ConnectMSSQL();
        ArrayList<String> diseaseDoctors=new ArrayList();
        diseaseDoctors=cnObj.fetchData("aa");
        
        for(int i=0;i<diseaseDoctors.size();i++)
        {
            doctorSuggestionOnDisease.add(diseaseDoctors.get(i));
        }
    }

    @FXML
    private void handleCheckbox(ActionEvent event) {
        
        checkProblems.setDisable(false);
         
        if(checkSuggestion.isSelected())
        {
             provider= SuggestionProvider.create(doctorSuggestion);
            new AutoCompletionTextFieldBinding<>(doctor_field, provider);
       
      //provider.addPossibleSuggestions(filteredAutoCompletions);

        }
        else
        {
           provider.clearSuggestions();
            checkProblems.setDisable(true);
            btn_details.setDisable(false);
            checkProblems.setSelected(false);
        }
        
    }
    
     @FXML
    private void handleCheckbox2(ActionEvent event) {
        if(checkProblems.isSelected())
        {
           // TextFields.bindAutoCompletion(doctor_field, "qq");
            comboCategory.setDisable(true);
            comboCategory.setStyle("-fx-opacity: 1");
            comboCategory.setValue(TagBar.category);
            btn_details.setDisable(true);
             doctor_field.setText("");
            doctor_field.setDisable(false);
             provider.clearSuggestions();
             doctorSuggestionOnDisease=TagBar.doctorSuggestionOnDisease;
             System.out.println(TagBar.doctorSuggestionOnDisease);
            provider.addPossibleSuggestions(doctorSuggestionOnDisease);
        }
        
         else
        {
           provider.clearSuggestions();
           comboCategory.setDisable(false);
           doctor_field.setDisable(true);
            btn_details.setDisable(false);
        }
        
        
    }

    @FXML
    private void handleSearch(KeyEvent event) {
        docSearch=docSearchField.getText().toString();
        initialize(URL,RB);
    }

    boolean categorySelected=false;
    
    @FXML
    private void handleComboBox(ActionEvent event) {
        
        
        String value=comboCategory.getValue().toString();
        ConnectMSSQL cnObj=new ConnectMSSQL();
        
        doctorSuggestion = new HashSet<>();
        
        
      Object[] ob=cnObj.fetchDoctorBasedOnCategory(value);
      
       ArrayList<String> doctorName = new ArrayList<String>();
       ArrayList<String> degree = new ArrayList<String>();
       doctorName=(ArrayList)ob[0];
       degree=(ArrayList)ob[1];
        for(int i=0;i<doctorName.size();i++)
        {
            doctorSuggestion.add(doctorName.get(i)+" "+degree.get(i));
        }
        if(checkSuggestion.isSelected())
        {
           provider.clearSuggestions();
           provider.addPossibleSuggestions(doctorSuggestion);
        }
        
        doctor_field.setDisable(false);
        
        ArrayList<String> availableTime=new ArrayList();
  availableTime=cnObj.fetchDoctorCategories();
  
   categorySelected=true;    
   comboTime.getItems().clear();
   comboTime.setDisable(true);
   consultDatePicker.setDisable(true);
   comboTime.setValue("Choose Consult Time");
   doctor_field.setText("");
    }

    @FXML
    private void doctorHandle(ActionEvent event) {
        System.out.println("True");
    }

    @FXML
    private void doctorSelect(javafx.scene.input.MouseEvent event) {
        if(categorySelected)
        {    
        doctor_field.setDisable(true);
        consultDatePicker.setDisable(false);
        comboTime.setDisable(false);
        
        String[] split = doctor_field.getText().split("\\s+");
        String selectedName=split[0]+" "+split[1];
        String selectedCategory=comboCategory.getValue().toString();
            
        ConnectMSSQL cnObj=new ConnectMSSQL();
        ArrayList<String> availableTime=new ArrayList();
        availableTime=cnObj.fetchDoctorTime(selectedName, selectedCategory);
        for(int i=0;i<availableTime.size();i++)
  comboTime.getItems().addAll(availableTime.get(i));   
        }
    }
void showAlert(String alert,boolean condition)
{
 BoxBlur blur=new BoxBlur(3,3,3);
 JFXDialogLayout content= new JFXDialogLayout();
content.setHeading(new Text("Info"));
content.setBody(new Text(alert));
StackPane stackpane = new StackPane();
JFXDialog dialog =new JFXDialog(sidePane, content, JFXDialog.DialogTransition.CENTER);
JFXButton button=new JFXButton("Okay");
button.setOnAction(new EventHandler<ActionEvent>(){
    @Override
    public void handle(ActionEvent event){
        dialog.close();
       overviewPane.setEffect(null);
       
    }
});
content.setActions(button);
dialog.show();
dialog.setOnDialogClosed((JFXDialogEvent event1) ->
        {
        overviewPane.setEffect(null);
        if(condition)
        {
        comboCategory.setValue("Choose Doctor Category");
       comboTime.setValue("Choose Doctor Time");
       doctor_field.setText("");
       tagBar.clearText();
       consultDatePicker.getEditor().clear();
       initialize(URL,RB);
       overviewPane.toFront();
        }
        }
);
overviewPane.setEffect(blur);    
}

    @FXML
    private void handleRating(javafx.scene.input.MouseEvent event) {
    }

    @FXML
    private void handleCheckbox3(ActionEvent event) {
    }
   
    
}
