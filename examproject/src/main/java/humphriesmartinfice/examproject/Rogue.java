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
public class Rogue extends Weapon {

    public Rogue(int Level, String Attack1, String Attack2, String Attack3, String Attack4, double manaCost, int rarity, int damage, int sdamage, String Name, String Type) {
        super(Level, "Slash", "Blade Storm", "Bleed", "Intimidate\nLowers Enemy Attack", (1 + Level), rarity, (Level + damage), (damage + Level), "Stab Stick", "Rogue");
        this.setType("Rogue");
        this.setIcon(new Image(getClass().getResource("/dagger.png").toString()));
    }

}
