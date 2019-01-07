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
public class Warrior extends Weapon {

    public Warrior(int Level, String Attack1, String Attack2, String Attack3, String Attack4, double manaCost, int rarity, int damage, int sdamage, String Name, String Type) {
        super(Level, "Smash", "Great Strike", "Slash", "Rage", (1 + Level), rarity, (4 +damage), Level, "Big Stick", "Warrior");

    }

}
