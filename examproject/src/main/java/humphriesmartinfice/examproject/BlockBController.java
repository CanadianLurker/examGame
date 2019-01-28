/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humphriesmartinfice.examproject;

import static humphriesmartinfice.examproject.MainApp.enemies;
import static humphriesmartinfice.examproject.MainApp.getCigs;
import static humphriesmartinfice.examproject.MainApp.getLevel;
import static humphriesmartinfice.examproject.MainApp.setCigs;
import static humphriesmartinfice.examproject.MainApp.saveLoc;
import static humphriesmartinfice.examproject.MainApp.keyB;
import static humphriesmartinfice.examproject.MainApp.bcount;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    private Label lblDoor1, lblDoor2, lblDoor3, lblResult, lblCigs, lblExit, lblCount;
    @FXML
    private Button fight, yes, no;
    @FXML
    private ImageView imgDoor1, imgDoor2, imgDoor3, imgPlayer, imgExit;

    Image back = new Image(getClass().getResource("/Prisoner2B.png").toString());
    Image front = new Image(getClass().getResource("/prisoner2.png").toString());
    Image closed = new Image(getClass().getResource("/door closed.png").toString());
    Image open = new Image(getClass().getResource("/door_open.png").toString());
    MediaPlayer opensound = new MediaPlayer((new Media(getClass().getResource("/opening.mp3").toString())));

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
            if (rand == 1) {
                cigs = cigs + 25;
                setCigs(getCigs() + cigs);
                lblCigs.setText("Cigs: " + getCigs());
            }
        }
        if (e.getCode() == KeyCode.E && col(imgPlayer, imgDoor2)) {
            check();
            if (rand == 1) {
                cigs = cigs + 25;
                setCigs(getCigs() + cigs);
                lblCigs.setText("Cigs: " + getCigs());
            }
        }
        if (e.getCode() == KeyCode.E && col(imgPlayer, imgDoor3)) {
            check();
            if (rand == 1) {
                cigs = cigs + 25;
                setCigs(getCigs() + cigs);
                lblCigs.setText("Cigs: " + getCigs());
            }
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
        if (imgPlayer.getLayoutX() >= 800 || imgPlayer.getLayoutX() <= 0) {
            imgPlayer.setLayoutX(imgPlayer.getLayoutX() - xvar);
        }
        if (col(imgPlayer, imgDoor1)) {
            imgDoor1.setImage(open);
            opensound.play();
        } else if (col(imgPlayer, imgDoor2)) {
            imgDoor2.setImage(open);
            opensound.play();
        } else if (col(imgPlayer, imgDoor3)) {
            imgDoor3.setImage(open);
            opensound.play();
        } else if (col(imgPlayer, imgExit)) {
            imgExit.setImage(open);
            opensound.play();
        } else if (col(imgPlayer, imgExit) == false) {
            imgDoor1.setImage(closed);
            imgDoor2.setImage(closed);
            imgDoor3.setImage(closed);
            imgExit.setImage(closed);
            opensound.stop();
        } else if (col(imgPlayer, imgDoor1) == false) {
            opensound.stop();
        } else if (col(imgPlayer, imgDoor2) == false) {
            opensound.stop();
        } else if (col(imgPlayer, imgDoor3) == false) {
            opensound.stop();
        }
        if (col(imgPlayer, imgDoor1)) {
            imgDoor1.setImage(open);
            opensound.play();
        }
    }

    private void y() {
        imgPlayer.setLayoutY(imgPlayer.getLayoutY() + yvar);
        if (imgPlayer.getLayoutY() >= 470 || imgPlayer.getLayoutY() <= 30) {
            imgPlayer.setLayoutY(imgPlayer.getLayoutY() - yvar);
        }
    }

    private void check() {
        if (rand == 1) {
            imgPlayer.setLayoutX(183);
            imgPlayer.setLayoutY(429);
            lblResult.setText("Congratulations \n You chose the correct door and have recieved 25 cigs! \n Would you like to play again?");
            panMessage.setVisible(true);
            yes.setVisible(true);
            no.setVisible(true);
        } else if (rand == 2 || rand == 3) {
            imgPlayer.setLayoutX(183);
            imgPlayer.setLayoutY(429);
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
        bcount = bcount - 1;
        lblCount.setText("Fight " + bcount + " more enemies to obtain a guard key");
        panMessage.setVisible(false);
        rand = ThreadLocalRandom.current().nextInt(1, 4);
        enemies.add(new Enemy(getLevel()));
        saveLoc("/fxml/BlockB.fxml");
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLCombat.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(home_page_scene);
        stage.setTitle("FIGHT!!!");
        stage.show();
    }

    @FXML
    private void btnYes(ActionEvent event) throws IOException {
        imgPlayer.setLayoutX(183);
        imgPlayer.setLayoutY(429);
        rand = ThreadLocalRandom.current().nextInt(1, 4);
        panMessage.setVisible(false);

    }

    @FXML
    private void btnNo(ActionEvent e) throws IOException {
        panMessage.setVisible(false);
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLCommonRoom.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(home_page_scene);
        stage.setTitle("Common Room");
        stage.show();
        home_page_scene.getRoot().requestFocus();
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
        lblCount.setText("Fight " + bcount + " more enemies to obtain a guard key");
        if (bcount == 0) {
            lblCount.setText("You recieved a guard key!");
            keyB = true;
        }
    }
}
