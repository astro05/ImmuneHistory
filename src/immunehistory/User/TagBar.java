/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author Sadman
 */
public class TagBar extends HBox {

    private final ObservableList<String> tags;
    private final TextField inputTextField;
    static Set<String> doctorSuggestionOnDisease = new HashSet<>();
    static String category;
    public ObservableList<String> getTags() {
        return tags;
    }
 public void clearText()
{
 tags.removeAll(tags);
}   
public String getText()
{
  String text;
  text=this.inputTextField.getText();
  return text;
}
    public TagBar() {
        getStyleClass().setAll("tag-bar");
        getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        tags = FXCollections.observableArrayList();
        inputTextField = new TextField();
        inputTextField.setOnAction(evt -> {
            String text = inputTextField.getText();
            if (!text.isEmpty() && !tags.contains(text)) {
                tags.add(text);
                inputTextField.clear();
            ConnectMSSQL cnObj=new ConnectMSSQL();
        ArrayList<String> diseaseDoctors=new ArrayList();
        diseaseDoctors=cnObj.fetchDoctorBasedOnDisease(text);
        
        doctorSuggestionOnDisease = new HashSet<>();
        
        for(int i=0;i<diseaseDoctors.size()-1;i++)
        {
            doctorSuggestionOnDisease.add(diseaseDoctors.get(i));
        }
        category=diseaseDoctors.get(diseaseDoctors.size()-1);
                System.out.println(category);
            }
        });

        inputTextField.prefHeightProperty().bind(this.heightProperty());
        HBox.setHgrow(inputTextField, Priority.ALWAYS);
        inputTextField.setBackground(null);

        tags.addListener((ListChangeListener.Change<? extends String> change) -> {
            while (change.next()) {
                if (change.wasPermutated()) {
                    
                    ArrayList<Node> newSublist = new ArrayList<>(change.getTo() - change.getFrom());
                    for (int i = change.getFrom(), end = change.getTo(); i < end; i++) {
                        newSublist.add(null);
                    }
                    for (int i = change.getFrom(), end = change.getTo(); i < end; i++) {
                        newSublist.set(change.getPermutation(i), getChildren().get(i));
                    }
                    getChildren().subList(change.getFrom(), change.getTo()).clear();
                    getChildren().addAll(change.getFrom(), newSublist);
                } else {
                    if (change.wasRemoved()) {
                        getChildren().subList(change.getFrom(), change.getFrom() + change.getRemovedSize()).clear();
                    }
                    if (change.wasAdded()) {
                        getChildren().addAll(change.getFrom(), change.getAddedSubList().stream().map(Tag::new).collect(Collectors.toList()));
                    }
                }
            }
        });
        ConnectMSSQL cnObj=new ConnectMSSQL();
        ArrayList<String> symptoms=new ArrayList();
        symptoms=cnObj.fetchSymptomps();
        TextFields.bindAutoCompletion(inputTextField, symptoms);
        getChildren().add(inputTextField);
        
        
        
    }

    private class Tag extends HBox {

        public Tag(String tag) {
            getStyleClass().setAll("tag");
            Button removeButton = new Button("X");
            removeButton.setOnAction((evt) -> tags.remove(tag));
            Text text = new Text(tag);
            HBox.setMargin(text, new Insets(0, 0, 0, 5));
            getChildren().addAll(text, removeButton);
        }
    }

}