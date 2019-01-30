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

    public Rogue(int Level, String Attack1, String Attack2, String Attack3, String Attack4, double manaCost, int rarity, int damage, int sdamage, String Type) {
        super(Level, "Slash", "Blade Storm", "Bleed", "Intimidate\nLowers Enemy Attack", (1 + Level), rarity, ((rarity * Level) + damage), (damage + Level), "Rogue");
        this.setType("Rogue");
        this.setIcon(new Image(getClass().getResource("/dagger.png").toString()));
    }

    public Rogue(int level, int rarity, int damage, String type) {
        super(level, rarity, damage, type);
        this.setType("Rogue");
        this.setIcon(new Image(getClass().getResource("/dagger.png").toString()));

    } 

}
