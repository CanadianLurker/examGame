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

/**
 * FXML Controller class
 *
 * @author n00bi
 */
public class FXMLCommonRoomController implements Initializable {
    
    @FXML
    private ImageView guard;
    @FXML
    private ImageView player;
    @FXML
    private ImageView guardRoom;
    @FXML
    private ImageView blockA;
    @FXML
    private ImageView blockB;
    @FXML
    private ImageView cellDoor;
    @FXML
    private Label lblRoom;
    @FXML
    private Label lblA;
    @FXML
    private Label lblB;
    @FXML
    private Label lblCell;
    @FXML 
    private Button leave;
    @FXML 
    private Button stay;
    @FXML 
    private Label lblMessage;
    @FXML
    private Polygon polyPlayer;
    @FXML
    private Pane panPlayer;
    @FXML
    private Polygon polyA;
    @FXML
    private Pane panA;
    @FXML
    private Polygon polyB;
    @FXML
    private Pane panB;
    
    int test = 0;
    
    @FXML
    Timeline Right = new Timeline(new KeyFrame(Duration.millis(25), ae -> moveRight()));
    @FXML
    Timeline Left = new Timeline(new KeyFrame(Duration.millis(25), ae -> moveLeft()));
    @FXML
    Timeline Up = new Timeline(new KeyFrame(Duration.millis(25), ae -> moveUp()));
    @FXML
    Timeline Down = new Timeline(new KeyFrame(Duration.millis(25), ae -> moveDown()));

    private void moveRight() {
        panPlayer.setTranslateX(panPlayer.getTranslateX() + 5);
        if (panPlayer.getTranslateX() >= 740) {
            panPlayer.setTranslateX(panPlayer.getTranslateX() - 5);
        }
        if (collision(panPlayer, panA)) {
            panPlayer.setTranslateX(panPlayer.getTranslateX() - 5);
            aTest();
        }
        if (collision(panPlayer, panB)) {
            panPlayer.setTranslateX(panPlayer.getTranslateX() - 5);
            bTest();
        }
        if (collision(panPlayer, guard)) {
            panPlayer.setTranslateX(panPlayer.getTranslateX() - 5);
        }
    }

    private void moveLeft() {
        panPlayer.setTranslateX(panPlayer.getTranslateX() - 5);
        if (panPlayer.getTranslateX() <= 5) {
            panPlayer.setTranslateX(panPlayer.getTranslateX() + 5);
        }
        if (collision(panPlayer, panA)) {
            panPlayer.setTranslateX(panPlayer.getTranslateX() + 5);
            aTest();
        }
        if (collision(panPlayer, panB)) {
            panPlayer.setTranslateX(panPlayer.getTranslateX() + 5);
            bTest();
        }
        if (collision(panPlayer, guard)) {
            panPlayer.setTranslateX(panPlayer.getTranslateX() + 5);
        }
    }

    private void moveUp() {
        panPlayer.setTranslateY(panPlayer.getTranslateY() - 5);
        if (panPlayer.getTranslateY() <= 40) {
            panPlayer.setTranslateY(panPlayer.getTranslateY() + 5);
        }
        if (collision(panPlayer, panA)) {
            panPlayer.setTranslateY(panPlayer.getTranslateY() + 5);
            aTest();
        }
        if (collision(panPlayer, panB)) {
            panPlayer.setTranslateY(panPlayer.getTranslateY() + 5);
            bTest();
        }
        if (collision(panPlayer, guard)) {
            panPlayer.setTranslateY(panPlayer.getTranslateY() + 5);
        }
    }

    private void moveDown() {
        panPlayer.setTranslateY(panPlayer.getTranslateY() + 5);
        if (panPlayer.getTranslateY() >= 415) {
            panPlayer.setTranslateY(panPlayer.getTranslateY() - 5);
        }
        if (collision(panPlayer, panA)) {
            panPlayer.setTranslateY(panPlayer.getTranslateY() - 5);
            aTest();
        }
        if (collision(panPlayer, panB)) {
            panPlayer.setTranslateY(panPlayer.getTranslateY() - 5);
            bTest();
        }
        if (collision(panPlayer, guard)) {
            panPlayer.setTranslateY(panPlayer.getTranslateY() - 5);
        }
    }
    
    private void aTest() {
            leave.setVisible(true);
            stay.setVisible(true);
            lblMessage.setVisible(true);
            test = 1;
            lblMessage.setText("Would you like to enter Cell Block A?");
    }      
    private void bTest() {
            leave.setVisible(true);
            stay.setVisible(true);
            lblMessage.setVisible(true);
            test = 2;
            lblMessage.setText("Would you like to enter Cell Block B?");
    }    
    
    @FXML
    public void keyPressed(KeyEvent event) {
        if ((event.getCode() == KeyCode.D)) {
            Right.setCycleCount(Timeline.INDEFINITE);
            Right.play();
        }
        if ((event.getCode() == KeyCode.A)) {
            Left.setCycleCount(Timeline.INDEFINITE);
            Left.play();
        }
        if ((event.getCode() == KeyCode.W)) {
            Up.setCycleCount(Timeline.INDEFINITE);
            Up.play();
        }
        if ((event.getCode() == KeyCode.S)) {
            Down.setCycleCount(Timeline.INDEFINITE);
            Down.play();
        }
    }

    @FXML
    public void keyReleased(KeyEvent event) {
        if ((event.getCode() == KeyCode.D)) {
            Right.stop();
        }
        if ((event.getCode() == KeyCode.A)) {
            Left.stop();
        }
        if ((event.getCode() == KeyCode.W)) {
            Up.stop();
        }
        if ((event.getCode() == KeyCode.S)) {
            Down.stop();
        }
    }

    public boolean collision(Object block1, Object block2) {
        try {
            //If the objects can be changed to shapes just see if they intersect
            Shape s1 = (Shape) block1;
            Shape s2 = (Shape) block2;
            Shape a = Shape.intersect(s1, s2);
            return a.getBoundsInLocal().getWidth() != -1;
        } catch (Exception e) {
            //If the objects can't be changed to shapes, make a shape with there size and location
            //Then rotate them

            //Gets the real location and size of the first object
            double rectX = ((Node) block1).getLayoutX() + ((Node) block1).getTranslateX();
            double rectY = ((Node) block1).getLayoutY() + ((Node) block1).getTranslateY();
            double rectWidth = ((Node) block1).getBoundsInLocal().getWidth();
            double rectHeight = ((Node) block1).getBoundsInLocal().getHeight();

            //Gets the real location and sizr of the second object
            double rectX2 = ((Node) block2).getLayoutX() + ((Node) block2).getTranslateX();
            double rectY2 = ((Node) block2).getLayoutY() + ((Node) block2).getTranslateY();
            double rectWidth2 = ((Node) block2).getBoundsInLocal().getWidth();
            double rectHeight2 = ((Node) block2).getBoundsInLocal().getHeight();

            //makes two new shapes and rotates them
            Shape rect = new Rectangle(rectX, rectY, rectWidth, rectHeight);
            Shape rect2 = new Rectangle(rectX2, rectY2, rectWidth2, rectHeight2);
            rect.setRotate(((Node) ((Node) block1)).getRotate());
            rect2.setRotate(((Node) block2).getRotate());
            //Makes a new shapes of where they touch
            Shape a = Shape.intersect(rect, rect2);

            //returns if they touch
            return a.getBoundsInLocal().getWidth() != -1;
        }
      }
    
    @FXML
    private void btnLeave(ActionEvent event) throws IOException {
        if (test == 1) {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/BlockA.fxml")); //where FXMLPage2 is the name of the scene

        Scene home_page_scene = new Scene(home_page_parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.hide();
        stage.setScene(home_page_scene);

        stage.setTitle("Cell Block A"); //changes the title
        stage.show(); //shows the new page
        home_page_scene.getRoot().requestFocus();
    }
        if (test == 2) {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/BlockB.fxml")); //where FXMLPage2 is the name of the scene

        Scene home_page_scene = new Scene(home_page_parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.hide();
        stage.setScene(home_page_scene);

        stage.setTitle("Cell Block B"); //changes the title
        stage.show(); //shows the new page
        home_page_scene.getRoot().requestFocus();
    }
    }
    
    @FXML
    private void btnStay(ActionEvent event) throws IOException {
        leave.setVisible(false);
        stay.setVisible(false);
        lblMessage.setVisible(false);
        test = 0;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
