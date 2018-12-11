/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humphriesmartinfice.examproject;

import java.util.concurrent.ThreadLocalRandom;

public class ConsumableItems {

    private int r, amount;
    private String effect;

    public ConsumableItems(int Level) {
        r = ThreadLocalRandom.current().nextInt(1, 4 + 1);
        switch (r) {
            case 1:
                effect = "Health Potion";
                break;
            case 2:
                effect = "Mana Potion";
                break;
            case 3:
                effect = "Bomb";
                break;
            case 4:
                effect = "Single Shot";
                break;
        }
        amount = 3 + (Level * 2);
    }

    public String getItemEffect() {
        return effect;
    }

    public int getAmount() {
        return amount;
    }

}
