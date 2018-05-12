package fields;

import things.Player;
import things.Box;

public class SpecialHole extends Hole {

    private boolean open;

    /**
     * A rálépő Box meghívja, ha nyitva van, megsemmisíti.
     * @param b A rálépő Box
     * @return 0
     */
    public int Interact(Box b){
        if(open){
            b.Die();
        }
        return 0;
    }

    /**
     * A rálépő Player meghívja, ha nyitva van, megsemmisíti.
     * @param p A rálépő Player
     * @return 0
     */
    public int Interact(Player p){
        if(open){
            p.Die();
        }
        return 0;
    }

    /**
     * Beállítja, hogy nyitva van-e a lyuk
     * @param op
     */
    public void SetOpen(boolean op) { open=op; }

}
