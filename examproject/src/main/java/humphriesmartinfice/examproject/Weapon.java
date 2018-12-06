/*
 *  By:Shayne Humphries
 *  Date:
 *  Purpose:
 */
package humphriesmartinfice.examproject;


import java.util.concurrent.ThreadLocalRandom;

public class Weapon {
    
private int level, rarity, damage, critchance, critdamage;
        
     public Weapon(int Level){
     damage = ThreadLocalRandom.current().nextInt(3,(5 + Level)+1) + Level;
     int possible = ThreadLocalRandom.current().nextInt(1,100+1);
     if(possible < 65){
     rarity = 1; }
     else if(possible >= 65 && possible < 85 ){
     rarity = 2;
     }else if (possible >= 85 && possible < 95){
     rarity = 3; 
     }else if (possible > 95){
     rarity = 4;
     }
     level = Level; 
     critchance = 1;
     critdamage = 3 + Level;
     }
     
     public int getDamage(){
     return this.damage;
     }
     
     public int getRarity(){
     return this.rarity;
     } 
     
     public int getLevel(){
     return this.level;
     }
     
    public void setSomething(){
    
    }
     
} 