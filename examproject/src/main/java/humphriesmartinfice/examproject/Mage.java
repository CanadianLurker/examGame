/*
 *  By:Shayne Humphries
 *  Date:
 *  Purpose:
 */
package humphriesmartinfice.examproject;

public class Mage extends Weapon {
    
    private String Attack1, Attack2, Attack3, Attack4;
    
    public Mage(int Level) {
        super(Level);
        Attack1 = "Inferno";
        Attack2 = "Thunder";
        Attack3 = "Blizzard";
        Attack4 = "Tsunami";
    }
    
    public String getAttack1(){
    return Attack1;
    }
    
    public String getAttack2(){
    return Attack2;
    }
    
    public String getAttack3(){
    return Attack3;
    }
    
    public String getAttack4(){
    return Attack4;
    }
    
    
}
