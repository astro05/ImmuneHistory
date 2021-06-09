/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author joyultimates
 */
public class EmailValidation {
    
     public static boolean isValidEmail(TextField tf){
         boolean b = false;
         String pattern =  "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*.\\w+([-.]\\w+)*";
    if(tf.getText().matches(pattern))
         b= true;
    return b;
   
    }
    
     public static boolean isValidEmail(TextField tf,Label lb, String errorMessage){
         boolean b = true;
         String msg = null;
    if(!isValidEmail(tf)){
          b = false;
          msg = errorMessage;
    }
    lb.setText(msg);
    return b;
   
    }
   
 
public static boolean isPasswordMatched(PasswordField tf1, PasswordField tf2){
         boolean b = false;
    if(tf1.getText().equals(tf2.getText()))
         b= true;
    return b;
   
    }

 public static boolean isPasswordMatched(PasswordField tf1,PasswordField tf2,Label lb, String errorMessage){
         boolean b = true;
         String msg = null;
    if(!isPasswordMatched(tf1,tf2)){
          b = false;
          msg = errorMessage;
    }
    lb.setText(msg);
    return b;
   
    }
    
     

}
