package fields;

import display.Colours;
import things.ColouredBox;
import things.Thing;

public class ColouredField extends Field {

    /** A színe */
    private Colours colour;

    /** A hozzá tartozó doboz */
    private ColouredBox box;

    /**
     * Beállítja a hozzá tartozó színes dobozt
     * @param cb Színes doboz
     */
    public void SetBox(ColouredBox cb){ box=cb; }

    /**
     * Beállítja a doboz színét adott színre
     * @param c Szín
     */
    private void SetColour(Colours c){ this.colour = c; }

    /**
     * Egy új színt generál a konstruktorban
     */
    public ColouredField()
    {
        SetColour(new Colours());
    }

    /**
     * Beállítja a saját és a hozzá tartozó színes doboz színét.
     */
    public void InitColour(){
        // Skeleton.getInstance.Call(this, box,"SetColour");
        box.SetColour(colour);
    }

    /**
     * Ha színes láda kerül rá, ellenőrzi, hogy a hozzá tartozó áll e rajta,
     * ha igen, pontot ad és kitörli.
     * @param t Rajta álló tárgy
     * @return pont
     */
    public int Interact(Thing t){
        int tmp=0;
        if (thing==box){
            tmp=1;
            thing.Die();
        }

        return tmp;
    }
}
