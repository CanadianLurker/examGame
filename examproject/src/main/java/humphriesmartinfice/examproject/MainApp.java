package humphriesmartinfice.examproject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    public static int level, health, healthMAX;
    
    private static Object Inventory[] = new Object[2];
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLStart.fxml"));    
        Scene scene = new Scene(root);    
        stage.setTitle("This Game still needs to be named");
        stage.setScene(scene);
        stage.show();
    }
    
    public void setLevel(int level){
    this.level = level;
    }
    
    public int getLevel(){
    return this.level;
    }
    
    public void setHealth(int health){
    this.health = health;
    }
    
    public int getHealth(){
    return this.health;
    }
    
    public void setHealthMAX(int health){
    this.healthMAX = health;
    }
    
    public int getHealthMAX(){
    return this.healthMAX;
    }
    
    public Array getInv(int spot){
        return (Array) Inventory[spot];
    }
    
    public void setInv(Object thing, int spot){
    Inventory[spot] = thing;
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
