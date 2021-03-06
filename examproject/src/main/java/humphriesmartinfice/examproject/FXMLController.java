package humphriesmartinfice.examproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import static humphriesmartinfice.examproject.MainApp.*;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLController implements Initializable {

    @FXML
    private Label lblSaves, lblEnter, lblTitle;
    @FXML
    private Button btnLoad, btnOpt, btnExit, btnNew, btnDelete;
    @FXML
    private ListView listSaves;
    @FXML
    private ImageView imgL;
    private double op = 0.8;
    private double vol = 1;

    private Image light = new Image(getClass().getResource("/lightning.gif").toString());

    Timeline lightning = new Timeline(new KeyFrame(Duration.millis(50), ae -> enter()));
    Timeline quiettime = new Timeline(new KeyFrame(Duration.millis(150), ae -> shush()));

    MediaPlayer kaboom = new MediaPlayer((new Media(getClass().getResource("/thunder.mp3").toString())));
    MediaPlayer rain = new MediaPlayer((new Media(getClass().getResource("/rainsound.mp3").toString())));

    @FXML
    private void btnLoad(ActionEvent event) throws IOException {
        if (btnLoad.getOpacity() > 0.4) {
            int record = listSaves.getSelectionModel().getSelectedIndex();
            if (record != -1) {
                MainApp.user.open(MainApp.fileName, record);
                System.out.println(MainApp.username);
                System.out.println(MainApp.level);
                System.out.println(MainApp.DEX);
                System.out.println(MainApp.STR);
                System.out.println(MainApp.INT);
                System.out.println(MainApp.cigs);
                saveLoc("MainMenu");
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLStart.fxml")); //where FXMLPage2 is the name of the scene
                Scene home_page_scene = new Scene(home_page_parent);
                //get reference to the stage 
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide(); //optional
                stage.setScene(home_page_scene); //puts the new scence in the stage
                stage.setTitle("Spawn Room"); //changes the title
                stage.setResizable(false);
                stage.show(); //shows the new page
                home_page_scene.getRoot().requestFocus();
                quiettime.play();
            }
        }
    }
    boolean dif = false;

    @FXML
    private void btnNewGame(ActionEvent event) throws IOException {
        if (btnLoad.getOpacity() > 0.4) {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("New Game");
            if (!dif) {
                dialog.setHeaderText(null);
            } else {
                dialog.setHeaderText("Please choose a different username");
                dif = false;

            }

            try {
                dialog.setContentText("Enter a username");
                Optional<String> result = dialog.showAndWait();
                for (int i = 0; i < MainApp.usernameList.size(); i++) {
                    if (dialog.getResult().trim().equals("") || dialog.getResult().trim().equals(MainApp.usernameList.get(i))) {
                        dif = true;
                        btnNewGame(event);
                        return;
                    }
                }
                MainApp.username = dialog.getResult();

                MainApp.usernameList.add(username);
                setINT(1);
                setSTR(1);
                setDEX(1);
                setLevel(1);
                setHealthMAX();
                setHealth(getHealthMAX());
                setManaMAX();
                setMana(getManaMAX());
                setEXP(0);
                setEXPNeeded();
                int rand = ThreadLocalRandom.current().nextInt(1, 3);
                if (rand == 1) {
                    weapon = new Rogue(1, "", "", "", "", 0, 0, 0, 0, "");
                }
                if (rand == 2) {
                    weapon = new Mage(1, "", "", "", "", 0, 0, 0, 0, "");
                }
                if (rand == 3) {
                    weapon = new Warrior(1, "", "", "", "", 0, 0, 0, 0, "");
                }
                MainApp.addToInventory(weapon);
                xplace = ThreadLocalRandom.current().nextInt(200, 700 + 1);
                yplace = ThreadLocalRandom.current().nextInt(50, 400 + 1);
                System.out.println(MainApp.username);
                System.out.println(MainApp.DEX);
                System.out.println(MainApp.STR);
                System.out.println(MainApp.INT);
                System.out.println(MainApp.cigs);
                MainApp.user.save(MainApp.fileName, MainApp.usernameList.indexOf(MainApp.username));
                saveLoc("MainMenu");
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLStart.fxml")); //where FXMLPage2 is the name of the scene
                Scene home_page_scene = new Scene(home_page_parent);
                //get reference to the stage 
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(home_page_scene); //puts the new scence in the stage
                stage.setTitle("Your Cell"); //changes the title
                stage.setResizable(false);
                stage.show(); //shows the new page
                home_page_scene.getRoot().requestFocus();
                quiettime.play();
            } catch (Exception e) {

            }
        }
    }

    @FXML
    private void delete(ActionEvent e) {
        if (btnLoad.getOpacity() > 0.4) {
            int record = listSaves.getSelectionModel().getSelectedIndex();
            if (record != -1) {
                user.delete(fileName, record);
                listSaves.getItems().remove(record);
                listSaves.getSelectionModel().clearSelection();
                MainApp.usernameList.remove(record);
            }
        }
    }

    @FXML
    private void btnOptions(ActionEvent event) throws IOException {
        if (btnOpt.getOpacity() > 0.8) {
            listSaves.getItems().clear();
            MainApp.usernameList.clear();
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLHelp.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            //get reference to the stage 
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("Help Screen");
            stage.setResizable(false);
            stage.show();
            home_page_scene.getRoot().requestFocus();
        }
    }

    @FXML
    private void btnExit(ActionEvent event) {
        if (btnExit.getOpacity() > 0.4) {
            System.exit(0);
        }
    }

    @FXML
    private void pressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && btnExit.getOpacity() == 0) {
            imgL.setImage(light);
            kaboom.play();
            lightning.setCycleCount(Timeline.INDEFINITE);
            lightning.play();
            rain.setVolume(0.8);
        }
    }

    private void enter() {
        if (lblEnter.getOpacity() > 0) {
            op = op - 0.05;
            lblEnter.setOpacity(op);
        }
        if (lblEnter.getOpacity() <= 0) {
            imgL.setImage(null);
            op = op + 0.008;
            lblSaves.setOpacity(op);
            listSaves.setOpacity(op);
            btnLoad.setOpacity(op);
            btnOpt.setOpacity(op);
            btnExit.setOpacity(op);
            btnNew.setOpacity(op);
            btnDelete.setOpacity(op);
            lblTitle.setOpacity(op + 0.013);
        }
        if (btnExit.getOpacity() >= 0.6) {
            lightning.stop();
            kaboom.stop();
        }
    }

    private void shush() {
        vol = vol - 0.02;
        rain.setVolume(vol);
        if (rain.getVolume() <= 0) {
            quiettime.stop();
            rain.stop();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dif = false;
        for (int i = 0; i < 9; i++) {
            MainApp.inventory[i] = new Weapon();
        }

        for (int j = 0; j < user.numRecord(fileName); j++) {
            if (!MainApp.usernameList.contains(user.openUser(fileName, j))) {
                MainApp.usernameList.add(user.openUser(fileName, j));
            }
        }
        listSaves.getItems().addAll(MainApp.usernameList);
        rain.setCycleCount(Timeline.INDEFINITE);
        rain.play();
        quiettime.setCycleCount(Timeline.INDEFINITE);
    }
}
