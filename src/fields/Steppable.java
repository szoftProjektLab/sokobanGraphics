package fields;

import enums.Direction;
import things.Box;
import things.ColouredBox;
import things.Player;
import things.Thing;

public abstract class Steppable {

    /** A leszármazotton lévő Thing*/
    protected Thing thing;

    /**
     * Absztrakt
     * Leszármazott osztályban az adott irányba beállítja a szomszédos mezőt.
     * @param d irány
     * @param f szomszédnak szánt Field
     */
    public abstract void SetNeighbour(Direction d, Field f);

    /**
     * Absztrakt
     * Leszármazott osztályban visszaadja abban az irányban lévő szomszédos mezőt
     * @param d Szomszéd iránya
     * @return
     */
    public abstract Field GetNeighbour(Direction d);

    /**
     * Visszaadja a jelenleg eltárolt, rajta lévő tárgy referenciáját
     * @return thing
     */
    public Thing getThing()
    {
        return thing;
    }

    /**
     * Beállítja a rajta lévő tárgy referenciáját
     * @param t
     */
    public void SetThing(Thing t){ thing = t;}

    /**
     * Egy Player-t hozzácsatol az aktuális mezőhöz.
     * @param p A csatolandó Player(Thing)
     * @return 0
     */
    public int Add(Player p){
        this.thing = p;
        p.SetField(this);
        int tmp = Interact(p);
        return tmp;
    }

    /**
     * Egy Box-ot hozzácsatol az aktuális mezőhöz.
     * @param b A csatolandó Box(Thing)
     * @return 0
     */
    public int Add(Box b){
        this.thing = t;
        b.SetField(this);
        int tmp = Interact(b);
        return tmp;
    }

    /**
     * Egy ColouredBox-ot hozzácsatol az aktuális mezőhöz.
     * @param t A csatolandó Box(Thing)
     * @return 0
     */
    public int Add(ColouredBox cb){
        this.thing = cb;
        cb.SetField(this);
        int tmp = Interact(cb);
        return tmp;
    }

    /**
     * Az éppen rajta álló tárgyat eltávolítja a mezőről
     * @param b Box
     */
    public void Remove(Box b){
        this.thing = null;

    }

    /**
     * Az éppen rajta álló tárgyat eltávolítja a mezőről
     * @param p Player
     */
    public void Remove(Player p){
        this.thing = null;

    }

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
     * Itt nem csinál semmit,
     * a leszármazottak felülírják ha használni akarják
     * @param cb ColouredBox
     * @return 0
     */
    public int Interact(ColouredBox cb){ return 0; }



    /**
     * Absztrakt
     * Leszármazott osztályban a mezőn álló játékos ezzel jelzi helyváltoztatási szándékát.
     * @param d A mozgás iránya
     * @param s A játékos maradék ereje
     * @return
     */
    public abstract int TryMove(Direction d, double s);

    /**
     * Absztrakt
     * Leszármazott osztályban a szomszédos mező a felőle érkező mozgás igényt ennek meghívásával jelzi.
     * @param d A mozgás iránya
     * @param t Az érkezni kívánó Thing
     * @param s A játékos maradék ereje
     * @return
     */
    public abstract int TryMove(Direction d, Thing t, double s);


    /**
     * Absztrakt
     * Leszármazott osztályban beállítja a mezőn adott effektet
     * @param effect  Effekt
     */
    public abstract void setEffect(double effect);
}
