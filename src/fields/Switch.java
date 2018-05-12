package fields;

import display.IDrawable;
import things.Box;
import things.Thing;

public class Switch extends Field implements IDrawable {

    /** A Switch-hez tartozó speciális lyuk */
    private SpecialHole hole;
    /**active e a switch*/
    private boolean active;

    /**
     * Beállítja a hozzá tartozó speciális lyukat.
     * @param h speciális lyuk
     */
    public void SetHole( SpecialHole h){ hole = h;}

    /**
     * Ha egy doboz eltávolítódik a kapcsolóról, a hozzá tartozó lyuk becsukódik.
     * @param t A róla távozó tárgy
     */
    public void Remove(Thing t){
        this.thing=null;
        hole.SetOpen(false);
        active = false;
    }

    /**
     * A kapcsolóra érkező Box meghívja, kinyitja a hozzá tartozó lyukat.
     * @param b A rálépő Box
     * @return
     */
    public int Interact(Box b) {
        if(hole!=null) {
            hole.SetOpen(true);
            active =true;
        }
        return 0;
    }

    /**
     *Objektum kirajzolása
     * @param x sor
     * @param y oszlop
     */
    @Override
    public void Draw(int x, int y) {
        String path="textures/Switchclosed.jpg";
        String path1="textures/Switchopen.jpg";

        if(active==true){

        }else{

        }
        //Valahogy kirajzolni az ablakra


    }

}
