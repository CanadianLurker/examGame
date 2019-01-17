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
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class FXMLCombatController implements Initializable {

    @FXML
    private ProgressBar prgMC, prgEnemy, prgMCMana, prgEnemyMana, prgEXP;
    @FXML
    private ImageView imgMC, imgEnemy;
    @FXML
    private Button btnAttack, btnItems, btnOther, btnChoice1, btnChoice2, btnChoice3, btnChoice4, btnSTR, btnDEX, btnINT, btnLog;
    @FXML
    private Pane panEXP;
    @FXML
    private Label lblMC, lblEnemy, lblMCMana, lblEnemyMana, lblHP, lblLevel, lblMana, lblPoints, lblSTR, lblDEX, lblINT, lblCigs;
    @FXML
    private ListView listLog;

    private String Choice1, Choice2, Choice3, Choice4, Primary;

    private int count, points, buffcount, cigs;

    private double exp;

    private boolean combat = true;//combat will always be active when coming into this scene
    private boolean DOT = false;
    public boolean EDOT = false;

    Timeline endturn = new Timeline(new KeyFrame(Duration.millis(1500), ae -> enemyTurn()));
    Timeline endscreen = new Timeline(new KeyFrame(Duration.millis(75), ae -> exp()));
    Timeline Buffer = new Timeline(new KeyFrame(Duration.millis(1750), ae -> buffer()));

    // MediaPlayer music = new MediaPlayer((new Media(getClass().getResource("/Background.mp3").toString())));
    @FXML
    private void Primary(ActionEvent event) {
        if (btnAttack.isArmed()) { //checks to see which primary button is pressed
            double reglow = (weapon.getDamage() - (2) + weapon.getExtra() + weapon.getExtraWar() - enemies.get(0).getDefence());
            double reghigh = ((weapon.getDamage() + (getLevel() + weapon.getLevel())) + weapon.getExtra() + weapon.getExtraWar() - enemies.get(0).getDefence());
            double spec = (weapon.getSDamage() - 2 + weapon.getExtra() + weapon.getExtraWar() - enemies.get(0).getDefence());
            double spechigh = ((weapon.getSDamage() + (getLevel() + weapon.getLevel())) + weapon.getExtra() + weapon.getExtraWar() - enemies.get(0).getDefence());
            //gets damage numbers from weapon to display with attack
            if (reglow < 0) { //if any number is negative, it will set it to 0
                reglow = 0;
            }
            if (reghigh < 0) {
                reghigh = 0;
            }
            if (spec < 0) {
                spec = 0;
            }
            if (spechigh < 0) {
                spechigh = 0;
            }
            Choice1 = "0 Mana -" + weapon.getAttack1() + "-" + reglow + "-" + reghigh + " Damage";
            Choice2 = weapon.getCost() + " Mana -" + weapon.getAttack2() + "-" + (reglow + spec) + "-"
                    + (reghigh + spechigh) + " Damage";
            Choice3 = weapon.getCost() + " Mana -" + weapon.getAttack3() + "-" + spec + "-" + spechigh + " Damage";
            Choice4 = Double.parseDouble(F.format(weapon.getCost() + (1.2 * weapon.getLevel()))) + " Mana -" + weapon.getAttack4();
            //sets what will be displayed in choices
            Primary = "Attack";
            btnAttack.setDisable(true);
            btnItems.setDisable(false); //disables the button that was pressed
            btnOther.setDisable(false);
        } else if (btnItems.isArmed()) {
            Choice1 = "Item that Doesn't exist yet"; // ArrayList.get(i).getName() or something, items should be an arraylist of objects
            Choice2 = "Item that also doesn't exist yet";
            Choice3 = "Another item that doesn't exist yet";  //gets items directly from the inventory
            Choice4 = "I can't believe this item doesn't exist yet";
            //gets items from inventory
            Primary = "Items";
            btnItems.setDisable(true);
            btnAttack.setDisable(false);
            btnOther.setDisable(false);
        } else if (btnOther.isArmed()) {
            Choice1 = "Flee";
            Choice2 = "Bribe";  //These are the only options that will always remain the same
            Choice3 = "Taunt";
            Choice4 = "Talk";
            Primary = "Other";
            btnOther.setDisable(true);
            btnAttack.setDisable(false);
            btnItems.setDisable(false);
        }
        btnChoice1.setText(Choice1);
        btnChoice2.setText(Choice2);
        btnChoice3.setText(Choice3);
        btnChoice4.setText(Choice4);
        //changes the text from --- to whatever the mana cost, weapon attack name and damage is
        btnChoice1.setDisable(false);
        if (getMana() >= weapon.getCost() && Primary.equals("Attack")) { //only enables buttons if the player has enough mana for it
            btnChoice2.setDisable(false);
            btnChoice3.setDisable(false);
        }
        if (getMana() >= weapon.getCost() + (1.2 * getLevel()) && Primary.equals("Attack")) { //same as the previous, but with more mana cost
            btnChoice4.setDisable(false);
        }
        if (Primary.equals("Items") || Primary.equals("Other")) {
            btnChoice2.setDisable(false);
            btnChoice3.setDisable(false);
            btnChoice4.setDisable(false);
        }
    }

    @FXML
    private void Secondary(ActionEvent event) {
        double damage = 0;
        if (btnChoice1.isArmed()) {
            if (Primary.equals("Attack")) { //checks to see if the choice buttons show attacks
                damage = weapon.Attack1(enemies.get(0).getDefence()); // basic attack, no mana cost
            }
            if (btnChoice1.getText().equals("")) {
            }
            if (btnChoice1.getText().equals("Flee")) { //Allows the player a chance to flee like a coward, no rewards gained
                if (ThreadLocalRandom.current().nextInt(1, 5 + 1) == 1) {
                    exp = 0;
                    endCombat();
                    // multiply exp gained by 0 here
                }
            }
        } else if (btnChoice2.isArmed()) {
            if (Primary.equals("Attack")) {
                damage = weapon.Attack2(enemies.get(0).getDefence()); //higher damage attack, costs mana
                setMana(getMana() - weapon.getCost());
            }
            if (btnChoice1.getText().equals("")) {
            }
            if (btnChoice2.getText().equals("Bribe")) { // Allows the player to buy their way out of a fight, 
                exp = exp / 2;
                endCombat();
                //half the exp of exp gained here
            }
        } else if (btnChoice3.isArmed()) {
            if (Primary.equals("Attack")) {
                damage = weapon.Attack3(enemies.get(0).getDefence()); //dot attack, costs mana
                DOT = true;
                count = count + 3;
                // add image, maybe something that just says "DOT"
                setMana(getMana() - weapon.getCost());
            }
            if (btnChoice3.getText().equals("Taunt")) { // a humourous quip that raises the damage of the enemy but reduces defence
                //mulitply their damage by like 1.2
                //reduce defence by 1.3 or whatever
            }
            if (btnChoice3.getText().equals("")) {
            }
        } else if (btnChoice4.isArmed()) {
            if (Primary.equals("Attack")) {
                setMana(getMana() - (weapon.getCost() + (1.2 * getLevel())));
                if (weapon.getType().equals("Mage")) { //checks to see which weapon type is being used
                    weapon.MageAttack(); //heal
                    listLog.getItems().add(("Player heals for " + (weapon.getDamage() + (weapon.getLevel() / 1.3)) + getINT()) + "health");
                }
                if (weapon.getType().equals("Rogue")) {
                    weapon.RogueAttack(enemies.get(0)); //lowers enemy defence
                }
                if (weapon.getType().equals("Warrior")) {
                    weapon.WarriorAttack(); //raises damage
                    buffcount += 4;
                }

            }
            if (btnChoice4.getText().equals("")) {
            }
            if (btnChoice4.getText().equals("Talk")) { //Talk about the weather, maybe be used for story

            }
        }
        if (getHealth() > getHealthMAX()) { //makes it so that the player can not overheal
            setHealth(getHealthMAX());
        }
        if (damage < 0) {
            damage = 0;
        }
        enemies.get(0).setHealth(enemies.get(0).getHealth() - damage); //the damaging process
        listLog.getItems().add(damage + " damage dealt to enemy!");
        listLog.scrollTo(listLog.getItems().size());
        progress(); //shows all the values to the player through progress bars and labels
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
        Primary = "";
        checkHP(prgEnemy); //checks health of enemy to see if it is above 0
        if (combat) { // if health of enemy is 0, combat is set to false, so the enemy turn does not start
            endturn.play(); // a timer puts a delay, slowing down combat.
        }
    }

    private void enemyTurn() {
        if (DOT) { //checks to see if enemy has a dot on them
            double dotd = weapon.Attack3(enemies.get(0).getDefence());
            enemies.get(0).setHealth(enemies.get(0).getHealth() - dotd);
            listLog.getItems().add(dotd + " damage dealt to enemy by dot!");
            count--;
            buffcount--;
            checkHP(prgEnemy); //check to make sure they don't attack while dead
            if (count == 0) { //removes dot if it is over
                DOT = false;
            }
            if (buffcount == 0) { //removes Warrior special attack buff if it runs out
                weapon.ResetWar();
            }
        }
        if (combat) {
            double d = enemies.get(0).Attack();
            setHealth(getHealth() - d); //the enemy strikes
            listLog.getItems().add(d + " damage dealt to player!");
            if (getEcount() == 0) {
                listLog.getItems().add("Enemy healed for " + (enemies.get(0).getHealth() + (enemies.get(0).getHealthMAX() / 3)) + " health.");
            }
            if (getEcount() > 0) {
                setHealth(getHealth() - getEdot());
                listLog.getItems().add(getEdot() + " damage dealt to player by dot!");
                setEcount(getEcount() - 1);
                if (getEcount() == 0) {
                    dot = false;
                }
            }
            progress(); //sets things for the player to see
            check(prgMC); //checks health for MC
            if (combat) {
                start(); //the start of the players turn, if it is still ongoing
            }
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
        prgEXP.setProgress(getEXP() / getEXPNeeded());
        panEXP.setVisible(true); //makes the end combat screen visible
        endscreen.setCycleCount(Timeline.INDEFINITE); //allows the timer to run more than once
        endscreen.play();
        lblLevel.setText("LEVEL: " + getLevel()); //sets the Level in big letters at the top
        lblHP.setText("MAX HP: " + getHealthMAX()); //reminds the player of their characters health
        lblMana.setText("MAX MANA: " + getManaMAX()); //reminds the player of their characters mana
        count = 0; //makes it so the next enemy fought doesn't have a dot for a random reason
        buffcount = 0;
        weapon.ResetWar(); //resets buff
        DOT = false;
        setCigs(getCigs() + cigs);
        lblCigs.setText("Cigs: " + getCigs());
        cleanLog();
        lblSTR.setText("STR: " + getSTR());
        lblDEX.setText("DEX: " + getDEX()); //reminds the player of their characters attributes
        lblINT.setText("INT: " + getINT());
        lblPoints.setText("" + points); // this should always be zero in any practical sense
    }

    private void checkHP(ProgressBar prgT) { //checks enemy health
        if (enemies.get(0).getHealth() <= 0) {
            listLog.getItems().add("Enemy defeated! " + enemies.get(0).getEXP() + " EXP gained!");
            listLog.getItems().add(enemies.get(0).getECigs() + " cigs attained");
            prgT.setProgress(0); //makes it so that the progress bar does not enter "INDETERMINATE" mode, which does not look good
            enemies.get(0).setHealth(0);
            if (getMana() != getManaMAX()) {
                setMana(getMana() + (enemies.get(0).getLevel() * 3));
                if (getMana() >= getManaMAX()) {
                    setMana(getManaMAX());
                }
            }
            progress();
            exp += enemies.get(0).getEXP();
            cigs += enemies.get(0).getECigs();
            enemies.remove(0);
            if (enemies.isEmpty()) {
                endCombat(); //ends combat and opens up end of combat screen
            } else {
                Buffer.play();
                DOT = false;
                count = 0;
            }
        }
    }

    private void cleanLog() {
        if (listLog.getItems().size() == 4) {
            listLog.getItems().clear();
        }
    }

    private void check(ProgressBar prgT) {
        if (prgT.getProgress() <= 0) {
            prgT.setProgress(0); //makes it so that the progress bar does not enter "INDETERMINATE" mode, looks awful
            setHealth(0);
            progress();
            exp = 0;
            cigs = 0;
            endCombat();
            //end game screen here
        }
    }

    private void progress() {
        prgMC.setProgress(getHealth() / getHealthMAX()); //sets the health of the whatever is being inputted with the method
        prgMCMana.setProgress(getMana() / getManaMAX()); //sets the mana of whatever is being inputted
        lblMC.setText(Double.parseDouble(F.format(getHealth())) + " / " + getHealthMAX()); //types into a label to for more visual sight as to what health is
        lblMCMana.setText(Double.parseDouble(F.format(getMana())) + " / " + getManaMAX()); // same as health but for mana
        prgEnemy.setProgress(enemies.get(0).getHealth() / enemies.get(0).getHealthMAX()); //sets the health of the whatever is being inputted with the method
        prgEnemyMana.setProgress(enemies.get(0).getMana() / enemies.get(0).getManaMAX()); //sets the mana of whatever is being inputted
        lblEnemy.setText(Double.parseDouble(F.format(enemies.get(0).getHealth())) + " / " + enemies.get(0).getHealthMAX()); //types into a label to for more visual sight as to what health is
        lblEnemyMana.setText(Double.parseDouble(F.format(enemies.get(0).getMana())) + " / " + enemies.get(0).getManaMAX());
    }

    @FXML
    private void OK(ActionEvent event) throws IOException {
        if (points == 0 && exp == 0) {
            Scene scene = new Scene(getArea());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.setTitle("Prison Escape");
            stage.setResizable(false);
            stage.show();
            scene.getRoot().requestFocus();
//link back to where the fight started in the "explore" state
        }
        if (exp != 0) { //if any exp is still being gathered, the exp bar will be filled up to whatever it should go to.
            endscreen.stop();
            setEXP(getEXP() + exp);
            exp = 0;
            while (getEXP() / getEXPNeeded() >= 1) {
                double leftover = getEXP() - getEXPNeeded();
                if (leftover < 0) {
                    leftover = 0;
                }
                setEXP(leftover);
                LevelUp();
            }
            prgEXP.setProgress(getEXP() / getEXPNeeded());
        }

    }

    @FXML
    private void PointUp(ActionEvent event) {
        if (points > 0) {
            points--; //makes it so that every point can only be used once
            if (btnSTR.isArmed()) {
                setSTR(getSTR() + 1);
            }
            if (btnDEX.isArmed()) {
                setDEX(getDEX() + 1); //adds a point to the attribute of the players choosing
            }
            if (btnINT.isArmed()) {
                setINT(getINT() + 1);
            }
        }
        setHealthMAX(); //in case of DEX or STR being added onto, this adds on the right amont of health
        setHealth(getHealthMAX());
        lblHP.setText("MAX HP: " + getHealthMAX());
        setManaMAX(); //what was said for health, but now for mana
        setMana(getManaMAX());
        lblMana.setText("MAX MANA: " + getManaMAX());
        prgMC.setProgress(getHealth() / getHealthMAX()); //sets the health of the whatever is being inputted with the method
        prgMCMana.setProgress(getMana() / getManaMAX()); //sets the mana of whatever is being inputted
        lblMC.setText(Double.parseDouble(F.format(getHealth())) + " / " + getHealthMAX()); //types into a label to for more visual sight as to what health is
        lblPoints.setText("" + points);
        lblSTR.setText("STR: " + getSTR());
        lblDEX.setText("DEX: " + getDEX());
        lblINT.setText("INT: " + getINT());
    }

    private void LevelUp() { //what happens when the big blue bar at the end of combat fills up
        setLevel(getLevel() + 1);
        lblLevel.setText("LEVEL: " + getLevel());
        setHealthMAX();
        setHealth(getHealthMAX());
        lblHP.setText("MAX HP: " + getHealthMAX());
        setManaMAX();
        setMana(getManaMAX());
        lblMana.setText("MAX MANA: " + getManaMAX());
        prgMC.setProgress(getHealth() / getHealthMAX()); //sets the health of the whatever is being inputted with the method
        prgMCMana.setProgress(getMana() / getManaMAX()); //sets the mana of whatever is being inputted
        lblMC.setText(Double.parseDouble(F.format(getHealth())) + " / " + getHealthMAX()); //types into a label to for more visual sight as to what health is
        lblMCMana.setText(Double.parseDouble(F.format(getMana())) + " / " + getManaMAX()); // same as health but for mana
        points++;
        lblPoints.setText("" + points);
    }

    private void exp() { //the method in the exp gathering timer
        exp--;
        setEXP(getEXP() + 1);
        if (getEXP() >= getEXPNeeded()) {
            setEXP(0);
            LevelUp();
        }
        prgEXP.setProgress(getEXP() / getEXPNeeded());
        if (exp <= 0) {
            endscreen.stop();
        }
    }

    private void buffer() { //quite literally a buffer, so that the enemy seems to have a "thinking phase"
        progress();
    }

    @FXML
    private void Log(ActionEvent E) {
        if (listLog.isVisible() == false) {
            listLog.setVisible(true);
        } else if (listLog.isVisible()) {
            listLog.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //   music.setCycleCount(Timeline.INDEFINITE);
        //   music.play();
        progress();
        btnChoice1.setText("---");
        btnChoice2.setText("---");
        btnChoice3.setText("---");
        btnChoice4.setText("---");
        btnChoice1.setDisable(true);
        btnChoice2.setDisable(true);
        btnChoice3.setDisable(true);
        btnChoice4.setDisable(true);
        count = 0;
        buffcount = 0;
        panEXP.setVisible(false);//Makes it so that when you re-enter comabt the end combat screen isn't already there
        //imgMC.setImage(); //set image from whatever the image is

    }

}