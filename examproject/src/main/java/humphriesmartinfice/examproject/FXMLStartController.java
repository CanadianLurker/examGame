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

/**
 * FXML Controller class
 *
 * @author n00bi
 */
public class FXMLStartController implements Initializable {

    @FXML
    private ImageView imgKey, imgTable, imgBed, imgToilet, imgDoor, imgPoster, imgPlayer, imgTableS;

    private int xvar, yvar;

    Timeline Vertical = new Timeline(new KeyFrame(Duration.millis(15), ae -> x()));
    Timeline Horizontal = new Timeline(new KeyFrame(Duration.millis(15), ae -> y()));

    private void x() {
        imgPlayer.setLayoutX(imgPlayer.getLayoutX() + xvar);
        if(col(imgTable, imgPlayer)|| col(imgPlayer, imgBed)||col(imgPlayer, imgToilet)|| imgPlayer.getLayoutX() >= 850 ||imgPlayer.getLayoutX() <= 3 || imgPlayer.getLayoutY() >= 540 ||imgPlayer.getLayoutY() <= 20 ){
        imgPlayer.setLayoutX(imgPlayer.getLayoutX() - xvar);
        imgPlayer.setLayoutY(imgPlayer.getLayoutY() - yvar);
        }
    }

    private void y() {
        imgPlayer.setLayoutY(imgPlayer.getLayoutY() + yvar);
    }

    @FXML
    public void keyPressed(KeyEvent event) throws IOException {
        if ((event.getCode() == KeyCode.D)) {
            xvar = 3;
        }
        if ((event.getCode() == KeyCode.A)) {
            xvar = -3;
        }
        if ((event.getCode() == KeyCode.W)) {
            yvar = -3;
        }
        if ((event.getCode() == KeyCode.S)) {
            yvar = 3;
        }
        if(col(imgPlayer, imgDoor)&& imgKey.isVisible() == false){
        imgDoor.setImage(new Image(getClass().getResource("/door closed.png").toString()));
        }
        if(event.getCode() == KeyCode.E && col(imgPlayer, imgDoor) && imgKey.isVisible() == false){
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLCommonRoom.fxml")); 
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("Common Room");
            stage.show();
            home_page_scene.getRoot().requestFocus();
        }
        if(event.getCode() == KeyCode.E && col(imgPlayer, imgTableS)){
            imgKey.setVisible(false);
        }
    }

    @FXML
    public void keyReleased(KeyEvent event) {
        if ((event.getCode() == KeyCode.D || event.getCode() == KeyCode.A)) {
            xvar = 0;
        }
        if ((event.getCode() == KeyCode.W || event.getCode() == KeyCode.S)) {
            yvar = 0;
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
    }

}
