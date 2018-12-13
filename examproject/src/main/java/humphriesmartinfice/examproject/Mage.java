/*
 *  By:Shayne Humphries
 *  Date:
 *  Purpose:
 */
package humphriesmartinfice.examproject;

import static humphriesmartinfice.examproject.MainApp.*;
import java.util.concurrent.ThreadLocalRandom;

public class Mage extends Weapon {

    private String Attack1, Attack2, Attack3, Attack4, Name;
    private int manaCost, damage;
    Weapon weapon = new Weapon(1);

    public Mage(int Level) {
        super(Level);
        Attack1 = "Stick"; //Just hittin em with a stick, will not get extra weapon damage 
        Attack2 = "Inferno"; //Basic attack that uses up mana
        Attack3 = "Thunder"; //DOT attack
        Attack4 = "Heal"; //Some healing spell that costs slightly more mana but does more than normal potion
        manaCost = 2 + (Level * 2); //Base cost of Inferno, Thunder and heal, which will have more initial cost in combat controller
        damage = 3 + Level; //base amount of damage added onto weapon(which already randomizes damage)

        Name = "Wizards Stick"; //base name off of rarity and level
    }

    public String getAttack1() {
        return Attack1;
    }

    public String getAttack2() {
        return Attack2;
    }

    public String getAttack3() {
        return Attack3;
    }

    public String getAttack4() {
        return Attack4;
    }

    public int getCost() {
        return manaCost;
    }

    public int getSDamage() {
        return damage;
    }

    public void SpecialAttack(int defence) {
        setHealth(getHealth() + (damage + (getLevel() / 2)));
    }

    public int Attack1(int defence) {
        return ThreadLocalRandom.current().nextInt(weapon.getDamage() - (1 + getLevel()), weapon.getDamage() + (getLevel() + 1) + 1) - defence;
    }

    public int Attack2(int defence) {
        return ThreadLocalRandom.current().nextInt(weapon.getDamage() - (1 + getLevel()), weapon.getDamage() + (getLevel() + 1) + 1)
                + ThreadLocalRandom.current().nextInt(damage - (getLevel() + 1), damage + (getLevel() + 1) + 1)
                - defence;
    }
    
    public int Attack3(int defence){
    return ThreadLocalRandom.current().nextInt(damage - (getLevel() + 1), damage + (getLevel() + 1) + 1) - defence;
    }

}
