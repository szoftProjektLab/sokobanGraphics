package fields;

import enums.Direction;
import things.Box;
import things.Player;
import things.Thing;

public abstract class Steppable {

    protected Thing thing;

    /**
     * Az adott irányba beállítja a szomszédos mezőt.
     * @param d irány
     * @param f szomszédnak szánt Field
     */
    public abstract void SetNeighbour(Direction d, Field f);

    /**
     * Visszaadja abban az irányban lévő szomszédos mezőt
     * @param d Szomszéd iránya
     * @return
     */
    public abstract Field GetNeighbour(Direction d);

    /**
     * Visszaadja a jelenleg eltárolt, rajta lévő tárgy referenciáját
     * @return
     */
    public abstract Thing getThing();

    /**
     * Egy tárgyat hozzácsatol az aktuális mezőhöz.
     * @param t A csatolandó tárgy
     * @return 0
     */

    public int Add(Player t){
        this.thing = t;
        t.SetField(this);
        int tmp = Interact(t);
        return tmp;
    }
    public int Add(Box t){
        this.thing = t;
        t.SetField(this);
        int tmp = Interact(t);
        return tmp;
    }

    /**
     * Az éppen rajta álló tárgyat eltávolítja a mezőről
     */
    public abstract void Remove(Thing t);

    /**
     * Virtuális függvény, itt nem csinál semmit
     * @param p
     * @return
     */

    public int Interact(Player p){
        System.out.println("PlayerInteract");
        return 0;
    }

    public int Interact(Box b){
        System.out.println("BoxInteract");
        return 0;}


    /**
     * A mezőn álló játékos ezzel jelzi helyváltoztatási szándékát.
     * @param d A mozgás iránya
     * @param s A játékos maradék ereje
     * @return
     */
    public abstract int TryMove(Direction d, double s);

    /**
     * A szomszédos mező a felőle érkező mozgás igényt ennek meghívásával jelzi.
     * @param d A mozgás iránya
     * @param t Az érkezni kívánó Thing
     * @param s A játékos maradék ereje
     * @return
     */
    public abstract int TryMove(Direction d, Thing t, double s);


    /**
     * Beállítja a mezőn adott effektet
     * @param effect  Effekt
     */
    public abstract void setEffect(double effect);
}
