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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author n00bi
 */
public class FXMLStartController implements Initializable {

    private boolean keys = false;

    @FXML
    private ImageView key;
    @FXML
    private ImageView lamp;
    @FXML
    private ImageView table;
    @FXML
    private ImageView bed;
    @FXML
    private ImageView toilet;
    @FXML
    private ImageView doorOpen;
    @FXML
    private ImageView doorClosed;
    @FXML
    private ImageView poster;
    @FXML
    private ImageView bars;
    @FXML
    private ImageView player;
    @FXML
    private Label keyFound;
    @FXML
    private Label exitMessage;
    @FXML
    private Button btnLeave;
    @FXML
    private Button btnStay;

    @FXML
    private void btnLeaveY(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLCommonRoom.fxml")); //where FXMLPage2 is the name of the scene

        Scene home_page_scene = new Scene(home_page_parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.hide();
        stage.setScene(home_page_scene);

        stage.setTitle("Common Room"); //changes the title
        stage.show(); //shows the new page
    }

    @FXML
    private void btnStayN(ActionEvent event) throws IOException {
        btnLeave.setVisible(false);
        btnStay.setVisible(false);
        exitMessage.setVisible(false);
    }

    @FXML
    Timeline Right = new Timeline(new KeyFrame(Duration.millis(10), ae -> moveRight()));
    @FXML
    Timeline Left = new Timeline(new KeyFrame(Duration.millis(10), ae -> moveLeft()));
    @FXML
    Timeline Up = new Timeline(new KeyFrame(Duration.millis(10), ae -> moveUp()));
    @FXML
    Timeline Down = new Timeline(new KeyFrame(Duration.millis(10), ae -> moveDown()));

    private void moveRight() {
        player.setTranslateX(player.getTranslateX() + 5);
        if (collision(player, table)) {
            player.setTranslateX(player.getTranslateX() - 5);
            if (keys == false) {
                keys = true;
                key.setVisible(false);
                keyFound.setVisible(true);
            }
        }
        if (collision(player, bed)) {
            player.setTranslateX(player.getTranslateX() - 5);
        }
        if (collision(player, toilet)) {
            player.setTranslateX(player.getTranslateX() - 5);
        }
        if (player.getTranslateX() >= 740) {
            player.setTranslateX(player.getTranslateX() - 5);
        }
        if (collision(player, doorClosed)) {
            player.setTranslateX(player.getTranslateX() - 5);
            if (keys == true) {
                doorClosed.setVisible(false);
                btnLeave.setVisible(true);
                btnStay.setVisible(true);
                exitMessage.setVisible(true);
            }
        }
    }

    private void moveLeft() {
        player.setTranslateX(player.getTranslateX() - 5);
        if (collision(player, table)) {
            player.setTranslateX(player.getTranslateX() + 5);
            if (keys == false) {
                keys = true;
                key.setVisible(false);
                keyFound.setVisible(true);
            }
        }
        if (collision(player, bed)) {
            player.setTranslateX(player.getTranslateX() + 5);
        }
        if (collision(player, toilet)) {
            player.setTranslateX(player.getTranslateX() + 5);
        }
        if (player.getTranslateX() <= 5) {
            player.setTranslateX(player.getTranslateX() + 5);
        }
        if (collision(player, doorClosed)) {
            player.setTranslateX(player.getTranslateX() + 5);
            if (keys == true) {
                doorClosed.setVisible(false);
                btnLeave.setVisible(true);
                btnStay.setVisible(true);
                exitMessage.setVisible(true);
            }
        }
    }

    private void moveUp() {
        player.setTranslateY(player.getTranslateY() - 5);
        if (collision(player, table)) {
            player.setTranslateY(player.getTranslateY() + 5);
            if (keys == false) {
                keys = true;
                key.setVisible(false);
                keyFound.setVisible(true);
            }
        }
        if (collision(player, bed)) {
            player.setTranslateY(player.getTranslateY() + 5);
        }
        if (collision(player, toilet)) {
            player.setTranslateY(player.getTranslateY() + 5);
        }
        if (player.getTranslateY() <= 40) {
            player.setTranslateY(player.getTranslateY() + 5);
        }
        if (collision(player, doorClosed)) {
            player.setTranslateY(player.getTranslateY() + 5);
            if (keys == true) {
                doorClosed.setVisible(false);
                btnLeave.setVisible(true);
                btnStay.setVisible(true);
                exitMessage.setVisible(true);
            }
        }
    }

    private void moveDown() {
        player.setTranslateY(player.getTranslateY() + 5);
        if (collision(player, table)) {
            player.setTranslateY(player.getTranslateY() - 5);
            if (keys == false) {
                keys = true;
                key.setVisible(false);
                keyFound.setVisible(true);
            }
        }
        if (collision(player, bed)) {
            player.setTranslateY(player.getTranslateY() - 5);
        }
        if (collision(player, toilet)) {
            player.setTranslateY(player.getTranslateY() - 5);
        }
        if (player.getTranslateY() >= 415) {
            player.setTranslateY(player.getTranslateY() - 5);
        }
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

    public boolean collision(ImageView imgP, ImageView imgO) {
        return (imgP.getBoundsInParent().intersects(imgO.getBoundsInParent()));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
