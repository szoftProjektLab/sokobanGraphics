package fields;

import things.Box;
import things.Thing;

public class Switch extends Field {

    private SpecialHole hole;

    /**
     * Beállítja a hozzá tartozó speciális lyukat
     * @param h speciális lyuk
     */
    public void SetHole( SpecialHole h){ hole = h;}

    /**
     * Ha egy doboz eltávolítódik a kapcsolóról, a hozzá tartozó lyuk becsukódik
     * @param t A róla távozó tárgy
     */
    public void Remove(Thing t){
        this.thing=null;
        hole.SetOpen(false);
    }

    /**
     * A kapcsolóra érkező doboz meghívja, kinyitja a hozzá tartozó lyukat
     * @param t Rálépő tárgy
     * @return
     */
    public int Interact(Box b) {
        if(hole!=null) {
            hole.SetOpen(true);
        }
        return 0;
    }

}
