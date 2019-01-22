
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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLController implements Initializable {

    @FXML
    private Label lblSaves, lblEnter, lblTitle;
    @FXML
    private Button btnLoad, btnOpt, btnExit, btnNew;
    @FXML
    private ListView listSaves;
    @FXML
    private ImageView imgL;
private TextField txtSearch;
    private double op = 0.8;
    private double vol = 1;

    private Image light = new Image(getClass().getResource("/lightning.gif").toString());

    Timeline lightning = new Timeline(new KeyFrame(Duration.millis(50), ae -> enter()));
    Timeline quiettime = new Timeline(new KeyFrame(Duration.millis(150), ae -> shush()));
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), ae -> check()));

    MediaPlayer kaboom = new MediaPlayer((new Media(getClass().getResource("/thunder.mp3").toString())));
    MediaPlayer rain = new MediaPlayer((new Media(getClass().getResource("/rainsound.mp3").toString())));

    @FXML
    private void btnLoad(ActionEvent event) throws IOException {
        int record = listSaves.getSelectionModel().getSelectedIndex();
        MainApp.user.open(MainApp.fileName, record);

        System.out.println(MainApp.username);
        System.out.println(MainApp.DEX);
        System.out.println(MainApp.STR);
        System.out.println(MainApp.INT);
        System.out.println(MainApp.cigs);
        if (btnLoad.getOpacity() > 0.8) {
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
            quiettime.play();
        }
    }
boolean dif=false;
    @FXML
    private void btnNewGame(ActionEvent event) throws IOException {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("New Game");
        if (!dif){
        dialog.setHeaderText(null);
        }else{
                    dialog.setHeaderText("Please choose a different username");
                    dif=false;

        }
        dialog.setContentText("Enter a username");
        Optional<String> result = dialog.showAndWait();
        try {
            for(String i: MainApp.usernameList){
            if (dialog.getResult().trim().equals("")||dialog.getResult().trim().equals(i))    {
                dif=true;
                btnNewGame(event);
                return;
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
                stage.hide(); 
                stage.setScene(home_page_scene); //puts the new scence in the stage
                stage.setTitle("Spawn Room"); //changes the title
                stage.setResizable(false);
                stage.show(); //shows the new page
                home_page_scene.getRoot().requestFocus();
            }
            }
        } catch (Exception e) {

        }
    }
    /* private void find(){
    
    ArrayList<String> usernameList2 = MainApp.usernameList;
    
    Collections.sort(usernameList2);
    if(binarySearch(usernameList2,0, usernameList2.size()-1, txtSearch.getText().toString())){
    //for(Object i: listSaves.getItems()){
    for(int i = 0; i < listSaves.getItems().size()-1; i++){
    if(listSaves.getItems().){
    listSaves.setFocusModel(listSaves.getFocusModel());
    }
    }
    
    }
    
    
    }*/
    

    @FXML
    private void btnOptions(ActionEvent event) throws IOException {
        if (btnOpt.getOpacity() > 0.8) {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLHelp.fxml")); 
        Scene home_page_scene = new Scene(home_page_parent);
        //get reference to the stage 
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(home_page_scene); 
        stage.setTitle("Help Screen");
        stage.setResizable(false);
        stage.show();
        home_page_scene.getRoot().requestFocus();
        }
    }

    @FXML
    private void btnExit(ActionEvent event) {
        if (btnExit.getOpacity() > 0.8) {
            System.exit(0);
        }
    }

    @FXML
    private void pressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            imgL.setImage(light);
            kaboom.play();
            lightning.setCycleCount(Timeline.INDEFINITE);
            lightning.play();
            rain.setVolume(0.8);
        }
    }

    private void enter() {
        if (lblEnter.getOpacity() > 0) {
            op = op - 0.05;
            lblEnter.setOpacity(op);
        }
        if (lblEnter.getOpacity() <= 0) {
            imgL.setImage(null);
            op = op + 0.008;
            lblSaves.setOpacity(op);
            listSaves.setOpacity(op);
            btnLoad.setOpacity(op);
            btnOpt.setOpacity(op);
            btnExit.setOpacity(op);
            btnNew.setOpacity(op);
            lblTitle.setOpacity(op + 0.013);
        }
        if (btnExit.getOpacity() >= 1) {
            lightning.stop();
        }
    }

    private void shush() {
        vol = vol - 0.02;
        rain.setVolume(vol);
        if (rain.getVolume() <= 0) {
            quiettime.stop();
            rain.stop();
        }
    }

    private void check() {

        if (listSaves.getSelectionModel().getSelectedIndex() != -1) {
            btnLoad.setDisable(false);
        }
    }
    /*public Boolean binarySearch(ArrayList<String> A, int left, int right, String V){
    int middle;
    if (left > right) {
    return false;
    }
    
    middle = (left + right)/2;
    int compare = V.compareTo(A.get(middle));
    if (compare == 0) {
    return true;
    }
    if (compare < 0) {
    return binarySearch(A, left, middle-1, V);
    } else {
    return binarySearch(A, middle + 1, right, V);
    }
    }*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dif=false;
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        MainApp.user = new File();
        for (int i = 0; i < 9; i++) {
            MainApp.inventory[i] = new Item();

        }

        for (int j = 0; j < user.numRecord(fileName); j++) {

            MainApp.usernameList.add(user.openUser(fileName, j));

        }

        listSaves.getItems().addAll(MainApp.usernameList);
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
        rain.setCycleCount(Timeline.INDEFINITE);
        rain.play();
        quiettime.setCycleCount(Timeline.INDEFINITE);
    }
}
