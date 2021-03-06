package things;

import display.Colours;
import display.IDrawable;
import display.MenuFrame;
import fields.Field;

/**
 * Színes Ládát megvalósító osztály
 */
public class ColouredBox extends Box implements IDrawable {

    /**
     * Színe a ládának
     */
    private Colours colour;
    /**
     * Ha Hole-ra lép, ez meghívódik és megsemmisül a ColouredBox,
     * meghívja a Warehouse  CBDecrease() metódusát.
     */
    public void Die(){
        warehouse.CBDecrease();
        field.Remove(this);
    }

    /**
     * A színesdoboz (ezzel kap a játékost pontot, ha a megfelelő helyre léptetik) színét beállító függvény
     * @param c Beállítandó szín
     */
    public void SetColour(Colours c){ colour = c;}

    /**
     * A színesdoboz (ezzel kap a játékost pontot, ha a megfelelő helyre léptetik) színét visszadó függvény
     * @return colour
     */
    public Colours GetColour(){ return colour;}


    /**
     * Rálépteti a ColouredBox-ot a kapott Field-re,
     * (Field. Switch, Hole paraméter esetén fut le ez a metódus)
     * @param f A kapott Field, amire lépnie kéne.
     * @return tmp A Field a ráhelyezett Box-al.
     */
    @Override
    public int AcceptMove(Field f){
        int tmp = 0;
        field.Remove(this);
        tmp += f.Add(this);
        return tmp;
    }

    /**
    *Objektum kirajzolása
     * @param x sor
     * @param y oszlop
     */
    @Override
    public void Draw(int x, int y) {
        String path="B";
        if(MenuFrame.getActiveGameFrame()!=null)MenuFrame.getActiveGameFrame().SetTextureThing(x,y,path,colour);
    }


}