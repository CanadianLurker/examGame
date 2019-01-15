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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author shayneh58
 */
public class FXMLGuardRoomController implements Initializable {

    @FXML
    private Pane panPlayer;
    @FXML
    private Polygon polPlayer;

    private double xvar = 0;
    private double yvar = 0;

    Timeline xmove = new Timeline(new KeyFrame(Duration.millis(15), ae -> x()));
    Timeline ymove = new Timeline(new KeyFrame(Duration.millis(15), ae -> y()));

    @FXML
    private void move(KeyEvent e) {
        if (e.getCode() == KeyCode.D) {
            xvar = 4;
        }
        if (e.getCode() == KeyCode.A) {
            xvar = -4;
        }
        if (e.getCode() == KeyCode.S) {
            yvar = 4;
        }
        if (e.getCode() == KeyCode.W) {
            yvar = -4;
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
        panPlayer.setLayoutY(panPlayer.getLayoutY() + yvar);
    }

    private void x() {
        panPlayer.setLayoutX(panPlayer.getLayoutY() + xvar);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
