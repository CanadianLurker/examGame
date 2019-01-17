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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import static humphriesmartinfice.examproject.MainApp.*;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author n00bi
 */
public class FXMLCommonRoomController implements Initializable {

    @FXML
    private ImageView imgGuard, imgGuardRoom, imgBlockA, imgBlockB, imgCell, imgPlayer;

    Image closed = new Image(getClass().getResource("/door closed.png").toString());
    Image open = new Image(getClass().getResource("/door_open.png").toString());
    Image back = new Image(getClass().getResource("/Prisoner2B.png").toString());
    Image front = new Image(getClass().getResource("/prisoner2.png").toString());

    int test = 0;
    private int xvar, yvar;

    Timeline Horizontal = new Timeline(new KeyFrame(Duration.millis(5), ae -> x()));
    Timeline Vertical = new Timeline(new KeyFrame(Duration.millis(5), ae -> y()));

    private void y() {
        imgPlayer.setLayoutY(imgPlayer.getLayoutY() + yvar);
        if (col(imgGuard, imgPlayer) || imgPlayer.getLayoutX() >= 830 || imgPlayer.getLayoutX() <= 0 || imgPlayer.getLayoutY() >= 470 || imgPlayer.getLayoutY() <= 30) {
            imgPlayer.setLayoutY(imgPlayer.getLayoutY() - yvar);
        }
    }

    private void x() {
        imgPlayer.setLayoutX(imgPlayer.getLayoutX() + xvar);
        if (col(imgGuard, imgPlayer) || imgPlayer.getLayoutX() >= 830 || imgPlayer.getLayoutX() <= 0 || imgPlayer.getLayoutY() >= 470 || imgPlayer.getLayoutY() <= 30) {
            imgPlayer.setLayoutX(imgPlayer.getLayoutX() - xvar);
        }
        if (col(imgPlayer, imgCell)) {
            imgCell.setImage(open);
        }
        if (col(imgPlayer, imgBlockA)) {
            imgBlockA.setImage(open);
        }
        if (col(imgPlayer, imgBlockB)) {
            imgBlockB.setImage(open);
        }
        if (col(imgPlayer, imgGuardRoom)) {
            imgGuardRoom.setImage(open);
        }
        if (col(imgPlayer, imgCell)== false) {
            imgCell.setImage(closed);
        }
        if (col(imgPlayer, imgBlockA) == false) {
            imgBlockA.setImage(closed);
        }
        if (col(imgPlayer, imgBlockB)== false) {
            imgBlockB.setImage(closed);
        }
        if (col(imgPlayer, imgGuardRoom)== false) {
            imgGuardRoom.setImage(closed);
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
        }
        if ((event.getCode() == KeyCode.S)) {
            yvar = 1;
        }
        if (event.getCode() == KeyCode.E && col(imgPlayer, imgBlockA)) {
            saveLoc(FXMLLoader.load(getClass().getResource("/fxml/BlockA.fxml")), 750,88);
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/BlockA.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("Block A");
            stage.show();
            home_page_scene.getRoot().requestFocus();
        }
        if (event.getCode() == KeyCode.E && col(imgPlayer, imgGuardRoom)) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLGuardRoom.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("Guard Room");
            stage.show();
            home_page_scene.getRoot().requestFocus();
        }
        if (event.getCode() == KeyCode.E && col(imgPlayer, imgBlockB)) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/BlockB.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("Block B");
            stage.show();
            home_page_scene.getRoot().requestFocus();
        }
        if (event.getCode() == KeyCode.E && col(imgPlayer, imgCell)) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLStart.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("Your Cell");
            stage.show();
            home_page_scene.getRoot().requestFocus();
        }
         if ((event.getCode() == KeyCode.I)) {
            if(!MainApp.invVis){
            MainApp.invVis=true;
            pnlInv.setVisible(true);
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
    public void keyReleased(KeyEvent event) {
        if ((event.getCode() == KeyCode.D)) {
            xvar = 0;
        }
        if ((event.getCode() == KeyCode.A)) {
            xvar = 0;
        }
        if ((event.getCode() == KeyCode.W)) {
            yvar = 0;
            imgPlayer.setImage(back);
        }
        if ((event.getCode() == KeyCode.S)) {
            yvar = 0;
            imgPlayer.setImage(front);
        }
    }

    public boolean col(ImageView block1, ImageView block2) {
        return (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));
    }
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

            if (MainApp.iSpaces[i] == MainApp.selected) {
                MainApp.rec[i].toFront();
                MainApp.iSpaces[i].toFront();
                MainApp.rec[i].setFill(Color.BLACK);
                
                
                ////////////// make sure to change damage
                
                
if(MainApp.inventory[i].getType().equals("Warrior")){
                System.out.println(MainApp.inventory[i].getClass().getSimpleName() + " , Level=" + MainApp.inventory[i].getLevel()+", Damage"+MainApp.inventory[i].getDamage()/2); //////////damage!!!!!!!!!!
}

else if(MainApp.inventory[i].getType().equals("Rogue")){
                System.out.println(MainApp.inventory[i].getClass().getSimpleName() + " , Level=" + MainApp.inventory[i].getLevel()+", Damage"+(MainApp.inventory[i].getDamage()-2));      //////////damage!!!!!!!!!!
}else{
    System.out.println(MainApp.inventory[i].getClass().getSimpleName() + " , Level=" + MainApp.inventory[i].getLevel()+", Damage"+MainApp.inventory[i].getDamage());   //////////damage!!!!!!!!!!
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
                MainApp.inventory[i] = new Item();
                MainApp.iSpaces[i].toFront();

                MainApp.iSpaces[i].setEffect(null);
                MainApp.displayIcons();

            }
        }
    }

    ////not needed{
    @FXML
    private void btnIn() {
        MainApp.addToInventory(txtIn.getText());
    }

    @FXML
    private void save() {
        System.out.println(MainApp.saveInventory());
    }
////}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Horizontal.setCycleCount(Timeline.INDEFINITE);
        Vertical.setCycleCount(Timeline.INDEFINITE);
        Horizontal.play();
        Vertical.play();
        
        
        
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
                 MainApp.inventory[i] = new Item();
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
         
         MainApp.txtIn=txtIn;
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
