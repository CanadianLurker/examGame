/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humphriesmartinfice.examproject;

import static humphriesmartinfice.examproject.MainApp.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
public class BlockAController implements Initializable {

    @FXML
    private Pane panPlayer, panEnemy, panTourn;
    @FXML
    private Polygon polPlayer, polEnemy, polDoor;
    @FXML
    private Button btnFight1, btnFight2, btnFight3, btnFight4;

    private double xvar = 0;
    private double yvar = 0;

    private Stage stage;
    private Scene scene;
    @FXML
    private Label lblFight1, lblFight2, lblFight3, lblFight4;
    @FXML
    private ImageView imgCommonRoom, imgDoor1, imgDoor2, imgDoor3, imgEnemy1, imgEnemy2, imgEnemy3;

    Image closed = new Image(getClass().getResource("/door closed.png").toString());
    Image open = new Image(getClass().getResource("/door_open.png").toString());
    
    MediaPlayer opensound = new MediaPlayer((new Media(getClass().getResource("/opening.mp3").toString())));

    Timeline xmove = new Timeline(new KeyFrame(Duration.millis(5), ae -> x()));
    Timeline ymove = new Timeline(new KeyFrame(Duration.millis(5), ae -> y()));
    Timeline enemymove = new Timeline(new KeyFrame(Duration.millis(5), ae -> yes()));
    //  Timeline buffer = new Timeline(new KeyFrame(Duration.millis(5), ae -> goFight()));

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
        }
        if (e.getCode() == KeyCode.W) {
            yvar = -1;
        }
        if (e.getCode() == KeyCode.E && col(polPlayer, polDoor)) {
            saveLoc("BlockA");
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLCommonRoom.fxml")); 
            scene = new Scene(home_page_parent);
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.close();
            stage.setScene(scene);
            stage.setTitle("Common Room");
            stage.setResizable(false);
            stage.show();
            home_page_parent.requestFocus();
        }
         if ((e.getCode() == KeyCode.I)) {
            if(!MainApp.invVis){
            MainApp.invVis=true;
            pnlInv.setVisible(true);
                        MainApp.displayIcons();

            /*Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLStart.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            home_page_scene.getRoot().requestFocus();*/
            }else{
                 MainApp.invVis=false;
           pnlInv.setVisible(false);
           /*Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLStart.fxml"));
           Scene home_page_scene = new Scene(home_page_parent);
           home_page_scene.getRoot().requestFocus();*/
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

    private void x() {
        if (panTourn.isVisible() == false) {
            panPlayer.setLayoutX(panPlayer.getLayoutX() + xvar);
        }
        if (panPlayer.getLayoutX() >= 830 || panPlayer.getLayoutX() <= 0 || panPlayer.getLayoutY() >= 470 || panPlayer.getLayoutY() <= 30) {
            panPlayer.setLayoutX(panPlayer.getLayoutX() - xvar);
        }
        if (col(polPlayer, polEnemy)) {
            panTourn.setVisible(true);
            lblFight1.setText("Two Enemies\nEnemies Levels: " + getLevel());
            if (getLevel() < 5) {
                lblFight2.setText("You are not high enough of a level for this fight. \nLevel required: 5");
                btnFight2.setDisable(true);
            }
            if (getLevel() < 15) {
                lblFight3.setText("You are not high enough of a level for this fight. \nLevel required: 15");
                btnFight3.setDisable(true);
            }
            if (getLevel() < 30) {
                lblFight4.setText("You are not high enough of a level for this fight. \nLevel required: 30");
                btnFight4.setDisable(true);
            }
            if (getLevel() >= 5) {
                lblFight2.setText("Boss\nEnemy Level: " + (getLevel() + 5));
            }
            if (getLevel() >= 15) {
                lblFight3.setText("Three Enemies\nEnemies Levels: " + (getLevel()));
            }
            if (getLevel() >= 30) {
                lblFight4.setText("Head Guard Johnathan\nEnemy Level: " + (getLevel() + 10));
            }
        }
        if (col(polDoor, polPlayer)) {
            imgCommonRoom.setImage(open);
            opensound.play();
        }
        if (col(polDoor, polPlayer) == false) {
            imgCommonRoom.setImage(closed);
            opensound.stop();
        }
    }
    
    @FXML
    private void NoFight(){
    panTourn.setVisible(false);
    panPlayer.setLayoutX(368);
    panPlayer.setLayoutY(341);
    }

    private void y() {
        if (panTourn.isVisible() == false) {
            panPlayer.setLayoutY(panPlayer.getLayoutY() + yvar);
        }
        if (panPlayer.getLayoutX() >= 830 || panPlayer.getLayoutX() <= 0 || panPlayer.getLayoutY() >= 470 || panPlayer.getLayoutY() <= 30) {
            panPlayer.setLayoutY(panPlayer.getLayoutY() - yvar);
        }
    }

    public boolean col(Object block1, Object block2) {
        Shape s1 = (Shape) block1;
        Shape s2 = (Shape) block2;
        Shape a = Shape.intersect(s1, s2);
        return a.getBoundsInLocal().getWidth() != -1;
    }

    @FXML
    private void Fight(ActionEvent e) throws IOException {
        if (btnFight1.isArmed()) {
            enemies.add(new Enemy(getLevel()));
            enemies.add(new Enemy(getLevel()));
        }
        if (btnFight2.isArmed()) {
            enemies.add(new Enemy(getLevel() + 5));
        }
        if (btnFight3.isArmed()) {
            enemies.add(new Enemy(getLevel()));
            enemies.add(new Enemy(getLevel()));
            enemies.add(new Enemy(getLevel()));
        }
        if (btnFight4.isArmed()) {
            enemies.add(new Enemy(getLevel() + 10));
        }
        panTourn.setVisible(false);
        xmove.stop();
        ymove.stop();
        EnemyEmerge(enemies.size());
        saveLoc("/fxml/BlockA.fxml");
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLCombat.fxml")); 
        scene = new Scene(home_page_parent);
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setResizable(false);
    }

    private void EnemyEmerge(int i) {
        if (i == 1) {
            imgEnemy2.setVisible(true);
        }
        if (i == 2) {
            imgEnemy1.setVisible(true);
            imgEnemy3.setVisible(true);
        }
        if (i == 3) {
            imgEnemy1.setVisible(true);
            imgEnemy2.setVisible(true);
            imgEnemy3.setVisible(true);
        }
        enemymove.setCycleCount(Timeline.INDEFINITE);
        enemymove.play();
        opensound.play();
    }

    private void yes() {
        if (imgEnemy1.getLayoutY() < 200) {
            imgEnemy1.setLayoutY(imgEnemy1.getLayoutY() + 1);
            imgEnemy2.setLayoutY(imgEnemy2.getLayoutY() + 1);
            imgEnemy3.setLayoutY(imgEnemy3.getLayoutY() + 1);
            if (imgEnemy1.isVisible()) {
                imgDoor1.setImage(open);
            }
            if (imgEnemy2.isVisible()) {
                imgDoor2.setImage(open);
            }
            if (imgEnemy3.isVisible()) {
                imgDoor3.setImage(open);
            }
        }
        if (imgEnemy1.getLayoutY() >= 200) {
            enemymove.stop();
            imgDoor1.setImage(closed);
            imgDoor2.setImage(closed);
            imgDoor3.setImage(closed);
            stage.hide();
            stage.setScene(scene);
            stage.setTitle("Combat!!!");
            stage.show();
        }
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

            if (MainApp.iSpaces[i] == MainApp.selected) {
                MainApp.rec[i].toFront();
                MainApp.iSpaces[i].toFront();
                MainApp.rec[i].setFill(Color.BLACK);
                
                
                ////////////// make sure to change damage
                
                
 lblStats.setText("Level: "+MainApp.inventory[i].getLevel() +"\n"+"Rarity: "+MainApp.inventory[i].getRarity()+"\n"+"Damage: "+MainApp.inventory[i].getDamage());
    System.out.println(MainApp.inventory[i].getClass().getSimpleName() + " , Level=" + MainApp.inventory[i].getLevel()+", Damage"+MainApp.inventory[i].getDamage());   //////////damage!!!!!!!!!!



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
                MainApp.inventory[i] = new Item();
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
        panTourn.setVisible(false);
        xmove.setCycleCount(Timeline.INDEFINITE);
        ymove.setCycleCount(Timeline.INDEFINITE);
        xmove.play();
        ymove.play();
        
        
        
       
        
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
         
         MainApp.img1=img1;
         MainApp.img2=img2;
         MainApp.img3=img3;
         MainApp.img4=img4;
         MainApp.img5=img5;
         MainApp.img6=img6;
         MainApp.img7=img7;
         MainApp.img8=img8;
         MainApp.img9=img9;
    }

}
