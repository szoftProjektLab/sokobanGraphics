package things;

import display.Colours;

public class ColouredBox extends Box{

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
     * @param c
     */
    public void SetColour(Colours c){ colour = c;}


}