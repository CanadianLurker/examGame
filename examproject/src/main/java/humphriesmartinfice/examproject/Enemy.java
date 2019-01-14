/*
 *  By:Shayne Humphries
 *  Date:
 *  Purpose:
 */
package humphriesmartinfice.examproject;

import java.util.concurrent.ThreadLocalRandom;
import static humphriesmartinfice.examproject.MainApp.*;
import javafx.scene.image.Image;

public class Enemy {

    private int level, damage, defence, cigs;
    private int manause = 1;
    private double Health, HealthMAX, Mana, ManaMAX, EXP, cost;
    private boolean chose = false;
    private Image imgE;

    public Enemy(int Level) {
        level = Level;
        damage = 3 + Level;
        defence = 0 + Level;
        HealthMAX = Level * 12;
        Health = HealthMAX;
        ManaMAX = 5 + (Level * 4);
        Mana = ManaMAX;
        cost = 2 + Level;
        EXP = 25 + (Level * 3); //the amount of exp a enemy has is based off of level
        cigs = 5 + (Level * 5);
        int look = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        if (look == 1) {
            //enemy 1
        }
        if (look == 2) {
            //enemy 2
        }
        if (look == 3) {
            //enemy 3
        } //will have to override this somehow for bosses, as well as damage and health.
    }

    public double getHealth() {
        return Health;
    }
    
    public double getECigs(){
    return cigs;
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
        chose = false;
        int damageRe = 0;
        while (chose == false) {
            int attack = ThreadLocalRandom.current().nextInt(1, 25 + 1);
            if (attack <= 15 && attack > 10 && this.getMana() > cost) {
                damageRe = ThreadLocalRandom.current().nextInt(damage - (level / 2), damage + ((3 * level) / 2) + 1);
                this.setMana(this.getMana() - cost);
                chose = true;
            }
            if (attack <= 10) {
                damageRe = ThreadLocalRandom.current().nextInt(damage - (1 + level), damage + (1 + level) + 1);
                chose = true;
            }
            if (attack <= 19 && attack > 15 && this.getMana() > cost && dot == false) {
                damageRe = ThreadLocalRandom.current().nextInt(damage - (1 + level), damage + (level) + 1);
                this.setMana(this.getMana() - cost);
                setEdot(damageRe);
                setEcount(4);
                dot = true;
                chose = true;
            }
            if (this.Health / this.HealthMAX != 1 && attack == 20 && this.getMana() > (cost * 1.2)) {
                damageRe = 0;
                this.setHealth(this.Health + (this.HealthMAX / 3));
                if (this.Health / this.HealthMAX > 1) {
                    this.setHealth(this.HealthMAX);
                }
                this.setMana(this.getMana() - (cost * 1.5));
                chose = true;
            }
            if (this.Mana < cost && attack > 20 && manause > 0) {
                this.setMana(this.Mana + (cost * 2));
                manause--;
                if(this.Mana / this.ManaMAX > 1){
                this.setMana(this.ManaMAX);
                }
                chose = true;
            }
        }
        if(damageRe < 0){
        damageRe = 0;
        }
        return damageRe;
    }//need to make this a method or something, just so that it can loop when the enemy doesn't have enough mana

}
