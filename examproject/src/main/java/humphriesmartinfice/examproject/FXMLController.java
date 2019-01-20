package humphriesmartinfice.examproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import static humphriesmartinfice.examproject.MainApp.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLController implements Initializable {


    @FXML
    private Label label;
    @FXML
    private ListView savesList;
@FXML
private Button load;
Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), ae -> check()));


    @FXML
    private void btnLoad(ActionEvent event) throws IOException {
        int record=savesList.getSelectionModel().getSelectedIndex();
        MainApp.user.open(MainApp.fileName, record);
        
        System.out.println(MainApp.username);
             System.out.println(MainApp.DEX);
             System.out.println(MainApp.STR);
             System.out.println(MainApp.INT);
             System.out.println(MainApp.cigs);
        
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLStart.fxml")); //where FXMLPage2 is the name of the scene
        Scene home_page_scene = new Scene(home_page_parent);
        //get reference to the stage 
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide(); //optional
        stage.setScene(home_page_scene); //puts the new scence in the stage
        stage.setTitle("Spawn Room"); //changes the title
        stage.setResizable(false);
        stage.show(); //shows the new page
        home_page_scene.getRoot().requestFocus();
    }
    @FXML
    private void btnNewGame(ActionEvent event) throws IOException {
       TextInputDialog dialog = new TextInputDialog(""); 
dialog.setTitle("New Game"); 
dialog.setHeaderText(null);
dialog.setContentText("Enter a username"); 
Optional<String> result = dialog.showAndWait();
        try {
            if (dialog.getResult().trim().equals("")) {
            } else {
                MainApp.username = dialog.getResult();
                MainApp.usernameList.add(username);
                
                System.out.println(MainApp.username);
             System.out.println(MainApp.DEX);
             System.out.println(MainApp.STR);
             System.out.println(MainApp.INT);
             System.out.println(MainApp.cigs);
                
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLStart.fxml")); //where FXMLPage2 is the name of the scene
                Scene home_page_scene = new Scene(home_page_parent);
                //get reference to the stage 
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide(); //optional
                stage.setScene(home_page_scene); //puts the new scence in the stage
                stage.setTitle("Spawn Room"); //changes the title
                stage.setResizable(false);
                stage.show(); //shows the new page
                home_page_scene.getRoot().requestFocus();
            }
        } catch (Exception e) {
            
        }
    }

    @FXML
    private void btnOptions(ActionEvent event) throws IOException {

    }

    @FXML
    private void btnExit(ActionEvent event) {
        System.exit(0);
    }

private void check(){
    
   if(savesList.getSelectionModel().getSelectedIndex()!=-1){
       load.setDisable(false);
   }
    }


    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        MainApp.user=new File();
for (int i = 0; i < 9; i++) {
    MainApp.inventory[i] = new Item();
    
         }

for(int j=0;j<user.numRecord(fileName);j++){

MainApp.usernameList.add( user.openUser(fileName, j));


}



savesList.getItems().addAll(MainApp.usernameList);
        //Only used for testing purposes//
        setINT(1);
        setSTR(1);
        setDEX(1);
        setLevel(30);
        setHealthMAX();
        setHealth(getHealthMAX());
        setManaMAX();
        setMana(getManaMAX());
        setEXP(0);
        setEXPNeeded();
        //Only used for testing purposes// 
    }
}
