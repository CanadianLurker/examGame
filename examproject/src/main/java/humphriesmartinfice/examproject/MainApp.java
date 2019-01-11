package humphriesmartinfice.examproject;

import humphriesmartinfice.examproject.MainApp;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static int level, STR, DEX, INT, edot;
    
    public static int ecount = 0;
    
    public static boolean dot = false;

    public static double health, healthMAX, mana, manaMAX, EXP, EXPNeeded;

    public static ArrayList<Enemy> enemies = new ArrayList();

    public static Weapon weapon = new Mage(50, "", "", "", "", 0, 0, 0, 0, "", "");

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("Prison Escape");
        stage.setScene(scene);
        stage.show();
        scene.getRoot().requestFocus();
        stage.setOnCloseRequest(e -> System.exit(0));
    }

    public static int getSTR() {
        return MainApp.STR;
    }

    public static void setSTR(int point) {
        MainApp.STR = point;
    }

    public static int getDEX() {
        return MainApp.DEX;
    }
    
    public static void setEcount(int E){
    ecount = E;
    }
    
    public static int getEcount(){
    return ecount;
    }
    
    public static void setEdot(int E){
    edot = E;
    }
    
    public static int getEdot(){
    return edot;
    }

    public static void setDEX(int point) {
        MainApp.DEX = point;
    }

    public static int getINT() {
        return MainApp.INT;
    }

    public static void setINT(int point) {
        MainApp.INT = point;
    }

    public static void setLevel(int level) {
        MainApp.level = level;
    }

    public static int getLevel() {
        return MainApp.level;
    }

    public static void setHealth(double health) {
        MainApp.health = health;
    }

    public static double getHealth() {
        return MainApp.health;
    }

    public static void setHealthMAX() {
        MainApp.healthMAX = MainApp.level * 10 + (MainApp.STR * 5) + (MainApp.DEX * 2);
    }

    public static double getHealthMAX() {
        return MainApp.healthMAX;
    }

    public static double getMana() {
        return MainApp.mana;
    }

    public static void setMana(double mana) {
        MainApp.mana = mana;
    }

    public static double getManaMAX() {
        return MainApp.manaMAX;
    }

    public static void setManaMAX() {
        MainApp.manaMAX = MainApp.level * 3 + 4 + (MainApp.INT * 2) + MainApp.DEX;
    }

    public static double getEXP() {
        return MainApp.EXP;
    }

    public static void setEXP(double exp) {
        MainApp.EXP = exp;
    }

    public static double getEXPNeeded() {
        return MainApp.EXPNeeded;
    }

    public static void setEXPNeeded() {
        MainApp.EXPNeeded = 22 + (MainApp.level * 12);
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
