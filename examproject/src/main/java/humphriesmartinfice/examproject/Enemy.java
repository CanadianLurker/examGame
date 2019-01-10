/*
 *  By:Shayne Humphries
 *  Date:
 *  Purpose:
 */
package humphriesmartinfice.examproject;

import java.util.concurrent.ThreadLocalRandom;
import static humphriesmartinfice.examproject.FXMLCombatController.*;

public class Enemy {

    private int level, damage, defence;
    private double Health, HealthMAX, Mana, ManaMAX, EXP, cost;
    private boolean chose = false;

    public Enemy(int Level) {
        level = Level;
        damage = 3 + Level;
        defence = 0 + Level;
        HealthMAX = Level * 10;
        Health = HealthMAX;
        ManaMAX = 5 + (Level * 2);
        Mana = ManaMAX;
        cost = 1 + Level;
        EXP = 15 + (Level * 2); //the amount of exp a enemy has is based off of level
    }

    public double getHealth() {
        return Health;
    }

    public void setDef(int def) {
        def = this.defence;
    }

    public void setHealth(double health) {
        this.Health = health;
    }

    public double getHealthMAX() {
        return HealthMAX;
    }

    public double getMana() {
        return Mana;
    }

    public void setMana(Double Mana) {
        this.Mana = Mana;
    }

    public double getManaMAX() {
        return ManaMAX;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefence() {
        return defence;
    }

    public int getLevel() {
        return level;
    }

    public double getEXP() {
        return EXP;
    }

    public double Attack() { //Needs to be improved upon
        int damageRe = 0;
        while (chose == false) {
            switch (ThreadLocalRandom.current().nextInt(1, 2 + 1)) {
                case 1: //basic attack/heavy
                    if ((this.Health / this.HealthMAX) < 0.5 && this.getMana() > cost) {
                        damageRe = ThreadLocalRandom.current().nextInt(damage - (level / 2), damage + (2 + level) + 1);
                        this.setMana(this.getMana() - cost);
                        chose = true;
                    }else {
                        damageRe = ThreadLocalRandom.current().nextInt(damage - (1 + level), damage + (1 + level) + 1);
                        chose = true;
                    }
                    break;
                case 2: //dot/heal 
                    if ((this.Health / this.HealthMAX) > 0.5 && this.getMana() > cost) {
                        damageRe = ThreadLocalRandom.current().nextInt(damage - (2 + level), damage + (level) + 1);
                        this.setMana(this.getMana() - cost);
                        chose = true;
                    }
                    if ((this.Health / this.HealthMAX) < 0.5 && this.getMana() > (cost * 1.5)) {
                        damageRe = 0;
                        this.setHealth(this.Health + (this.HealthMAX / 3));
                        this.setMana(this.getMana() - (cost * 1.5));
                        chose = true;
                    }
                    break;
            }
        }
        return damageRe;
    }//need to make this a method or something, just so that it can loop when the enemy doesn't have enough mana

}
