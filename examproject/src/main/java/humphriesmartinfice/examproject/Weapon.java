/*
 *  By:Shayne Humphries
 *  Date:
 *  Purpose:
 */
package humphriesmartinfice.examproject;

import static humphriesmartinfice.examproject.MainApp.*;
import java.util.concurrent.ThreadLocalRandom;

public class Weapon extends Item {

    private String Attack1, Attack2, Attack3, Attack4, Name, type;
    private int level, rarity,damage, sdamage, extra, nerf, extrawar;
    private double manaCost;

    public Weapon(int Level, String Attack1, String Attack2, String Attack3, String Attack4, double manaCost, int rarity, int damage, int sdamage, String Name, String Type) {
        this.Attack1 = Attack1; //Just hittin em with a stick, will not get extra weapon damage 
        this.Attack2 = Attack2; //Basic attack that uses up mana
        this.Attack3 = Attack3; //DOT attack
        this.Attack4 = Attack4; //Some healing spell that costs slightly more mana but does more than normal potion
        this.manaCost = manaCost; //Base cost of Inferno, Thunder and heal, which will have more initial cost in combat controller
        this.sdamage = sdamage; //base amount of damage added onto weapon(which already randomizes damage)
        this.Name = Name; //base name off of rarity and level    
        this.setDamage(ThreadLocalRandom.current().nextInt(3, (5 + Level) + 1) + Level)  ;
        int possible = ThreadLocalRandom.current().nextInt(1, 100 + 1);
        if (possible < 65) {
            this.setRarity(1);
        } else if (possible >= 65 && possible < 85) {
            this.setRarity(2);
        } else if (possible >= 85 && possible < 95) {
            this.setRarity(3);
        } else if (possible > 95) {
            this.setRarity(4) ;
        }
        this.setLevel(Level); 
        this.type = Type;
        this.setDamage(damage);
        this.setRarity(rarity);
    }
/*
    public int getDamage() {
        return this.damage;
    }

    public int getRarity() {
        return this.rarity;
    }

    public int getLevel() {
        return this.level;
    }
*/
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

    public double getCost() {
        return manaCost;
    }

    public int getSDamage() {
        return sdamage;
    }

    public String getName() {
        return this.Name;
    }

    public String getType() {
        return this.type;
    }
    
    public double getExtraWar(){
    return extrawar;
    } 

    public void MageAttack() {
        setHealth(getHealth() + (this.getDamage() + (this.getLevel() / 1.3)) + extra);
    }

    public void RogueAttack(Enemy enemy) {
        nerf += this.getLevel();
    }
    
    public int getNerf(){
    return nerf;
    }

    public void WarriorAttack() {

        extrawar = damage * (3 / 2);
    }

    public void ResetWar() {
        extrawar = 0;

    }

    public int getExtra() {
        extraget();
        return extra;
    }

    public int Attack1(int defence) {
        extraget(); //gets extra damage from attributes
        return ThreadLocalRandom.current().nextInt(this.getDamage() - 2, this.getDamage() + (getLevel() + this.getLevel()) + 1) + extra - defence;
    }

    public int Attack2(int defence) {
        extraget();
        return ThreadLocalRandom.current().nextInt(sdamage - 2, sdamage + (getLevel() + this.getLevel()) + 1)
                + ThreadLocalRandom.current().nextInt(this.getDamage() - 2, this.getDamage() + (getLevel() + this.getLevel()) + 1)
                + extra - defence;
    }

    public int Attack3(int defence) {
        extraget();
        return ThreadLocalRandom.current().nextInt(sdamage - 2, sdamage + (getLevel() + this.getLevel()) + 1) + extra - defence;
    }

    private void extraget() {
        if (type.equals("Mage")) {
            extra = getINT();
        }
        if (type.equals("Rogue")) {
            extra = getDEX();
        }
        if (type.equals("Warrior")) {
            extra = getSTR();
        }
    }

}
