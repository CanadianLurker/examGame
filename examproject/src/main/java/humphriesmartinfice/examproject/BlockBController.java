/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humphriesmartinfice.examproject;

import static humphriesmartinfice.examproject.MainApp.getCigs;
import static humphriesmartinfice.examproject.MainApp.getLevel;
import static humphriesmartinfice.examproject.MainApp.setCigs;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.util.concurrent.ThreadLocalRandom;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author n00bi
 */
public class BlockBController implements Initializable {

    @FXML
    private Pane panMessage;
    @FXML
    private Label lblDoor1, lblDoor2, lblDoor3, lblResult, lblCigs, lblExit;
    @FXML
    private Button fight, yes, no;
    @FXML
    private ImageView imgDoor1, imgDoor2, imgDoor3, imgPlayer, imgExit;
    
    Image back = new Image(getClass().getResource("/Prisoner2B.png").toString());
    Image front = new Image(getClass().getResource("/prisoner2.png").toString());

    private double xvar = 0;
    private double yvar = 0;

    int rand;

    int cigs = getCigs();

    Timeline xmove = new Timeline(new KeyFrame(Duration.millis(15), ae -> x()));
    Timeline ymove = new Timeline(new KeyFrame(Duration.millis(15), ae -> y()));

    @FXML
    private void keyPressed(KeyEvent e) throws IOException {
        if (e.getCode() == KeyCode.D) {
            xvar = 4;
        }
        if (e.getCode() == KeyCode.A) {
            xvar = -4;
        }
        if (e.getCode() == KeyCode.S) {
            yvar = 4;
            imgPlayer.setImage(front);
        }
        if (e.getCode() == KeyCode.W) {
            yvar = -4;
            imgPlayer.setImage(back);
        }
        if (e.getCode() == KeyCode.E && col(imgPlayer, imgExit)) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLCommonRoom.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("Common Room");
            stage.show();
            home_page_scene.getRoot().requestFocus();
        }
        if (e.getCode() == KeyCode.E && col(imgPlayer, imgDoor1)) {
            check();
        }
        if (e.getCode() == KeyCode.E && col(imgPlayer, imgDoor2)) {
            check();
        }
        if (e.getCode() == KeyCode.E && col(imgPlayer, imgDoor3)) {
            check();
        }
    }

    @FXML
    private void keyReleased(KeyEvent e) {
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
            imgPlayer.setImage(front);
        }
    }

    private void x() {
        imgPlayer.setLayoutX(imgPlayer.getLayoutX() + xvar);
        if (col(imgPlayer, imgDoor1) || col(imgPlayer, imgDoor2) || col(imgPlayer, imgDoor3) || col(imgPlayer, imgExit)) {
            imgPlayer.setLayoutX(imgPlayer.getLayoutX() - xvar);
        }
    }

    private void y() {
        imgPlayer.setLayoutY(imgPlayer.getLayoutY() + yvar);
        if (imgPlayer.getLayoutX() >= 800 || imgPlayer.getLayoutX() <= 0 || imgPlayer.getLayoutY() >= 470 || imgPlayer.getLayoutY() <= 30) {
            imgPlayer.setLayoutX(imgPlayer.getLayoutX() - xvar);
            imgPlayer.setLayoutY(imgPlayer.getLayoutY() - yvar);
        }
    }

    private void check() {
        if (rand == 1) {
            xmove.stop();
            ymove.stop();
            imgPlayer.setTranslateY(imgPlayer.getTranslateY() + 5);
            lblResult.setText("Congratulations, you chose the correct door and have recieved 25 cigs!");
            cigs = cigs + 25;
            setCigs(getCigs() + cigs);
            lblCigs.setText("Cigs: " + getCigs());
            panMessage.setVisible(true);
            yes.setVisible(true);
            no.setVisible(true);
        } else if (rand == 2 || rand == 3) {
            xmove.stop();
            ymove.stop();
            imgPlayer.setTranslateY(imgPlayer.getTranslateY() + 5);
            panMessage.setVisible(true);
            yes.setVisible(false);
            no.setVisible(false);
            fight.setVisible(true);
            lblResult.setText("Sorry, you chose the wrong door.");
        }
    }

    public boolean col(ImageView block1, ImageView block2) {
        return (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));
    }

    @FXML
    private void btnFight(ActionEvent event) throws IOException {
        panMessage.setVisible(false);
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLCombat.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(home_page_scene);
        stage.setTitle("FIGHT!!!");
        stage.show();
        home_page_scene.getRoot().requestFocus();
    }

    @FXML
    private void btnYes(ActionEvent event) throws IOException {
        xmove.setCycleCount(Timeline.INDEFINITE);
        ymove.setCycleCount(Timeline.INDEFINITE);
        xmove.play();
        ymove.play();
        rand = ThreadLocalRandom.current().nextInt(1, 4);
        panMessage.setVisible(false);
    }

    @FXML
    private void btnNo(ActionEvent event) throws IOException {
        panMessage.setVisible(false);
        xmove.setCycleCount(Timeline.INDEFINITE);
        ymove.setCycleCount(Timeline.INDEFINITE);
        xmove.play();
        ymove.play();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblCigs.setText("Cigs: " + getCigs());
        xmove.setCycleCount(Timeline.INDEFINITE);
        ymove.setCycleCount(Timeline.INDEFINITE);
        xmove.play();
        ymove.play();
        rand = ThreadLocalRandom.current().nextInt(1, 4);
    }
}
