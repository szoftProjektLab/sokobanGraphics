package fields;

import things.Box;
import things.Player;

public class Hole extends Field {

    /**
     * Ami rálép az meghívja, a Hole azt megsemmisíti.
     * @param p A rálépő Thing
     * @return 0
     */

    public int Interact(Player p){
        p.Die();
        return 0;
    }
    public int Interact(Box b){ return 0;}

}
