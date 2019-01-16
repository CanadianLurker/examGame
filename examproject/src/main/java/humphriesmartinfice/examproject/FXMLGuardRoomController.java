package humphriesmartinfice.examproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author shayneh58
 */
public class FXMLGuardRoomController implements Initializable {

    @FXML
    private ImageView imgPlayer;

    private double xvar = 0;
    private double yvar = 0;

    Timeline xmove = new Timeline(new KeyFrame(Duration.millis(5), ae -> x()));
    Timeline ymove = new Timeline(new KeyFrame(Duration.millis(5), ae -> y()));

    @FXML
    private void move(KeyEvent e) {
        if (e.getCode() == KeyCode.D) {
            xvar = 1;
        }
        if (e.getCode() == KeyCode.A) {
            xvar = -1;
        }
        if (e.getCode() == KeyCode.S) {
            yvar = 1;
        }
        if (e.getCode() == KeyCode.W) {
            yvar = -1;
        }
    }

    @FXML
    private void rmove(KeyEvent e) {
        if (e.getCode() == KeyCode.D) {
            xvar = 0;
        }
        if (e.getCode() == KeyCode.A) {
            xvar = 0;
        }
        if (e.getCode() == KeyCode.S) {
            yvar = 0;
        }
        if (e.getCode() == KeyCode.W) {
            yvar = 0;
        }
    }

    private void y() {
        imgPlayer.setLayoutY(imgPlayer.getLayoutY() + yvar);
    }

    private void x() {
        imgPlayer.setLayoutX(imgPlayer.getLayoutX() + xvar);
        if (imgPlayer.getLayoutX() >= 830 || imgPlayer.getLayoutX() <= 0 || imgPlayer.getLayoutY() >= 470 || imgPlayer.getLayoutY() <= 30) {
            imgPlayer.setLayoutX(imgPlayer.getLayoutX() - xvar);
            imgPlayer.setLayoutY(imgPlayer.getLayoutY() - yvar);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        xmove.setCycleCount(Timeline.INDEFINITE);
        ymove.setCycleCount(Timeline.INDEFINITE);
        xmove.play();
        ymove.play();
    }

}
