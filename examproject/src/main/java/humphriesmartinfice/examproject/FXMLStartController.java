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
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author n00bi
 */
public class FXMLStartController implements Initializable {

    @FXML
    private ImageView player;
    @FXML
    Timeline Right = new Timeline(new KeyFrame(Duration.millis(50), ae -> right()));
    @FXML
    Timeline Left = new Timeline(new KeyFrame(Duration.millis(50), ae -> left()));
    @FXML
    Timeline Up = new Timeline(new KeyFrame(Duration.millis(50), ae -> up()));
    @FXML
    Timeline Down = new Timeline(new KeyFrame(Duration.millis(50), ae -> down()));

    private void right() {
        player.setTranslateX(player.getTranslateX() + 5);
    }
    private void left() {
        player.setTranslateX(player.getTranslateX() - 5);
    }
    private void up() {
        player.setTranslateY(player.getTranslateY() - 5);
    }
    private void down() {
        player.setTranslateY(player.getTranslateY() + 5);
    }

    public void keyPressed(KeyEvent event) {
        if ((event.getCode() == KeyCode.D)) {
            Right.setCycleCount(Timeline.INDEFINITE);
            Right.play();
        }
        if ((event.getCode() == KeyCode.A)) {
            Left.setCycleCount(Timeline.INDEFINITE);
            Left.play();
        }
        if ((event.getCode() == KeyCode.W)) {
            Up.setCycleCount(Timeline.INDEFINITE);
            Up.play();
        }
        if ((event.getCode() == KeyCode.S)) {
            Down.setCycleCount(Timeline.INDEFINITE);
            Down.play();
        }
    }
    public void keyReleased(KeyEvent event) {
        if ((event.getCode() == KeyCode.D)) {
            Right.stop();
        }
        if ((event.getCode() == KeyCode.A)) {
            Left.stop();
        }
        if ((event.getCode() == KeyCode.W)) {
            Up.stop();
        }
        if ((event.getCode() == KeyCode.S)) {
            Down.stop();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
