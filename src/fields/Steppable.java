package fields;

import enums.Direction;
import things.Box;
import things.Player;
import things.Thing;

public abstract class Steppable {

    /** A leszármazotton lévő Thing*/
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
     * Egy Player-t hozzácsatol az aktuális mezőhöz.
     * @param t A csatolandó Player(Thing)
     * @return 0
     */
    public int Add(Player t){
        this.thing = t;
        t.SetField(this);
        int tmp = Interact(t);
        return tmp;
    }

    /**
     * Egy Box-ot hozzácsatol az aktuális mezőhöz.
     * @param t A csatolandó Box(Thing)
     * @return 0
     */
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
     * Itt nem csinál semmit,
     * a leszármazottak felülírják ha használni akarják
     * @param p Player
     * @return 0
     */
    public int Interact(Player p){ return 0; }

    /**
     * Itt nem csinál semmit,
     * a leszármazottak felülírják ha használni akarják
     * @param b Box
     * @return 0
     */
    public int Interact(Box b){ return 0; }


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
