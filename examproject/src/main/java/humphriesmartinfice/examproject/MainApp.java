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

    public static int level;
    
    public static double health, healthMAX, mana, manaMAX,EXP, EXPNeeded;
    
    private static Weapon[][] Inventory = new Weapon[2][1];
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));    
        Scene scene = new Scene(root);    
        stage.setTitle("This Game still needs to be named");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void setLevel(int level){
    MainApp.level = level;
    }
    
    public static int getLevel(){
    return MainApp.level;
    }
    
    public static void setHealth(double health){
    MainApp.health = health;
    }
    
    public static double getHealth(){
    return MainApp.health;
    }
    
    public static void setHealthMAX(){
    MainApp.healthMAX = MainApp.level *10 + 10;
    }
    
    public static double getHealthMAX(){
    return MainApp.healthMAX;
    }
    
    public static double getMana(){
    return MainApp.mana;
    }
    
    public static void setMana(double mana){
    MainApp.mana = mana;
    }
    
    public static double getManaMAX(){
    return MainApp.manaMAX;
    }
    
    public static void setManaMAX(){
    MainApp.manaMAX = MainApp.level * 5 + 5;
    }
    
    public static double getEXP(){
    return MainApp.EXP;
    }
    
    public static void setEXP(double exp){
    MainApp.EXP = exp;
    }
    
    public static double getEXPNeeded(){
    return MainApp.EXPNeeded;
    }
    
    public static void setEXPNeeded(){
     MainApp.EXPNeeded = 22 + (MainApp.level * 12) ;
    }
    
    public static Weapon getInv(int spot){
        return Inventory[spot][0];
    }
    
    public static void setInv(Weapon thing, int spot){
    Inventory[spot][0] = thing;
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
