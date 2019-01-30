/*
 *  By:Shayne Humphries
 *  Date:
 *  Purpose:
 */
package humphriesmartinfice.examproject;

import javafx.scene.image.Image;

public class Mage extends Weapon {

    public Mage(int Level, String Attack1, String Attack2, String Attack3, String Attack4, double manaCost, int rarity, int damage, int sdamage, String Type) {
        super(Level, "Stick", "Inferno", "Thunder", "Heal\nRefills Player Health", (1 + (Level * 1.2)), rarity, (damage + (rarity * Level)), (2 * Level), "Mage");
this.setType("Mage");
this.setIcon(new Image(getClass().getResource("/staff.png").toString()));
    }
  public Mage(int level, int rarity, int damage, String type){
         super(level,rarity,damage,type);
        this.setType("Mage");
         this.setIcon(new Image(getClass().getResource("/staff.png").toString()));
        
    } 
}
