package fields;

import things.Box;

public class Switch extends Field {

    /** A Switch-hez tartozó speciális lyuk */
    private SpecialHole hole;

    /**
     * Beállítja a hozzá tartozó speciális lyukat.
     * @param h speciális lyuk
     */
    public void SetHole( SpecialHole h){ hole = h;}

    /**
     * Ha egy doboz eltávolítódik a kapcsolóról, a hozzá tartozó lyuk becsukódik.
     * @param b A róla távozó tárgy
     */
    public void Remove(Box b){
        this.thing=null;
        hole.SetOpen(false);
    }

    /**
     * A kapcsolóra érkező Box meghívja, kinyitja a hozzá tartozó lyukat.
     * @param b A rálépő Box
     * @return 0
     */
    public int Interact(Box b) {
        if(hole!=null) {
            hole.SetOpen(true);
        }
        return 0;
    }

}
