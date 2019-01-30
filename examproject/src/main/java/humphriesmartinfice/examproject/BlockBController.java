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
import static humphriesmartinfice.examproject.MainApp.stoptime;
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
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author n00bi
 */
public class BlockBController implements Initializable {

    @FXML
    private Pane panMessage, pnlInv;
    @FXML
    private Label lblDoor1, lblDoor2, lblDoor3, lblResult, lblCigs, lblExit, lblCount, lblEquip, lblCigs1;
    @FXML
    private Button fight, yes, no;
    @FXML
    private ImageView imgDoor1, imgDoor2, imgDoor3, imgPlayer, imgExit, imgPaper;

    Image back = new Image(getClass().getResource("/Prisoner2B.png").toString());
    Image front = new Image(getClass().getResource("/prisoner2.png").toString());
    Image closed = new Image(getClass().getResource("/door closed.png").toString());
    Image open = new Image(getClass().getResource("/door_open.png").toString());
    Image FirstBlock = new Image(getClass().getResource("/SecPaper.png").toString());
    Image SecondBlock = new Image(getClass().getResource("/ThirdPaper.png").toString());
    MediaPlayer page = new MediaPlayer((new Media(getClass().getResource("/TurnThePage.mp3").toString())));
    MediaPlayer opensound = new MediaPlayer((new Media(getClass().getResource("/opening.mp3").toString())));
    MediaPlayer ambient = new MediaPlayer((new Media(getClass().getResource("/FFXIV OST The Burn ( A Land Long Dead ).mp3").toString())));

    private double xvar = 0;
    private double yvar = 0;

    int rand;
//changed
    /* 
    int cigs = getCigs();
     */
    int cigs = 0;
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
        if (e.getCode() == KeyCode.E && imgPaper.isVisible()) {
            imgPaper.setVisible(false);
            page.stop();
        }
        if (e.getCode() == KeyCode.E && col(imgPlayer, imgExit)) {
            saveLoc("BlockB");
            MainApp.stoptime = ambient.getCurrentTime();
            ambient.stop();
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
        if ((e.getCode() == KeyCode.I)) {
            if (!MainApp.invVis) {
                MainApp.invVis = true;
                pnlInv.setVisible(true);
                MainApp.displayIcons();
                lblCigs1.setText("Cigs: " + MainApp.cigs);
            } else {
                MainApp.invVis = false;
                pnlInv.setVisible(false);
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
    private void click(MouseEvent e) {

        MainApp.selected = (ImageView) e.getSource();

        for (int i = 0; i < 9; i++) {

            if (MainApp.iSpaces[i] == MainApp.selected) {
                MainApp.rec[i].toFront();
                MainApp.iSpaces[i].toFront();
                MainApp.rec[i].setFill(Color.BLACK);

                lblStats.setText("Level: " + MainApp.inventory[i].getLevel() + "\n" + "Rarity: " + MainApp.inventory[i].getRarity() + "\n" + "Damage: " + MainApp.inventory[i].getDamage());
                System.out.println(MainApp.inventory[i].getClass().getSimpleName() + " , Level=" + MainApp.inventory[i].getLevel() + ", Damage" + MainApp.inventory[i].getDamage());   //////////damage!!!!!!!!!!

                ////////////// make sure to change damage
                if (MainApp.inventory[i].getType().equals("Warrior")) {
                    System.out.println(MainApp.inventory[i].getClass().getSimpleName() + " , Level=" + MainApp.inventory[i].getLevel() + ", Damage" + MainApp.inventory[i].getDamage() / 2); //////////damage!!!!!!!!!!
                } else if (MainApp.inventory[i].getType().equals("Rogue")) {
                    System.out.println(MainApp.inventory[i].getClass().getSimpleName() + " , Level=" + MainApp.inventory[i].getLevel() + ", Damage" + (MainApp.inventory[i].getDamage() - 2));      //////////damage!!!!!!!!!!
                } else {
                    System.out.println(MainApp.inventory[i].getClass().getSimpleName() + " , Level=" + MainApp.inventory[i].getLevel() + ", Damage" + MainApp.inventory[i].getDamage());   //////////damage!!!!!!!!!!
                }

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
    private void btnDelete() {
        for (int i = 0; i < 9; i++) {

            if (MainApp.iSpaces[i] == MainApp.selected) {
                // MainApp.inventory[i] = new Item();
                MainApp.iSpaces[i].toFront();

                MainApp.iSpaces[i].setEffect(null);
                MainApp.displayIcons();

            }
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
                    System.out.println(MainApp.weapon.getSDamage());
                    lblEquip.setText("unequip");
                }
            }
        }
    }

    @FXML
    private void btnFight(ActionEvent event) throws IOException {
        bcount = bcount - 1;
        lblCount.setText("Fight " + bcount + " more enemies to obtain a guard key");
        panMessage.setVisible(false);
        rand = ThreadLocalRandom.current().nextInt(1, 4);
        enemies.add(new Enemy(getLevel()));

        saveLoc("/fxml/BlockB.fxml");
        MainApp.stoptime = ambient.getCurrentTime();
        ambient.stop();
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
        imgPlayer.getParent().requestFocus();

    }

    @FXML
    private void btnNo(ActionEvent e) throws IOException {
        panMessage.setVisible(false);
        saveLoc("BlockB");
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
        ambient.setCycleCount(Timeline.INDEFINITE);
        ambient.setVolume(0.3);
        ambient.setStartTime(stoptime);
        ambient.play();
        if (MainApp.keyB & MainApp.pcount == 1) {
            MainApp.pcount--;
            imgPaper.setVisible(true);
            page.play();
            if (MainApp.ABoss) {
                imgPaper.setImage(SecondBlock);
            } else {
                imgPaper.setImage(FirstBlock);
            }
        }
        lblCigs.setText("Cigs: " + getCigs());
        xmove.setCycleCount(Timeline.INDEFINITE);
        ymove.setCycleCount(Timeline.INDEFINITE);
        xmove.play();
        ymove.play();
        rand = ThreadLocalRandom.current().nextInt(1, 4);
        lblCount.setText("Fight " + bcount + " more enemies to obtain a guard key");
        if (bcount == 0) {
            lblCount.setText("You recieved a guard key!");
            imgPaper.setVisible(true);
            keyB = true;
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
