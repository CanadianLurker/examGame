package humphriesmartinfice.examproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import static humphriesmartinfice.examproject.MainApp.*;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author shayneh58
 */
public class FXMLGuardRoomController implements Initializable {

    @FXML
    private ImageView imgPlayer, imgEnemy, imgCommonRoom, imgO, imgPaper;
    @FXML
    private Label lblEquip, lblCigs;

    private double xvar = 0;
    private double yvar = 0;

    Timeline xmove = new Timeline(new KeyFrame(Duration.millis(5), ae -> x()));
    Timeline ymove = new Timeline(new KeyFrame(Duration.millis(5), ae -> y()));
    Timeline beepadjust = new Timeline(new KeyFrame(Duration.millis(500), ae -> beeper()));

    Image closed = new Image(getClass().getResource("/door closed.png").toString());
    Image open = new Image(getClass().getResource("/door_open.png").toString());
    Image back = new Image(getClass().getResource("/Prisoner2B.png").toString());
    Image front = new Image(getClass().getResource("/prisoner2.png").toString());

    MediaPlayer opensound = new MediaPlayer((new Media(getClass().getResource("/opening.mp3").toString())));
    MediaPlayer beep = new MediaPlayer((new Media(getClass().getResource("/beep.mp3").toString())));
    MediaPlayer page = new MediaPlayer((new Media(getClass().getResource("/TurnThePage.mp3").toString())));
    MediaPlayer ambient = new MediaPlayer((new Media(getClass().getResource("/FFXIV OST The Burn ( A Land Long Dead ).mp3").toString())));

    @FXML
    private void move(KeyEvent e) throws IOException {
        if (e.getCode() == KeyCode.D) {
            xvar = 1;
        }
        if (e.getCode() == KeyCode.A) {
            xvar = -1;
        }
        if (e.getCode() == KeyCode.S) {
            yvar = 1;
            imgPlayer.setImage(front);
        }
        if (e.getCode() == KeyCode.W) {
            yvar = -1;
            imgPlayer.setImage(back);
        }
        if (e.getCode() == KeyCode.E && col(imgPlayer, imgCommonRoom)) {
            MainApp.stoptime = ambient.getCurrentTime();
            ambient.stop();
            saveLoc("GuardRoom");
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLCommonRoom.fxml")); // now hosting the testing grounds for combat
            Scene scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.close();
            stage.setScene(scene);
            stage.setTitle("Common Room");
            stage.setResizable(false);
            stage.show();
            home_page_parent.requestFocus();
            beep.stop();
            beepadjust.stop();
        }
        if (e.getCode() == KeyCode.E && col(imgPlayer, imgO)) {
            beep.stop();
            beepadjust.stop();
            ambient.setVolume(0.3);
            MainApp.WItem = true;
        }
        if (e.getCode() == KeyCode.E && imgPaper.isVisible()) {
            imgPaper.setVisible(false);
            page.stop();
        } else if (e.getCode() == KeyCode.E && col(imgPlayer, imgEnemy) && MainApp.WItem) {
            imgPaper.setVisible(true);
            page.play();
        }
        if ((e.getCode() == KeyCode.I)) {
            if (!MainApp.invVis) {
                MainApp.invVis = true;
                pnlInv.setVisible(true);
                MainApp.displayIcons();
                lblCigs.setText("Cigs: " + MainApp.cigs);
            } else {
                MainApp.invVis = false;
                pnlInv.setVisible(false);
            }
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
        if (imgPlayer.getLayoutY() >= 470 || imgPlayer.getLayoutY() <= 30) {
            imgPlayer.setLayoutY(imgPlayer.getLayoutY() - yvar);
        }
    }

    private void x() {
        imgPlayer.setLayoutX(imgPlayer.getLayoutX() + xvar);
        if (imgPlayer.getLayoutX() >= 830 || imgPlayer.getLayoutX() <= 0) {
            imgPlayer.setLayoutX(imgPlayer.getLayoutX() - xvar);
        }
        if (col(imgPlayer, imgCommonRoom)) {
            imgCommonRoom.setImage(open);
            opensound.play();
        }
        if (!col(imgPlayer, imgCommonRoom)) {
            imgCommonRoom.setImage(closed);
            opensound.stop();
        }
    }

    public boolean col(ImageView block1, ImageView block2) {
        return (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));
    }

    private void beeper() {
        double x = imgPlayer.getLayoutX();
        double y = imgPlayer.getLayoutY();
        double xdif = Math.abs(xplace - x);
        double ydif = Math.abs(yplace - y);
        double rate = 1 / ((xdif + ydif) / 400);
        if (rate > 3) {
            rate = 3;
        }
        if (rate < 0.5) {
            rate = 0.5;
        }
        beep.setRate(rate);
        System.out.println(rate);
    }

    @FXML
    Label lblStats;
    @FXML

    ImageView img1,
            img2,
            img3,
            img4,
            img5,
            img6,
            img7,
            img8,
            img9;

    @FXML
    Rectangle rec1,
            rec2,
            rec3,
            rec4,
            rec5,
            rec6,
            rec7,
            rec8,
            rec9;

    @FXML
    Pane pnlInv;

    @FXML
    private void click(MouseEvent e) {

        MainApp.selected = (ImageView) e.getSource();

        for (int i = 0; i < 9; i++) {

            if (MainApp.iSpaces[i] == MainApp.selected && !MainApp.inventory[i].getType().equals("Item")) {
                MainApp.rec[i].toFront();
                MainApp.iSpaces[i].toFront();
                MainApp.rec[i].setFill(Color.BLACK);
                if (MainApp.weapon == MainApp.inventory[i]) {
                    lblEquip.setText("unequip");
                } else {
                    lblEquip.setText("equip");

                }

                ////////////// make sure to change damage
                lblStats.setText("Level: " + MainApp.inventory[i].getLevel() + "\n" + "Rarity: " + MainApp.inventory[i].getRarity() + "\n" + "Damage: " + MainApp.inventory[i].getDamage());
                System.out.println(MainApp.inventory[i].getClass().getSimpleName() + " , Level=" + MainApp.inventory[i].getLevel() + ", Damage" + MainApp.inventory[i].getDamage());   //////////damage!!!!!!!!!!

            } else {
                MainApp.rec[i].setFill(Color.GREY);

            }
        }

    }

    @FXML
    private void paneClick(MouseEvent e) {
        for (int i = 0; i < 9; i++) {
            MainApp.rec[i].setFill(Color.GREY);
            MainApp.selected = null;
        }

    }

    @FXML
    private void equip() {
        for (int i = 0; i < 9; i++) {
            if (MainApp.iSpaces[i] == MainApp.selected) {
                if (MainApp.weapon == MainApp.inventory[i]) {
                    lblEquip.setText("equip");
                    MainApp.weapon = null;

                } else {
                    MainApp.weapon = MainApp.inventory[i];
                    lblEquip.setText("unequip");
                }
            }
        }
    }

    @FXML
    private void btnDelete() {
        for (int i = 0; i < 9; i++) {

            if (MainApp.iSpaces[i] == MainApp.selected) {
                MainApp.inventory[i] = new Weapon();
                MainApp.iSpaces[i].toFront();

                MainApp.iSpaces[i].setEffect(null);
                MainApp.displayIcons();

            }
        }
    }

    ////not needed{
    @FXML
    private void save() {
        System.out.println(MainApp.saveInventory());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ambient.setCycleCount(Timeline.INDEFINITE);
        ambient.setVolume(0.3);
        ambient.setStartTime(stoptime);
        ambient.play();
        MainApp.cigs = 2000;
        xmove.setCycleCount(Timeline.INDEFINITE);
        ymove.setCycleCount(Timeline.INDEFINITE);
        xmove.play();
        ymove.play();
        System.out.print(xplace + " " + yplace);
        imgO.setLayoutX(xplace);
        imgO.setLayoutY(yplace);
        beep.setCycleCount(Timeline.INDEFINITE);
        beepadjust.setCycleCount(Timeline.INDEFINITE);
        if (MainApp.ABoss && !MainApp.WItem) {
            ambient.setVolume(0.1);
            beep.play();
            beepadjust.play();
        }
        MainApp.rec[0] = rec1;
        MainApp.rec[1] = rec2;
        MainApp.rec[2] = rec3;
        MainApp.rec[3] = rec4;
        MainApp.rec[4] = rec5;
        MainApp.rec[5] = rec6;
        MainApp.rec[6] = rec7;
        MainApp.rec[7] = rec8;
        MainApp.rec[8] = rec9;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                MainApp.inv[i][j] = 0;
                MainApp.IS[i] = new InnerShadow();
                MainApp.rec[i].setFill(Color.GREY);
            }

        }

        MainApp.iSpaces[0] = img1;
        MainApp.iSpaces[1] = img2;
        MainApp.iSpaces[2] = img3;
        MainApp.iSpaces[3] = img4;
        MainApp.iSpaces[4] = img5;
        MainApp.iSpaces[5] = img6;
        MainApp.iSpaces[6] = img7;
        MainApp.iSpaces[7] = img8;
        MainApp.iSpaces[8] = img9;

        MainApp.img1 = img1;
        MainApp.img2 = img2;
        MainApp.img3 = img3;
        MainApp.img4 = img4;
        MainApp.img5 = img5;
        MainApp.img6 = img6;
        MainApp.img7 = img7;
        MainApp.img8 = img8;
        MainApp.img9 = img9;
    }

}
