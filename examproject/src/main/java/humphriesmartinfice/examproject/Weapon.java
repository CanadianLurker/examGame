/*
 *  By:Shayne Humphries
 *  Date:
 *  Purpose:
 */
package humphriesmartinfice.examproject;

import static humphriesmartinfice.examproject.MainApp.*;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.Image;

public class Weapon {

    private String Attack1, Attack2, Attack3, Attack4, type;
    private int sdamage, extra, nerf, extrawar, rarityI, damage, level;
    private double manaCost;
    private Image icon;

    public Weapon(int Level, String Attack1, String Attack2, String Attack3, String Attack4, double manaCost, int rarity, int damage, int sdamage, String Type) {
        this.Attack1 = Attack1; //Just hittin em with a stick, will not get extra weapon damage 
        this.Attack2 = Attack2; //Basic attack that uses up mana
        this.Attack3 = Attack3; //DOT attack
        this.Attack4 = Attack4; //Some healing spell that costs slightly more mana but does more than normal potion
        this.manaCost = manaCost; //Base cost of Inferno, Thunder and heal, which will have more initial cost in combat controller
        this.sdamage = sdamage; //base amount of damage added onto weapon(which already randomizes damage)
        int possible = ThreadLocalRandom.current().nextInt(1, 100 + 1);
        if (possible < 40) {
            this.rarityI = (1);
        } else if (possible >= 40 && possible < 70) {
            this.rarityI =(2);
        } else if (possible >= 70 && possible < 90) {
            this.rarityI =(3);
        } else if (possible > 90) {
            this.rarityI =(4);
        }
        this.damage = (ThreadLocalRandom.current().nextInt(4, (5 + Level) + 1) + (Level * rarity));
        level = Level;
        this.type = Type;
    }

    public Weapon() {
        icon = null;
        type = "Item";
    }

    public Weapon(int level, int rarity, int damage, String type) {
        this.setDamage(damage);
        this.setLevel(level);
        this.setRarity(rarity);
        this.setType(type);
    }

    public int getRarity() {
        return this.rarityI;
    }

    public void setRarity(int i) {
        rarityI = i;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int i) {
        damage = i;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        level = i;
    }

    public Image getIcon() {
        return this.icon;
    }

    public void setIcon(Image i) {
        icon = i;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String i) {
        type = i;
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

    public void setAttack1() {
        if (type.equals("Rogue")) {
            Attack1 = "Slash";
        }
        if (type.equals("Mage")) {
            Attack1 = "Stick";
        }
        if (type.equals("Warrior")) {
            Attack1 = "Smash";
        }
    }

    public void setAttack2() {
        if (type.equals("Rogue")) {
            Attack2 = "Blade Storm";
        }
        if (type.equals("Mage")) {
            Attack2 = "Inferno";
        }
        if (type.equals("Warrior")) {
            Attack2 = "Great Strike";
        }
    }

    public void setAttack3() {
        if (type.equals("Rogue")) {
            Attack3 = "Bleed";
        }
        if (type.equals("Mage")) {
            Attack3 = "Thunder";
        }
        if (type.equals("Warrior")) {
            Attack3 = "Bash";
        }
    }

    public void setAttack4() {
        if (type.equals("Rogue")) {
            Attack4 = "Intimidate\n Lowers Enemy Damage";
        }
        if (type.equals("Mage")) {
            Attack4 = "Heal\nRefills Player Health";
        }
        if (type.equals("Warrior")) {
            Attack4 = "Rage\nRaises Attack Briefly";
        }
    }

    public double getCost() {
        return manaCost;
    }

    public void setCost() {
        if (type.equals("Rogue")) {
            manaCost = 1 + level;
        }
        if (type.equals("Mage")) {
            manaCost = (1 + (level * 1.2));
        }
        if (type.equals("Warrior")) {
            manaCost = (1 + level);
        }
    }

    public int getSDamage() {
        return sdamage;
    }

    public void setSDamage() {
        if (type.equals("Rogue")) {
            sdamage = (damage + level);
        }
        if (type.equals("Mage")) {
            sdamage = (2 * level);
        }
        if (type.equals("Warrior")) {
            sdamage = (level);
        }
    }

    public void setDamage() {
        if (type.equals("Rogue")) {
            damage = (rarityI * level) + damage;
        }
        if (type.equals("Mage")) {
            damage = damage + (rarityI * level);
        }
        if (type.equals("Warrior")) {
            damage = 2 * (damage + rarityI);
        }
    }


    public double getExtraWar() {
        return extrawar;
    }

    public void MageAttack() {
        setHealth(getHealth() + (this.getDamage() + (this.getLevel() / 1.3)) + extra);
    }

    public void RogueAttack(Enemy enemy) {
        nerf += this.getLevel();
    }

    public int getNerf() {
        return nerf;
    }

    public void WarriorAttack() {
        extrawar = this.getDamage() * (3 / 2);
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
