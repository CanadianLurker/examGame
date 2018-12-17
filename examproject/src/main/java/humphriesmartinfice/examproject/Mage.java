/*
 *  By:Shayne Humphries
 *  Date:
 *  Purpose:
 */
package humphriesmartinfice.examproject;

public class Mage extends Weapon {

    public Mage(int Level, String Attack1, String Attack2, String Attack3, String Attack4, double manaCost, int rarity, int damage, int sdamage, String Name) {
        super(Level, "Stick", "Inferno", "Thunder", "Heal",(4 + (Level * 1.2)), rarity, damage, (4 + Level), "Wizards stick giggity");
    }

}
