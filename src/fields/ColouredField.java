package fields;

import display.Colours;
import display.IDrawable;
import things.ColouredBox;

public class ColouredField extends Field implements IDrawable {

    /** A színe */
    private Colours colour;

    /** A hozzá tartozó doboz */
    private ColouredBox matchingbox;

    /**
     * Beállítja a hozzá tartozó színes dobozt
     * @param cb Színes doboz
     */
    public void SetBox(ColouredBox cb){ matchingbox=cb; }

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
        matchingbox.SetColour(colour);
    }

    /**
     * Ellenőrzi, hogy a hozzá tartozó színes láda áll e rajta,
     * ha igen, pontot ad és kitörli.
     * @param cb Rajta álló ColouredBox
     * @return tmp
     */
    public int Interact(ColouredBox cb){
        int tmp=0;
        if (cb.GetColour()==matchingbox.GetColour()){
            tmp=1;
            thing.Die();
        }
        return tmp;
    }

    /**
     *Objektum kirajzolása
     * @param x sor
     * @param y oszlop
     */
    @Override
    public void Draw(int x, int y) {
        String path="textures/Field2.jpg";

        //Valahogy kirajzolni az ablakra


    }
}
