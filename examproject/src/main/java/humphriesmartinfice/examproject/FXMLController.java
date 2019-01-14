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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private ListView savesList;

    @FXML
    private void btnLoad(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLStart.fxml")); //where FXMLPage2 is the name of the scene

        Scene home_page_scene = new Scene(home_page_parent);
        //get reference to the stage 
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.hide(); //optional
        stage.setScene(home_page_scene); //puts the new scence in the stage

        stage.setTitle("Spawn Room"); //changes the title
        stage.show(); //shows the new page
        home_page_scene.getRoot().requestFocus();
    }

    @FXML
    private void btnOptions(ActionEvent event) throws IOException {

    }

    @FXML
    private void btnExit(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Only used for testing purposes//
        setINT(1);
        setSTR(1);
        setDEX(1);
        setLevel(1);
        setHealthMAX();
        setHealth(getHealthMAX());
        setManaMAX();
        setMana(getManaMAX());
        setEXP(0);
        setEXPNeeded();
        //Only used for testing purposes// 
    }
}
