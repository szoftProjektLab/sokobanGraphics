package fields;

import enums.Direction;
import things.Thing;

import java.util.HashMap;
import java.util.Map;

public class Field extends Steppable {

    /** A Field szomszédos Field-jei*/
    private Map<Direction, Field> neighbours;
    /** A Field-en lévő Thing*/
    protected Thing thing;
    /** A Field-en aktív effekt*/
    protected double effect;

    /**
     * Konstruktor: neighbours map inicializálása, effect beállítása
     */
    public Field(){
        //Kell konstruktorba? Skeleton.getInstance.Return(this);
        neighbours = new HashMap<Direction, Field>();
        effect = 1;
        thing = null;
    }

    /**
     * Az adott irányba beállítja a szomszédos mezőt.
     * @param d irány
     * @param f szomszédnak szánt Field
     */
    public void SetNeighbour(Direction d, Field f){
        //Kell setterbe? Skeleton.getInstance.Return(this);
        neighbours.put(d, f);
    }

    /**
     * Visszaadja abban az irányban lévő szomszédos mezőt
     * @param d Szomszéd iránya
     * @return
     */
    public Field GetNeighbour(Direction d){
        return neighbours.get(d);
    }

    /**
     * Visszaadja a jelenleg eltárolt, rajta lévő tárgy referenciáját
     * @return
     */
    public Thing getThing()
    {
        return thing;
    }


    /**
     * Az éppen rajta álló tárgyat eltávolítja a mezőről
     */
    public void Remove(Thing t){
        this.thing = null;

    }

    /**
     * A mezőn álló játékos ezzel jelzi helyváltoztatási szándékát.
     * @param d A mozgás iránya
     * @param s A játékos maradék ereje
     * @return
     */
    public int TryMove(Direction d, double s){
        //return neighbours.get(d).TryMove(d, this.thing);
        int tmp = neighbours.get(d).TryMove(d, this.thing, effect);
        return tmp;
    }

    /**
     * A szomszédos mező a felőle érkező mozgás igényt ennek meghívásával jelzi.
     * @param d A mozgás iránya
     * @param t Az érkezni kívánó Thing
     * @param s A játékos maradék ereje
     * @return
     */
    public int TryMove(Direction d, Thing t, double s){
        int tmp =0;
        if(s < effect) {
            return 0;
        }
        s-=effect;
        if (this.thing==null){
            tmp = t.AcceptMove(this);
        } else{
            tmp = t.MakeCollision(d, this.thing, s);
            if(this.thing == null)
                tmp = t.AcceptMove(this);
        }
        return tmp;
    }

    public void SetThing(Thing t){ thing = t;}


    /**
     * Beállítja a mezőn adott effektet
     * @param effect  Effekt
     */
    public void setEffect(double effect) {
        this.effect = effect;
    }

}
