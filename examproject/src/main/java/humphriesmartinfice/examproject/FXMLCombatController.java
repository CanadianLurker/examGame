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
import java.util.ArrayList;
import java.text.DecimalFormat;

public class FXMLCombatController implements Initializable {
    
    @FXML
    private ProgressBar prgMC, prgEnemy, prgMCMana, prgEnemyMana, prgEXP;
    @FXML
    private ImageView imgMC, imgEnemy;
    @FXML
    private Button btnAttack, btnItems, btnOther, btnChoice1, btnChoice2, btnChoice3, btnChoice4, btnSTR, btnDEX, btnINT;
    @FXML
    private Pane panEXP;
    @FXML
    private Label lblMC, lblEnemy, lblMCMana, lblEnemyMana, lblHP, lblLevel, lblMana, lblPoints, lblSTR, lblDEX, lblINT;
    
    private String Choice1, Choice2, Choice3, Choice4, Primary;
    
    ArrayList<Enemy> enemies = new ArrayList();
    
    private int count, points, buffcount;
    
    private double exp;
    
    private boolean combat = true;//combat will always be active when coming into this scene
    private boolean DOT = false;
    
    DecimalFormat F = new DecimalFormat("0.00"); //format so decimals don't go on for ever

    Timeline endturn = new Timeline(new KeyFrame(Duration.millis(1500), ae -> enemyTurn()));
    Timeline endscreen = new Timeline(new KeyFrame(Duration.millis(75), ae -> exp()));
    Timeline Buffer = new Timeline(new KeyFrame(Duration.millis(1750), ae -> buffer()));
    
    Mage weapon = new Mage(1, "", "", "", "", 0, 0, 0, 0, "", "");

    // MediaPlayer music = new MediaPlayer((new Media(getClass().getResource("/Background.mp3").toString())));
    @FXML
    private void Primary(ActionEvent event) {
        if (btnAttack.isArmed()) { //checks to see which primary button is pressed
            int reglow = (weapon.getDamage() - (2) + weapon.getExtra() - enemies.get(0).getDefence());
            int reghigh = ((weapon.getDamage() + (getLevel() + weapon.getLevel())) + weapon.getExtra() - enemies.get(0).getDefence());
            int spec = (weapon.getSDamage() - 2 + weapon.getExtra() - enemies.get(0).getDefence());
            int spechigh = ((weapon.getSDamage() + (getLevel() + weapon.getLevel())) + weapon.getExtra() - enemies.get(0).getDefence());     
            setPos(reglow);
            setPos(reghigh);
            setPos(spec);
            setPos(spechigh);
            Choice1 = "0 Mana -" + weapon.getAttack1() + "-" + reglow + "-" + reghigh + " Damage";
            Choice2 = weapon.getCost() + " Mana -" + weapon.getAttack2() + "-" + (reglow + spec + enemies.get(0).getDefence()) + "-"
                    + (reghigh + spechigh + enemies.get(0).getDefence()) + " Damage";
            Choice3 = weapon.getCost() + " Mana -" + weapon.getAttack3() + "-" + spec + "-"+ spechigh + " Damage";
            Choice4 = Double.parseDouble(F.format(weapon.getCost() + (1.2 * weapon.getLevel()))) + " Mana -" + weapon.getAttack4();
            Primary = "Attack";
            btnAttack.setDisable(true);
            btnItems.setDisable(false); //disables the button that was pressed
            btnOther.setDisable(false);
        } else if (btnItems.isArmed()) {
            Choice1 = "Item that Doesn't exist yet"; // ArrayList.get(i).getName() or something, items should be an arraylist of objects
            Choice2 = "Item that also doesn't exist yet";
            Choice3 = "Another item that doesn't exist yet";  //gets items directly from the inventory
            Choice4 = "I can't believe this item doesn't exist yet";
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
        btnChoice1.setDisable(false);
        if (getMana() >= weapon.getCost() && Primary.equals("Attack")) {
            btnChoice2.setDisable(false); //makes it so that the secondary buttons can be pressed
            btnChoice3.setDisable(false);
        }
        if (getMana() >= weapon.getCost() + (1.2 * getLevel()) && Primary.equals("Attack")) {
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
        int damage = 0;
        if (btnChoice1.isArmed()) {
            if (Primary.equals("Attack")) { //checks for the attack name to determine if it is an attack
                damage = weapon.Attack1(enemies.get(0).getDefence());
            }
            if (btnChoice1.getText().equals("")) {
            }
            if (btnChoice1.getText().equals("Flee")) { //Allows the player a chance to flee like a coward, no rewards gained
                if (ThreadLocalRandom.current().nextInt(1, 4 + 1) == 1) {
                    exp = 0;
                    endCombat();
                    // multiply exp gained by 0 here
                }
            }
        } else if (btnChoice2.isArmed()) {
            if (Primary.equals("Attack")) {
                damage = weapon.Attack2(enemies.get(0).getDefence());
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
                damage = weapon.Attack3(enemies.get(0).getDefence());
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
                    weapon.MageAttack();
                }
                if (weapon.getType().equals("Rogue")) {
                    weapon.RogueAttack(enemies.get(0));
                }
                if (weapon.getType().equals("Warrior")) {
                    weapon.WarriorAttack();
                    buffcount += 4;
                }
                
            }
            if (btnChoice4.getText().equals("")) {
            }
            if (btnChoice4.getText().equals("Talk")) { //Talk about the weather, maybe be used for story

            }
        }
        if (getHealth() > getHealthMAX()) {
            setHealth(getHealthMAX());
        }
        if (damage <= 0) {
            damage = 0;
        }
        enemies.get(0).setHealth(enemies.get(0).getHealth() - damage);
        progress();
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
    
    public void setPos(int p){
    if(p<0){
     p= 0;
    }  
    }
    
    private void enemyTurn() {
        if (DOT) {
            enemies.get(0).setHealth(enemies.get(0).getHealth() - weapon.Attack3(enemies.get(0).getDefence()));
            count--;
            buffcount--;
            checkHP(prgEnemy);
            if (count == 0) {
                DOT = false;
            }
            if(buffcount == 0){
            weapon.ResetWar();
            }
        }
        if (combat) {
            setHealth(getHealth() - enemies.get(0).Attack());
            progress();
            check(prgMC); //checks health for MC
            if (combat) {
                start(); //the start of the players turn
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
        endscreen.setCycleCount(Timeline.INDEFINITE);
        endscreen.play();
        lblLevel.setText("LEVEL: " + getLevel());
        lblHP.setText("MAX HP: " + getHealthMAX());
        lblMana.setText("MAX MANA: " + getManaMAX());
        count = 0;
        buffcount = 0;
        weapon.ResetWar();
        DOT = false;
        lblSTR.setText("STR: " + getSTR());
        lblDEX.setText("DEX: " + getDEX());
        lblINT.setText("INT: " + getINT());
        lblPoints.setText("" + points);
    }
    
    private void checkHP(ProgressBar prgT) {
        if (enemies.get(0).getHealth() <= 0) {
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
    
    private void check(ProgressBar prgT) {
        if (prgT.getProgress() <= 0) {
            prgT.setProgress(0); //makes it so that the progress bar does not enter "INDETERMINATE" mode, looks poopoo
            setHealth(0);
            progress();
            exp = 0;
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
    private void OK(ActionEvent event) {
        if (exp != 0) {
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
        if (points == 0) {
//link back to where the fight started in the "explore" state
        }
    }
    
    @FXML
    private void PointUp(ActionEvent event) {
        if (points > 0) {
            points--;
            if (btnSTR.isArmed()) {
                setSTR(getSTR() + 1);
            }
            if (btnDEX.isArmed()) {
                setDEX(getDEX() + 1);
            }
            if (btnINT.isArmed()) {
                setINT(getINT() + 1);
            }
        }
        setHealthMAX();
        setHealth(getHealthMAX());
        lblHP.setText("MAX HP: " + getHealthMAX());
        setManaMAX();
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
    
    private void LevelUp() {
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
    
    private void exp() {
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
    
    private void buffer() {
        progress();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //   music.setCycleCount(Timeline.INDEFINITE);
        //   music.play();
        //Only used for testing purposes//
        setINT(0);
        setSTR(0);
        setDEX(0);
        setLevel(1);
        setHealthMAX();
        setHealth(getHealthMAX());
        setManaMAX();
        setMana(getManaMAX());
        setEXP(0);
        setEXPNeeded();
        enemies.add(new Enemy(1));
        //enemies.add(new Enemy(1));
        //enemies.add(new Enemy(1));
        //Only used for testing purposes//    
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
        //imgEnemy.setImage(); //gets the images from the enemy

    }
    
}