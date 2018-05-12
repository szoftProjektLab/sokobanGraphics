package fields;

import things.Box;
import things.Player;

public class Hole extends Field {

    /**
     * Amely Player rálép az meghívja, a Hole azt megsemmisíti.
     * @param p A rálépő Player
     * @return 0
     */
    public int Interact(Player p){
        p.Die();
        return 0;
    }

    public int Interact(Box b){
        b.Die();
        return 0;
    }
}
