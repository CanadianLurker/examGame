/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humphriesmartinfice.examproject;

import javafx.scene.image.Image;

/**
 *
 * @author shayneh58
 */
public class Warrior extends Weapon {

    public Warrior(int Level, String Attack1, String Attack2, String Attack3, String Attack4, double manaCost, int rarity, int damage, int sdamage, String Type) {
        super(Level, "Smash", "Great Strike", "Slash", "Rage\nRaises Attack Briefly", (1 + Level), rarity, (2 * damage), Level, "Warrior");
 this.setType("Warrior");
        this.setIcon(new Image(getClass().getResource("/axe.png").toString()));
    }
     public Warrior(int level, int rarity, int damage, String type){
         super(level,rarity,damage,type);
        this.setType("Warrior");
         this.setIcon(new Image(getClass().getResource("/axe.png").toString()));
        
    } 

}
