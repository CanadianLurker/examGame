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
import javafx.scene.input.KeyCode;
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
    private Polygon polPlayer, polEnemy;
    @FXML
    private Button btnFight1, btnFight2, btnFight3, btnFight4;
    @FXML
    private Label lblFight1, lblFight2, lblFight3, lblFight4;

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

    private void x() {
        if (panTourn.isVisible() == false) {
            panPlayer.setLayoutX(panPlayer.getLayoutX() + xvar);
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
    }

    private void y() {
        if (panTourn.isVisible() == false) {
            panPlayer.setLayoutY(panPlayer.getLayoutY() + yvar);
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
        MainApp.saveLoc(FXMLLoader.load(getClass().getResource("/fxml/BlockA.fxml")), panPlayer.getLayoutX(), panPlayer.getLayoutY());
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLCombat.fxml")); // now hosting the testing grounds for combat
        Scene scene = new Scene(home_page_parent);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setTitle("Combat!!!");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Shit not broke yo");
        panTourn.setVisible(false);
        xmove.setCycleCount(Timeline.INDEFINITE);
        ymove.setCycleCount(Timeline.INDEFINITE);
        xmove.play();
        ymove.play();
        panPlayer.setLayoutX(getLocX() - 10);
        panPlayer.setLayoutY(getLocY());
    }

}
