/*
 *  By:Shayne Humphries
 *  Date:
 *  Purpose:
 */
package humphriesmartinfice.examproject;

import java.util.concurrent.ThreadLocalRandom;

public class Enemy {

    private int level, damage, defence;
    private double Health, HealthMAX, Mana, ManaMAX;

    public Enemy(int Level) {
        level = Level;
        damage = 3 + Level;
        defence = 0 + Level;
        Health = 10 + (Level * 10);
        HealthMAX = 10 + (Level * 10);
        Mana = 20 + (Level * 2);
        ManaMAX = 20 + (Level * 2);
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
    
    public double Attack(){
    return ThreadLocalRandom.current().nextInt(damage - (1 + level), damage + (1 + level) + 1);
    }

}