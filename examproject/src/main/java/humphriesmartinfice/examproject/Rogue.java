/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humphriesmartinfice.examproject;

/**
 *
 * @author shayneh58
 */
public class Rogue extends Weapon {

    public Rogue(int Level, String Attack1, String Attack2, String Attack3, String Attack4, double manaCost, int rarity, int damage, int sdamage, String Name, String Type) {
        super(Level, "Slash", "Blade Storm", "Bleed", "Intimidate", (1 + Level), rarity, (2 + damage), (2 + Level), "Stab Stick", "Rogue");

    }

}
