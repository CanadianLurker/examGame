/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humphriesmartinfice.examproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import static humphriesmartinfice.examproject.MainApp.*;

/**
 * FXML Controller class
 *
 * @author n00bi
 */
public class FXMLCommonRoomController implements Initializable {

    @FXML
    private ImageView imgGuard, imgGuardRoom, imgBlockA, imgBlockB, imgCell, imgPlayer;

    int test = 0;
    private int xvar, yvar;

    Timeline Horizontal = new Timeline(new KeyFrame(Duration.millis(15), ae -> x()));
    Timeline Vertical = new Timeline(new KeyFrame(Duration.millis(15), ae -> y()));

    private void y() {
        imgPlayer.setLayoutY(imgPlayer.getLayoutY() + yvar);
    }

    private void x() {
        imgPlayer.setLayoutX(imgPlayer.getLayoutX() + xvar);
    }

    @FXML
    public void keyPressed(KeyEvent event) throws IOException {
        if ((event.getCode() == KeyCode.D)) {
            xvar = 2;
        }
        if ((event.getCode() == KeyCode.A)) {
            xvar = - 2;
        }
        if ((event.getCode() == KeyCode.W)) {
            yvar = -2;
        }
        if ((event.getCode() == KeyCode.S)) {
            yvar = 2;
        }
        if (event.getCode() == KeyCode.E && col(imgPlayer, imgBlockA)) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/BlockA.fxml")); 
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("Block A");
            stage.show();
            home_page_scene.getRoot().requestFocus();
        }
        if (event.getCode() == KeyCode.E && col(imgPlayer, imgGuardRoom)) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLGuardRoom.fxml")); 
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("Guard Room");
            stage.show();
            home_page_scene.getRoot().requestFocus();
        }
        if (event.getCode() == KeyCode.E && col(imgPlayer, imgBlockB)) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/BlockB.fxml")); 
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("Block B");
            stage.show();
            home_page_scene.getRoot().requestFocus();
        }
        if (event.getCode() == KeyCode.E && col(imgPlayer, imgCell)) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLStart.fxml")); 
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("Your Cell");
            stage.show();
            home_page_scene.getRoot().requestFocus();
        }
    }

    @FXML
    public void keyReleased(KeyEvent event) {
        if ((event.getCode() == KeyCode.D)) {
            xvar = 0;
        }
        if ((event.getCode() == KeyCode.A)) {
            xvar = -0;
        }
        if ((event.getCode() == KeyCode.W)) {
            yvar = -0;
        }
        if ((event.getCode() == KeyCode.S)) {
            yvar = 0;
        }
    }

    public boolean col(ImageView block1, ImageView block2) {
        return (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Horizontal.setCycleCount(Timeline.INDEFINITE);
        Vertical.setCycleCount(Timeline.INDEFINITE);
        Horizontal.play();
        Vertical.play();
    }

}
