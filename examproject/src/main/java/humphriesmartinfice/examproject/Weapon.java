/*
 *  By:Shayne Humphries
 *  Date:
 *  Purpose:
 */
package humphriesmartinfice.examproject;

import static humphriesmartinfice.examproject.MainApp.*;
import java.util.concurrent.ThreadLocalRandom;

public class Weapon {

    private String Attack1, Attack2, Attack3, Attack4, Name, type;
    private int level, rarity, damage, sdamage, extra;
    private double manaCost;

    public Weapon(int Level, String Attack1, String Attack2, String Attack3, String Attack4, double manaCost, int rarity, int damage, int sdamage, String Name,String Type) {
        this.Attack1 = Attack1; //Just hittin em with a stick, will not get extra weapon damage 
        this.Attack2 = Attack2; //Basic attack that uses up mana
        this.Attack3 = Attack3; //DOT attack
        this.Attack4 = Attack4; //Some healing spell that costs slightly more mana but does more than normal potion
        this.manaCost = manaCost; //Base cost of Inferno, Thunder and heal, which will have more initial cost in combat controller
        this.sdamage = sdamage; //base amount of damage added onto weapon(which already randomizes damage)
        this.Name = Name; //base name off of rarity and level    
        this.damage = ThreadLocalRandom.current().nextInt(3, (5 + Level) + 1) + Level;
        this.rarity = rarity;
        level = Level;
        this.type = Type;
        
    }
    
    public int getDamage() {
        return this.damage;
    }

    public int getRarity() {
        return this.rarity;
    }

    public int getLevel() {
        return this.level;
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

    public double getCost() {
        return manaCost;
    }

    public int getSDamage() {
        return sdamage;
    }
    
    public String getName(){
    return this.Name;
    }
    
    public String getType(){
    return this.type;
    }

    public void SpecialAttack(int defence) {
        setHealth(getHealth() + (damage + (level / 1.3)));
        
    }
    
    public int getExtra(){
    extraget();
    return extra;
    }

    public int Attack1(int defence) {
        extraget();
        return ThreadLocalRandom.current().nextInt(damage - 2, damage + (getLevel() + level) + 1) - defence + extra;
    }

    public int Attack2(int defence) {
        extraget();
        return ThreadLocalRandom.current().nextInt(sdamage - 2, sdamage + (getLevel() + level) + 1)
                + ThreadLocalRandom.current().nextInt(damage - 2, damage + (getLevel() + level) + 1)
                - defence + extra ;
    }

    public int Attack3(int defence) {
        extraget();
        return ThreadLocalRandom.current().nextInt(sdamage - 2, sdamage + (getLevel() + level) + 1) - defence + extra;
    }
    
    private void extraget(){
    if(Type(type)==1){
        extra = getINT();
        }
        if(Type(type)==2){
        extra = getDEX();
        }if(Type(type)==3){
        extra = getSTR();
        }
    }
    
    private int Type(String type){
    int t = 0;
    if (type.equals("Mage")){
    t= 1;
    }if(type.equals("Rogue")){
    t= 2;
    }
    if(type.equals("Warrior")){
    t= 3;
    }
    return t; 
    }

}
