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
    private ImageView imgPlayer, imgEnemy, imgCommonRoom, imgO;
    @FXML
    private Label lblEquip;

    private double xvar = 0;
    private double yvar = 0;

    Timeline xmove = new Timeline(new KeyFrame(Duration.millis(5), ae -> x()));
    Timeline ymove = new Timeline(new KeyFrame(Duration.millis(5), ae -> y()));

    Image closed = new Image(getClass().getResource("/door closed.png").toString());
    Image open = new Image(getClass().getResource("/door_open.png").toString());

    MediaPlayer opensound = new MediaPlayer((new Media(getClass().getResource("/opening.mp3").toString())));

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
        if (e.getCode() == KeyCode.E && col(imgPlayer, imgCommonRoom)) {
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
        }

        if ((e.getCode() == KeyCode.I)) {
            if (!MainApp.invVis) {
                MainApp.invVis = true;
                pnlInv.setVisible(true);
                MainApp.displayIcons();
                /*Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLStart.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            home_page_scene.getRoot().requestFocus();*/
            } else {
                MainApp.invVis = false;
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
    
    @FXML
    private void buyWeapon(){
        if (MainApp.cigs>=1000){
            if(MainApp.nextSpot()!=8){
        int rand = ThreadLocalRandom.current().nextInt(1,3+1);
         Item i;
        if (rand==1){
             i=new Warrior(MainApp.getLevel(), "", "", "", "", 0, 0, 0, 0, "", "");
           
        }else if(rand==2) {
           i=new Mage(MainApp.getLevel(), "", "", "", "", 0, 0, 0, 0, "", "");  
        }else{
             i=new Rogue(MainApp.getLevel(), "", "", "", "", 0, 0, 0, 0, "", "");
        }
        MainApp.cigs-=1000;
        addToInventory(i);
    }
        else{
            //not enough
        }
            
    }else{
            
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
    Pane pnlInv;

    @FXML
    private void click(MouseEvent e) {

        MainApp.selected = (ImageView) e.getSource();

        for (int i = 0; i < 9; i++) {

            if (MainApp.iSpaces[i] == MainApp.selected&&!MainApp.inventory[i].getType().equals("Item")) {
                MainApp.rec[i].toFront();
                MainApp.iSpaces[i].toFront();
                MainApp.rec[i].setFill(Color.BLACK);
if(MainApp.itemsEquipped.contains(MainApp.inventory[i])){
    lblEquip.setText("unequip");
}else{
    if (MainApp.itemsEquipped.size()==4){
        
        lblEquip.setDisable(true);
    }
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
private void equip(){
        for (int i=0; i<9;i++){
            if(MainApp.iSpaces[i] == MainApp.selected){
           if(MainApp.itemsEquipped.contains(MainApp.inventory[i])){
               MainApp.itemsEquipped.remove(MainApp.inventory[i]);    
               lblEquip.setText("equip");
               
  
    }else{
              
               MainApp.itemsEquipped.add(MainApp.inventory[i]);
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
        MainApp.cigs=2000;
        xmove.setCycleCount(Timeline.INDEFINITE);
        ymove.setCycleCount(Timeline.INDEFINITE);
        xmove.play();
        ymove.play();
        int xplace = ThreadLocalRandom.current().nextInt(200, 700 + 1);
        int yplace = ThreadLocalRandom.current().nextInt(50, 400 + 1);
        System.out.print(xplace + " " + yplace);
        imgO.setLayoutX(xplace);
        imgO.setLayoutY(yplace);
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
