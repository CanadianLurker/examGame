/*
 *  By:Shayne Humphries
 *  Date:
 *  Purpose:
 */
package humphriesmartinfice.examproject;

import java.util.concurrent.ThreadLocalRandom;

public class Enemy {

    private int level, damage, defence;
    private double Health, HealthMAX, Mana, ManaMAX, EXP;

    public Enemy(int Level) {
        level = Level;
        damage = 3 + Level;
        defence = 0 + Level;
        HealthMAX = Level * 10;
        Health = HealthMAX;
        ManaMAX = 5 + (Level * 2);
        Mana = ManaMAX;
        EXP = 15 + (Level * 2); //the amount of exp a enemy has is based off of level
    }

    public double getHealth() {
        return Health;
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

    public double Attack() {
        return ThreadLocalRandom.current().nextInt(damage - (1 + level), damage + (1 + level) + 1);
    }

}
