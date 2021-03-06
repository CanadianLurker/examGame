/*
 * made by Shayne, Ryan And Darby
 */
package humphriesmartinfice.examproject;

import static humphriesmartinfice.examproject.MainApp.enemies;
import static humphriesmartinfice.examproject.MainApp.getLevel;
import static humphriesmartinfice.examproject.MainApp.saveLoc;
import static humphriesmartinfice.examproject.MainApp.stoptime;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author n00bi
 */
public class FXMLStartController implements Initializable {

    @FXML
    private ImageView imgKey, imgTable, imgBed, imgToilet, imgDoor, imgPoster, imgPlayer, imgSTable, imgSBed, imgPaper;
    @FXML
    private Label lblCigs;

    private int xvar, yvar;

    Image closed = new Image(getClass().getResource("/door closed.png").toString());
    Image open = new Image(getClass().getResource("/door_open.png").toString());
    Image back = new Image(getClass().getResource("/Prisoner2B.png").toString());
    Image front = new Image(getClass().getResource("/prisoner2.png").toString());
    Image paper = new Image(getClass().getResource("/Paper.jpg").toString());
    Image warden = new Image(getClass().getResource("/WardenPaper.png").toString());
    Image Fpaper = new Image(getClass().getResource("/FinalPaper.png").toString());

    Timeline Vertical = new Timeline(new KeyFrame(Duration.millis(5), ae -> x()));
    Timeline Horizontal = new Timeline(new KeyFrame(Duration.millis(5), ae -> y()));

    MediaPlayer opensound = new MediaPlayer((new Media(getClass().getResource("/opening.mp3").toString())));
    MediaPlayer page = new MediaPlayer((new Media(getClass().getResource("/TurnThePage.mp3").toString())));
    MediaPlayer ambient = new MediaPlayer((new Media(getClass().getResource("/FFXIV OST The Burn ( A Land Long Dead ).mp3").toString())));

    private void x() {
        imgPlayer.setLayoutX(imgPlayer.getLayoutX() + xvar);
        if (col(imgTable, imgPlayer) || col(imgPlayer, imgBed) || col(imgPlayer, imgToilet) || imgPlayer.getLayoutX() >= 830 || imgPlayer.getLayoutX() <= 0) {
            imgPlayer.setLayoutX(imgPlayer.getLayoutX() - xvar);
        }
        if (col(imgPlayer, imgDoor) && MainApp.key) {
            imgDoor.setImage(open);
            opensound.play();
        }
        if (col(imgPlayer, imgDoor) == false) {
            imgDoor.setImage(closed);
            opensound.stop();
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
            imgPlayer.setImage(back);
        }
        if ((event.getCode() == KeyCode.S)) {
            yvar = 1;
            imgPlayer.setImage(front);
        }
        if (event.getCode() == KeyCode.E && col(imgPlayer, imgDoor) && MainApp.key) {
            saveLoc("FXMLStart");
            MainApp.stoptime = ambient.getCurrentTime();
            ambient.stop();
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
        if (event.getCode() == KeyCode.E && col(imgPlayer, imgSTable) && !imgPaper.isVisible()) {
            imgPaper.setVisible(true);
            Vertical.stop();
            Horizontal.stop();
            page.play();
        } else if (imgPaper.isVisible() && event.getCode() == KeyCode.E && !MainApp.WItem) {
            Vertical.play();
            Horizontal.play();
            imgPaper.setVisible(false);
            page.stop();
            imgKey.setVisible(false);
            MainApp.key = true;
        } else if (imgPaper.isVisible() && event.getCode() == KeyCode.E && imgPaper.getImage().equals(warden)) {
            Vertical.play();
            Horizontal.play();
            imgPaper.setVisible(true);
            page.stop();
            imgKey.setVisible(false);
            enemies.add(new Enemy(getLevel() + 10));
            ambient.stop();
            MainApp.Warden = true;
            saveLoc("/fxml/FXMLStart.fxml");
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLCombat.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("FIGHT!!!");
            stage.setResizable(false);
            stage.show();
        }
        else if (imgPaper.isVisible() && event.getCode() == KeyCode.E && MainApp.Warden) {
            imgPaper.setVisible(false);
        }
        if (event.getCode() == KeyCode.E && col(imgPlayer, imgSBed)) {
            MainApp.setHealth(MainApp.getHealthMAX());
            MainApp.user.save(MainApp.fileName, MainApp.usernameList.indexOf(MainApp.username));

            System.out.println(MainApp.username);
            System.out.println(MainApp.DEX);
            System.out.println(MainApp.STR);
            System.out.println(MainApp.INT);
            System.out.println(MainApp.cigs);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Saved");
            alert.setHeaderText(null);
            alert.setContentText(MainApp.username + ", Your progress has been saved, and you have been healed.");
            alert.showAndWait();
        }
        if ((event.getCode() == KeyCode.I)) {
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

        if ((event.getCode() == KeyCode.P)) {
            MainApp.addToInventory("WWA011011WRD022022WWD033033OIZ000000WRB044044WMA998709OIZ000000WMA088880OIZ000000");
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
            imgPlayer.setImage(front);
        }
        if (event.getCode() == KeyCode.S) {
            yvar = 0;
        }
    }

    public boolean col(ImageView block1, ImageView block2) {
        return (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));
    }

    @FXML
    Label lblStats, lblEquip;

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
    TextField txtIn;

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
    private void equip() {
        for (int i = 0; i < 9; i++) {
            if (MainApp.iSpaces[i] == MainApp.selected) {
                if (MainApp.weapon == MainApp.inventory[i]) {
                    lblEquip.setText("equip");
                    MainApp.weapon = null;

                } else {
                    MainApp.inventory[i].setAttack1();
                    MainApp.inventory[i].setAttack2();
                    MainApp.inventory[i].setAttack3();
                    MainApp.inventory[i].setAttack4();
                    MainApp.inventory[i].setCost();
                    MainApp.inventory[i].setSDamage();
                    MainApp.inventory[i].setDamage();
                    MainApp.weapon = MainApp.inventory[i];
                    System.out.println(MainApp.weapon.getSDamage());
                    lblEquip.setText("unequip");
                }
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
////}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ambient.setCycleCount(Timeline.INDEFINITE);
        ambient.setVolume(0.3);
        ambient.setStartTime(stoptime);
        ambient.play();
        Horizontal.setCycleCount(Timeline.INDEFINITE);
        Vertical.setCycleCount(Timeline.INDEFINITE);
        Horizontal.play();
        Vertical.play();
        if (MainApp.key) {
            imgKey.setVisible(false);
            imgPlayer.setLayoutX(392);
            imgPlayer.setLayoutY(65);
        }
        if (MainApp.getArea().equals("MainMenu")) {
            imgPlayer.setLayoutX(159);
            imgPlayer.setLayoutY(439);
            ambient.setStartTime(Duration.ZERO);
        }
        if (MainApp.WItem) {
            imgKey.setImage(paper);
            imgKey.setVisible(true);
            imgPaper.setImage(warden);
        }if (MainApp.Warden) {
            imgPlayer.setLayoutX(376);
            imgPlayer.setLayoutY(450);
            imgPaper.setImage(Fpaper);
            imgPaper.setVisible(true);
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
                System.out.println(MainApp.inventory[i].getDamage());
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
