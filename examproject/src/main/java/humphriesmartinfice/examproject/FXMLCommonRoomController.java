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

/**
 * FXML Controller class
 *
 * @author n00bi
 */
public class FXMLCommonRoomController implements Initializable {

    @FXML
    private ImageView imgGuard, imgGuardRoom, imgBlockA, imgBlockB, imgCell;
    @FXML
    private Polygon polPlayer;
    @FXML
    private Pane panPlayer;

    int test = 0;
    private int xvar, yvar;

    Timeline Horizontal = new Timeline(new KeyFrame(Duration.millis(15), ae -> x()));
    Timeline Vertical = new Timeline(new KeyFrame(Duration.millis(15), ae -> y()));

    private void y() {
        panPlayer.setLayoutY(panPlayer.getLayoutY() + yvar);
    }

    private void x() {
        panPlayer.setLayoutX(panPlayer.getLayoutX() + xvar);
        if(col(polPlayer, imgBlockA)){
        try {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/BlockA.fxml")); //where FXMLPage2 is the name of the scene
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = new Stage();
            stage.setScene(home_page_scene);
            stage.setTitle("Cell Block A"); //changes the title
            stage.show(); //shows the new page
            home_page_scene.getRoot().requestFocus();
        } catch (IOException iOException) {
        }}
    }

    @FXML
    public void keyPressed(KeyEvent event) {
        if ((event.getCode() == KeyCode.D)) {
            xvar = 2;
        }
        if ((event.getCode() == KeyCode.A)) {
            xvar = - 2;
        }
        if ((event.getCode() == KeyCode.W)) {
            yvar = -2;
        }
        if ((event.getCode() == KeyCode.S)) {
            yvar = 2;
        }
    }

    @FXML
    public void keyReleased(KeyEvent event) {
        if ((event.getCode() == KeyCode.D)) {
            xvar = 0;
        }
        if ((event.getCode() == KeyCode.A)) {
            xvar = -0;
        }
        if ((event.getCode() == KeyCode.W)) {
            yvar = -0;
        }
        if ((event.getCode() == KeyCode.S)) {
            yvar = 0;
        }
    }

    public boolean col(Object block1, Object block2) {
        Shape s1 = (Shape) block1;
        Shape s2 = (Shape) block2;
        Shape a = Shape.intersect(s1, s2);
        return a.getBoundsInLocal().getWidth() != -1;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Horizontal.setCycleCount(Timeline.INDEFINITE);
        Vertical.setCycleCount(Timeline.INDEFINITE);
        Horizontal.play();
        Vertical.play();
    }

}
