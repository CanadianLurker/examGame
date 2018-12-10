/*
 *  By:Shayne Humphries
 *  Date:
 *  Purpose:
 */
package humphriesmartinfice.examproject;

public class Enemy {

    private int level, damage, defence;
    private double Health, HealthMAX, Mana, ManaMAX;

    public Enemy(int Level) {
        level = Level;
        damage = 1;
        defence = 1;
        Health = 50;
        HealthMAX = 75;
        Mana = 1;
        ManaMAX = 20;
    }

    public double getHealth() {
        return Health;
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

}
