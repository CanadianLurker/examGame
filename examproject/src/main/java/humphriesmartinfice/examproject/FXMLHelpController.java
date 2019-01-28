/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humphriesmartinfice.examproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author n00bi
 */
public class FXMLHelpController implements Initializable {

    @FXML
    private Button back, interact, howto, controls;
    @FXML
    private Label lblMessage;

    @FXML
    private void btnHowto(ActionEvent event) throws IOException {
        lblMessage.setText("This game is a prison escape game. \nThe objective of the game is to explore and fight your way out of the prison. \nThe more enemies you face the more xp you'll gain and level up.\nOnce you've reached level 30 and obtain both guard keys you'll be able to face the final boss. \nThere are 2 guard keys and are found after doing challenges.");
    }

    @FXML
    private void btnControls(ActionEvent event) throws IOException {
        lblMessage.setText(" W - move up \n A - move left \n S - move down \n D - move right \n E - interact with objects and people \n I - Open and close inventory");
    }

    @FXML
    private void btnInteract(ActionEvent event) throws IOException {
        lblMessage.setText("To interact with things like doors or enemies press 'E' while at whatever your trying to interact with. \nYou can press 'E' while at your bed to save the game. \nWhile at a door you can press 'E' to enter said door. \nWhile standing with an enemy press 'E' to initiate combat.");
    }

    @FXML
    private void btnBack(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Prison Escape");
        stage.show();
        parent.requestFocus();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
