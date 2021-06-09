/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author joyultimates
 */
public class TextFieldValidation {
   
    public static boolean isTextFieldNoEmpty(TextField tf){
         boolean b = false;
    if(tf.getText().length() !=0 || tf.getText().isEmpty())
         b= true;
    return b;
   
    }
    
     public static boolean isTextFieldNoEmpty(TextField tf,Label lb, String errorMessage){
         boolean b = true;
         String msg = null;
    if(!isTextFieldNoEmpty(tf)){
          b = false;
          msg = errorMessage;
    }
    lb.setText(msg);
    return b;
   
    }
     
 public static boolean istextFieldTypeNumber(TextField tf){
           boolean b = false;
           if(tf.getText().matches("([0-9]+(\\.[0-9]+)?)+"))
           b = true;   
 return b;
 } 
 
public static boolean istextFieldTypeNumber(TextField tf,Label lb, String errorMessage){
         boolean b = true;
         String msg = null;
    if(!istextFieldTypeNumber(tf)){
          b = false;
          msg = errorMessage;
    }
    lb.setText(msg);
    return b;
   
    }
 
     
    
    
}
