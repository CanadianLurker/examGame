package humphriesmartinfice.examproject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static int level, STR, DEX, INT,  cigs;

    public static int ecount = 0;

    public static boolean dot = false;

    public static double health, healthMAX, mana, manaMAX, EXP, EXPNeeded, layX, layY, edot;

    public static Parent area;

    public static ArrayList<Enemy> enemies = new ArrayList();
    
    public static DecimalFormat F = new DecimalFormat("0"); //format so decimals don't go on for ever

    public static Weapon weapon = new Rogue(30, "", "", "", "", 0, 0, 0, 0, "", "");

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("Prison Escape");
        stage.setScene(scene);
        stage.show();
        scene.getRoot().requestFocus();
        stage.setOnCloseRequest(e -> System.exit(0));
    }

    public static void saveLoc(Parent a, double x, double y){
        area = a;
        layX = x;
        layY = y;
    }
    
    public static double getLocX(){
    return layX; 
    }
    
    public static double getLocY(){
    return layY;
    }
    
    public static Parent getArea(){
    return area;
    }

    public static int getSTR() {
        return MainApp.STR;
    }

    public static int getCigs() {
        return cigs;
    }

    public static void setCigs(int c) {
        cigs = c;
    }

    public static void setSTR(int point) {
        MainApp.STR = point;
    }

    public static int getDEX() {
        return MainApp.DEX;
    }

    public static void setEcount(int E) {
        ecount = E;
    }

    public static int getEcount() {
        return ecount;
    }

    public static void setEdot(double E) {
        edot = E;
    }

    public static double getEdot() {
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
        MainApp.manaMAX = MainApp.level * 3 + 4 + (MainApp.INT * 3) + (MainApp.DEX * 2);
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
        MainApp.EXPNeeded = 25 + (MainApp.level * 18);
    }

     @FXML
    public static ImageView img1,
    
    
    img2,
    
    img3,
    
    img4,
    
    img5,
    
    img6,
    
    img7,
    
    img8,
    
    img9;
    
    @FXML
    public static Rectangle rec1,
    
    
    rec2,
    
    rec3,
    
    rec4,
    
    rec5,
    
    rec6,
    
    rec7,
    
    rec8,
    
    rec9;
    
    
    @FXML
    public static Pane pnlInv;
    
    @FXML
    public static TextField txtIn;
    public static Item inventory[] = new Item[9]; //array of items
    public static char inv[][] = new char[9][5];
    //type
    //specific
    //rarity

   public static int intInv[][] = new int[9][2];   //3 digits each
    //damage
    //level
    public static String sInv[][] = new String[9][2];
    //damage
    //level
    public static InnerShadow IS[] = new InnerShadow[9]; //innershadow effect
    public static ImageView iSpaces[] = new ImageView[9];//each imageview
    public static Rectangle rec[] = new Rectangle[9];//background rectangles
   
    public static int rare;
    public static int damage;
    public static int levelI;
    public static ImageView selected;
    public static boolean invVis;
    
    public static String saveInventory() {
        String s = "";
        for (int i = 0; i < 9; i++) {
//changes rarity to char
            rare = inventory[i].getRarity();
            if (rare == 0) {
                inv[i][2] = 'Z';
            } else if (rare == 1) {
                inv[i][2] = 'A';
            } else if (rare == 2) {
                inv[i][2] = 'B';
            } else if (rare == 3) {
                inv[i][2] = 'C';
            } else if (rare == 4) {
                inv[i][2] = 'D';
            }
        if(inventory[i].getType().equals("Warrior")){
            damage = inventory[i].getDamage()/2;
        }else if(inventory[i].getType().equals("Rogue")){
            damage = inventory[i].getDamage()-2;
        }else{
            //includes mage
            damage = inventory[i].getDamage();
        }
            
            intInv[i][0] = damage; 
            levelI = inventory[i].getLevel();
            intInv[i][1] = levelI;
            
            sInv[i][0]=""+intInv[i][0];
                 sInv[i][1]=""+intInv[i][1];
                 
                 
            for (int j = 0; j < 3; j++) {
                
            if (sInv[i][0].length() != 3) {
                sInv[i][0] = "0" + sInv[i][0];
                                  System.out.println(intInv[i][0]);

            }
 if (sInv[i][1].length() != 3) {
                sInv[i][1] = "0" + sInv[i][1];
                  System.out.println(intInv[i][1]);
            }
            }
            
           // sInv[i][0] = "" + intInv[i][0];

           
////
          //  inv[i][0] = inventory[i].getClass().getSuperclass().getSimpleName().charAt(0); //not usable
           // inv[i][1] = inventory[i].getClass().getSimpleName().charAt(0);                 //not usable
            //new
            inv[i][0]=inventory[i].getClass().getSuperclass().getSimpleName().charAt(0);
            inv[i][1] = inventory[i].getType().charAt(0);
            
            s = s + inv[i][0] + inv[i][1] + inv[i][2] + sInv[i][0] + sInv[i][1]; //[4] added 

           System.out.println(s);

        }
        //// will need to be edited to 36 after [3] is added
        /////sike, [4] with 3 spaces for each last two(levelI, damage)

        if (s.length() != 81) {
            System.out.println("yikes. this is bad");

        }
        return s;
    }

    public static void displayIcons() {
//sows the pictures

        img1.setImage(inventory[0].getIcon());
        img2.setImage(inventory[1].getIcon());
        img3.setImage(inventory[2].getIcon());
        img4.setImage(inventory[3].getIcon());
        img5.setImage(inventory[4].getIcon());
        img6.setImage(inventory[5].getIcon());
        img7.setImage(inventory[6].getIcon());
        img8.setImage(inventory[7].getIcon());
        img9.setImage(inventory[8].getIcon());
        for (int i = 0; i < 9; i++) {
            //adds a colour based on rarity
            if (inventory[i].getRarity() == 1) {
                //rarity 1 doesnt have a colour
            } else if (inventory[i].getRarity() == 2) {
                IS[i].setColor(Color.RED);
                IS[i].setHeight(8);
                IS[i].setWidth(8);

                iSpaces[i].setEffect(IS[i]);
            } else if (inventory[i].getRarity() == 3) {
                IS[i].setColor(Color.BLUE);
                IS[i].setHeight(8);
                IS[i].setWidth(8);
                iSpaces[i].setEffect(IS[i]);
            } else if (inventory[i].getRarity() == 4) {
                IS[i].setColor(Color.PURPLE);
                IS[i].setHeight(8);
                IS[i].setWidth(8);
                iSpaces[i].setEffect(IS[i]);

            }

        }

    }

    public static int nextSpot() {
//gets the next open spot
        for (int i = 0; i < 9; i++) {
            if (inventory[i].getClass().getSimpleName().equals("Item")) {
                return i;
            }
        }
        return 8;
    }

    public static void addToInventory(Item i) {
        //adds to the inventory by passing an item
        inventory[nextSpot()] = i;
        displayIcons();

    }

    public static void addToInventory(String s) {
         //adds to the inventory by passing a string
        int j = 0;
       
        for (int i = 0; i < 81; i += 9) {
            //puts the characters into arrays
 sInv[j][0]="";
        sInv[j][1]="";
            inv[j][0] = s.charAt(i);
            inv[j][1] = s.charAt(i + 1);
            inv[j][2] = s.charAt(i + 2);
            sInv[j][0] = sInv[j][0] + s.charAt(i + 3);
            sInv[j][0] = sInv[j][0] + s.charAt(i + 4);
            sInv[j][0] = sInv[j][0] + s.charAt(i + 5);
            sInv[j][1] = sInv[j][1] + s.charAt(i + 6);
            sInv[j][1] = sInv[j][1] + s.charAt(i + 7);
            sInv[j][1] = sInv[j][1] + s.charAt(i + 8);
            
            
            
            System.out.println(inv[j][2]);
            j++;
        }
        ///////////////////////////////////////////////////////////////////////
        //changes rarity to number
        int rare;
        for (int i = 0; i < 9; i++) {

            if (inv[i][2] == 'A') {
                rare = 1;
            } else if (inv[i][2] == 'B') {
                rare = 2;
            } else if (inv[i][2] == 'C') {
                rare = 3;
            } else if (inv[i][2] == 'D') {
                rare = 4;
            } else {
                rare = 0;
            }
            System.out.println("--------------------------"+sInv[i][0]);
             System.out.println("--------------------------"+sInv[i][1]);
           
             
             //take away 0s to get integer for damage
             try {
                intInv[i][0] = Integer.parseInt(sInv[i][0]);
                
                 
            } catch (NumberFormatException numberFormatException) {
                for (int q = 0; q <3 ; q++) {
                System.out.println("welp.");
                if (sInv[i][0].startsWith("0")){
                    sInv[i][0].replaceFirst("0", "");
                }
                
                    try {
                        intInv[i][0] = Integer.parseInt(sInv[i][0]);
                     
                    } catch (NumberFormatException numberFormatException1) {
                        intInv[i][0]=0;
                    }
            }
            }
            //
            
            ////take away 0s to get integer for Level
            try {
               
                intInv[i][1] = Integer.parseInt(sInv[i][1]);
                 
            } catch (NumberFormatException numberFormatException) {
                for (int q = 0; q <3 ; q++) {
                System.out.println("welp.");
               
                 if (sInv[i][1].startsWith("0")){
                    sInv[i][1].replaceFirst("0", "");
                }
                    try {
                        
                        intInv[i][1] = Integer.parseInt(sInv[i][1]);
                    } catch (NumberFormatException numberFormatException1) {
                         intInv[i][1]=0;
                    }
            }
            }
            ////
            
            // blank, delete item
            if (inv[i][0] == 'O') {

                if (inv[i][0] == 'I') {
                    inventory[nextSpot()] = new Item();
                }
            } 
            //
            
            else if (inv[i][0] == 'W') {
//warrior
                if (inv[i][1] == 'W') {
                 System.out.println(intInv[i][0]+"-------------"+intInv[i][1]);
                    inventory[nextSpot()] = new Warrior(intInv[i][1],"","","","",0,rare,intInv[i][0],0,"","Warrior"); //1, "", "", "", "", 0, 0, 0, 0, "", ""
                    System.out.println(inventory[nextSpot()-1].getDamage()/2+"-------------"+inventory[nextSpot()-1].getLevel());
                }
                //mage
                if (inv[i][1] == 'M') {
                      System.out.println(intInv[i][0]+"-------------"+intInv[i][1]);
                    inventory[nextSpot()] = new Mage(intInv[i][1],"","","","",0,rare,intInv[i][0],0,"","Mage");
                    System.out.println(inventory[nextSpot()].getDamage()+"-------------"+inventory[nextSpot()].getLevel());
                   
                }
                //rogue
                if (inv[i][1] == 'R') {
                 System.out.println(intInv[i][0]+"-------------"+intInv[i][1]);
                    inventory[nextSpot()] = new Rogue(intInv[i][1],"","","","",0,rare,intInv[i][0],0,"","Rogue"); //1, "", "", "", "", 0, 0, 0, 0, "", ""
                    System.out.println(inventory[nextSpot()-1].getDamage()-2+"-------------"+inventory[nextSpot()-1].getLevel());
                }

            } else if (inv[i][0] == 'P') { //     potions/other items
//make sure to add the here
//
//
//
//
//
//
//

            }

        }

        displayIcons();

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
        rec[0] = rec1;
        rec[1] = rec2;
        rec[2] = rec3;
        rec[3] = rec4;
        rec[4] = rec5;
        rec[5] = rec6;
        rec[6] = rec7;
        rec[7] = rec8;
        rec[8] = rec9;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                inv[i][j] = 0;
                inventory[i] = new Item();
                IS[i] = new InnerShadow();

                rec[i].setFill(Color.GREY);
            }

        }
        iSpaces[0] = img1;
        iSpaces[1] = img2;
        iSpaces[2] = img3;
        iSpaces[3] = img4;
        iSpaces[4] = img5;
        iSpaces[5] = img6;
        iSpaces[6] = img7;
        iSpaces[7] = img8;
        iSpaces[8] = img9;
    }

}
