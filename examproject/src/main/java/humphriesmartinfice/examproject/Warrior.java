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

    public Warrior(int Level, String Attack1, String Attack2, String Attack3, String Attack4, double manaCost, int rarity, int damage, int sdamage, String Name, String Type) {
        super(Level, "Smash", "Great Strike", "Slash", "Rage\nRaises Attack Briefly", (1 + Level), rarity, (2 * damage), Level, "Big Stick", "Warrior");
this.setIcon(new Image(getClass().getResource("/axe.png").toString()));
    }

}
