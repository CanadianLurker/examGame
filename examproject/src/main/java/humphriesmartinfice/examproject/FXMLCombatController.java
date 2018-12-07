/*
 *  By:Shayne Humphries
 *  Date:
 *  Purpose:
 */
package humphriesmartinfice.examproject;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static humphriesmartinfice.examproject.MainApp.*;

public class FXMLCombatController implements Initializable {

    @FXML
    private ProgressBar prgMC, prgEnemy, prgMCMana, prgEnemyMana, prgEXP;
    @FXML
    private ImageView imgMC, imgEnemy;
    @FXML
    private Button btnAttack, btnItems, btnOther, btnChoice1, btnChoice2, btnChoice3, btnChoice4;
    @FXML
    private Pane panEXP;
    @FXML
    private Label lblMC, lblEnemy, lblMCMana, lblEnemyMana, lblHP, lblLevel;

    private String Choice1, Choice2, Choice3, Choice4;

    private double MCHealth, EnemyHealth, MCHealthMAX, EnemyHealthMAX, MCMana, MCManaMAX, EnemyMana, EnemyManaMAX;

    private boolean combat = true; //combat will always be active when coming into this scene

    Timeline endturn = new Timeline(new KeyFrame(Duration.millis(1500), ae -> enemyTurn()));

    Mage weapon = new Mage(1);

    // MediaPlayer music = new MediaPlayer((new Media(getClass().getResource("/Background.mp3").toString())));
    @FXML
    private void Primary(ActionEvent event) {
        if (btnAttack.isArmed()) { //checks to see which primary button is pressed
            Choice1 = weapon.getAttack1();
            Choice2 = weapon.getAttack2(); //gets the name of the attacks from the weapon
            Choice3 = weapon.getAttack3();
            Choice4 = weapon.getAttack4();
            btnAttack.setDisable(true);
            btnItems.setDisable(false); //disables the button that was pressed
            btnOther.setDisable(false);
        } else if (btnItems.isArmed()) {
            Choice1 = "Item that Doesn't exist yet"; // ArrayList.get(i).getName() or something, items should be an arraylist of objects
            Choice2 = "Item that also doesn't exist yet";
            Choice3 = "Another item that doesn't exist yet";  //gets items directly from the inventory
            Choice4 = "I can't believe this item doesn't exist yet";
            btnItems.setDisable(true);
            btnAttack.setDisable(false);
            btnOther.setDisable(false);
        } else if (btnOther.isArmed()) {
            Choice1 = "Flee";
            Choice2 = "Bribe";  //These are the only options that will always remain the same
            Choice3 = "Taunt";
            Choice4 = "Talk";
            btnOther.setDisable(true);
            btnAttack.setDisable(false);
            btnItems.setDisable(false);
        }
        btnChoice1.setText(Choice1);
        btnChoice2.setText(Choice2);
        btnChoice3.setText(Choice3);
        btnChoice4.setText(Choice4);
        btnChoice1.setDisable(false);
       if(MCMana > weapon.getCost()){
        btnChoice2.setDisable(false); //makes it so that the secondary buttons can be pressed
        btnChoice3.setDisable(false);}
       if(MCMana > weapon.getCost() + (2 * 1) ){ // ********chance 1 to getLevel()***********
        btnChoice4.setDisable(false);}
    }

    @FXML
    private void Secondary(ActionEvent event) {
        if (btnChoice1.isArmed()) {
            if (btnChoice1.getText().equals(weapon.getAttack1())) { //checks for the attack name to determine if it is an attack
                EnemyHealth = EnemyHealth - weapon.getDamage();
            }
            if (btnChoice1.getText().equals("")) {
            }
            if (btnChoice1.getText().equals("Flee")) { //Allows the player a chance to flee like a coward, no rewards gained
                if (ThreadLocalRandom.current().nextInt(1, 4 + 1) == 1) {
                    endCombat();
                    // multiply exp gained by 0 here
                }
            }
        } else if (btnChoice2.isArmed()) {
            if (btnChoice2.getText().equals(weapon.getAttack2())) {
                EnemyHealth = EnemyHealth - (weapon.getDamage() + weapon.getSDamage());
                MCMana = MCMana - weapon.getCost();
            }
            if (btnChoice1.getText().equals("")) {
            }
            if (btnChoice2.getText().equals("Bribe")) { // Allows the player to buy their way out of a fight, 
                endCombat();
                //half the exp of exp gained here
            }
        } else if (btnChoice3.isArmed()) {
            if (btnChoice3.getText().equals(weapon.getAttack3())) {
                EnemyHealth = EnemyHealth - weapon.getSDamage();
                MCMana = MCMana - weapon.getCost();
            }
            if (btnChoice3.getText().equals("Taunt")) { // a humourous quip that raises the damage of the enemy but reduces defence
                //mulitply their damage by like 1.2
                //reduce defence by 1.3 or whatever
            }
            if (btnChoice3.getText().equals("")) {
            }
        } else if (btnChoice4.isArmed()) {
            if (btnChoice4.getText().equals(weapon.getAttack4())) {
                MCMana = MCMana - (weapon.getCost() + (2 * 1)); //************Turn the 1 int getLevel() ****************
            }
            if (btnChoice4.getText().equals("")) {
            }
            if (btnChoice4.getText().equals("Talk")) { //Talk about the weather, maybe be used for story

            }
        }
        lblEnemy.setText(EnemyHealth + " / " + EnemyHealthMAX); //updates the label as to what the health is
        prgEnemy.setProgress((EnemyHealth / EnemyHealthMAX)); //updates the progress bar as to what the health is
        prgMCMana.setProgress(MCMana / MCManaMAX);
        lblMCMana.setText(MCMana + " / " + MCManaMAX);
        btnOther.setDisable(true);
        btnItems.setDisable(true);
        btnAttack.setDisable(true);
        btnChoice1.setDisable(true); // disables all the stuff
        btnChoice2.setDisable(true);
        btnChoice3.setDisable(true);
        btnChoice4.setDisable(true);
        btnChoice1.setText("---");
        btnChoice2.setText("---");
        btnChoice3.setText("---");
        btnChoice4.setText("---");
        checkHP(prgEnemy); //checks health of enemy to see if it is above 0
        if (combat) { // if health of enemy is 0, combat is set to false, so the enemy turn does not start
            endturn.play(); // a timer puts a delay, slowing down combat.
        }
    }

    private void enemyTurn() {
        MCHealth = MCHealth - ThreadLocalRandom.current().nextInt(3, 5 + 1);
        prgMC.setProgress((MCHealth / MCHealthMAX));
        lblMC.setText(MCHealth + " / " + MCHealthMAX); // sets the health of the MC
        prgEnemyMana.setProgress(EnemyMana / EnemyManaMAX);
        lblEnemyMana.setText(EnemyMana + " / " + EnemyManaMAX);
        checkHP(prgMC); //checks health for MC
        if (combat) {
            start(); //the start of the players turn
        }

    }

    private void start() {
        btnAttack.setDisable(false);
        btnItems.setDisable(false);
        btnOther.setDisable(false); // Just sets the buttons up for the start of a turn
    }

    private void endCombat() {
        // starts loot/exp, then dialogue, which will be in main scene, maybe?. 
        combat = false; // makes it so that the next turn does not start
        panEXP.setVisible(true); //makes the end combat screen visible
    }

    private void checkHP(ProgressBar prgT) {
        if (prgT.getProgress() <= 0) {
            prgT.setProgress(0); //makes it so that the progress bar does not enter "INDETERMINATE" mode, looks poopoo
            endCombat(); //ends combat and opens up end of combat screen
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //   music.setCycleCount(Timeline.INDEFINITE);
        //   music.play();
        MCHealth = 50; //gets the health of the player
        MCHealthMAX = 100; //gets the max health of the player
        EnemyHealth = 50; //gets the health of the enemy
        EnemyHealthMAX = 75; //gets the max health of the enemy
        MCMana = 30;
        MCManaMAX = 30;
        EnemyMana = 20;
        EnemyManaMAX = 40;
        prgMC.setProgress(MCHealth / MCHealthMAX); //sets the health of the character
        prgEnemy.setProgress(EnemyHealth / EnemyHealthMAX); //sets the health of the enemy
        prgMCMana.setProgress(MCMana / MCManaMAX);
        prgEnemyMana.setProgress(EnemyMana / EnemyManaMAX);
        lblMC.setText(MCHealth + " / " + MCHealthMAX);
        lblEnemy.setText(EnemyHealth + " / " + EnemyHealthMAX);
        lblMCMana.setText(MCMana + " / " + MCManaMAX);
        lblEnemyMana.setText(EnemyMana + " / " + EnemyManaMAX);
        btnChoice1.setText("---");
        btnChoice2.setText("---");
        btnChoice3.setText("---");
        btnChoice4.setText("---");
        btnChoice1.setDisable(true);
        btnChoice2.setDisable(true);
        btnChoice3.setDisable(true);
        btnChoice4.setDisable(true);
        panEXP.setVisible(false); //Makes it so that when you re-enter comabt the end combat screen isn't already there
        //imgMC.setImage(); //set image from whatever the image is
        //imgEnemy.setImage(); //gets the images from both the player and enemy

    }

}
