package fields;

import display.IDrawable;
import display.MenuFrame;
import things.Box;
import things.ColouredBox;

/**
 * Kapcsolót megvalósító osztály
 */
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
     * @param b A róla távozó tárgy
     */
    public void Remove(Box b){
        SetThing(null);
        hole.SetOpen(false);
        active = false;
    }

    /**
     * A kapcsolóra érkező Box meghívja, kinyitja a hozzá tartozó lyukat.
     * @param b A rálépő Box
     * @return 0
     */
    public int Interact(Box b) {
        if(hole!=null) {
            hole.SetOpen(true);
            active =true;
        }
        return 0;
    }
    /**
     * A kapcsolóra érkező ColouredBox meghívja, kinyitja a hozzá tartozó lyukat.
     * @param b A rálépő ColouredBox
     * @return 0
     */
   public int Interact(ColouredBox b) {
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
        String path="SC";
        String path1="SO";

        if(active==true){
            if(MenuFrame.getActiveGameFrame()!=null)MenuFrame.getActiveGameFrame().SetTextureField(x,y,path1);
        }else{
            if(MenuFrame.getActiveGameFrame()!=null)MenuFrame.getActiveGameFrame().SetTextureField(x,y,path);
        }

    }

}
