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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author n00bi
 */
public class FXMLStartController implements Initializable {

    @FXML
    private ImageView imgKey, imgTable, imgBed, imgToilet, imgDoor, imgPoster, imgPlayer, imgSTable;

    private int xvar, yvar;

    Image closed = new Image(getClass().getResource("/door closed.png").toString());
    Image open = new Image(getClass().getResource("/door_open.png").toString());
    Image back = new Image(getClass().getResource("/Prisoner2B.png").toString());
    Image front = new Image(getClass().getResource("/prisoner2.png").toString());

    Timeline Vertical = new Timeline(new KeyFrame(Duration.millis(5), ae -> x()));
    Timeline Horizontal = new Timeline(new KeyFrame(Duration.millis(5), ae -> y()));

    private boolean key;

    private void x() {
        imgPlayer.setLayoutX(imgPlayer.getLayoutX() + xvar);
        if (col(imgTable, imgPlayer) || col(imgPlayer, imgBed) || col(imgPlayer, imgToilet) || imgPlayer.getLayoutX() >= 830 || imgPlayer.getLayoutX() <= 0) {
            imgPlayer.setLayoutX(imgPlayer.getLayoutX() - xvar);
        }
        if (col(imgPlayer, imgDoor) && key) {
            imgDoor.setImage(open);
        }
        if (col(imgPlayer, imgDoor) == false) {
            imgDoor.setImage(closed);
        }
    }

    private void y() {
        imgPlayer.setLayoutY(imgPlayer.getLayoutY() + yvar);
        if (col(imgTable, imgPlayer) || col(imgPlayer, imgBed) || col(imgPlayer, imgToilet) || imgPlayer.getLayoutY() >= 470 || imgPlayer.getLayoutY() <= 30) {
            imgPlayer.setLayoutY(imgPlayer.getLayoutY() - yvar);
        }
    }

    @FXML
    public void keyPressed(KeyEvent event) throws IOException {
        if ((event.getCode() == KeyCode.D)) {
            xvar = 1;
        }
        if ((event.getCode() == KeyCode.A)) {
            xvar = -1;
        }
        if ((event.getCode() == KeyCode.W)) {
            yvar = -1;
        }
        if ((event.getCode() == KeyCode.S)) {
            yvar = 1;
        }
        if (event.getCode() == KeyCode.E && col(imgPlayer, imgDoor) && key) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLCommonRoom.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("Common Room");
            stage.setResizable(false);
            stage.show();
            home_page_scene.getRoot().requestFocus();
        }
        if (event.getCode() == KeyCode.E && col(imgPlayer, imgSTable)) {
            imgKey.setVisible(false);
            key = true;
        }
    }

    @FXML
    public void keyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.D) {
            xvar = 0;
        }
        if (event.getCode() == KeyCode.A) {
            xvar = 0;
        }
        if (event.getCode() == KeyCode.W) {
            yvar = 0;
            imgPlayer.setImage(back);
        }
        if (event.getCode() == KeyCode.S) {
            yvar = 0;
            imgPlayer.setImage(front);
        }
    }

    public boolean col(ImageView block1, ImageView block2) {
        return (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Horizontal.setCycleCount(Timeline.INDEFINITE);
        Vertical.setCycleCount(Timeline.INDEFINITE);
        Horizontal.play();
        Vertical.play();
        if (key) {
            imgKey.setVisible(false);
        }
    }

}
