package fields;

import game.Prototype;
import things.Thing;

public class SpecialHole extends Hole {

    private boolean open;

    /**
     * Ami rálép az meghívja, Ha nyitva van, megsemmisíti.
     * @param t A rálépő Thing
     * @return
     */
    public int Interact(Thing t){
        if(open){
            t.Die();
        }
        return 0;
    }

    /**
     * Beállítja, hogy nyitva van-e a lyuk
     * @param op
     */
    public void SetOpen(boolean op) {
        open=op;
        if(op)
            Prototype.getInstance().ReplaceField(this,"L");
        else
            Prototype.getInstance().ReplaceField(this,"Q");
    }

}
