package fields;

import display.IDrawable;
import display.MenuFrame;
import enums.Direction;
import things.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Mezőt megvalósító osztály
 */
public class Field extends Steppable implements IDrawable {

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
        neighbours.put(d, f);
    }

    /**
     * Visszaadja abban az irányban lévő szomszédos mezőt
     * @param d Szomszéd iránya
     * @return neighbours.get(d)
     */
    public Field GetNeighbour(Direction d){
        return neighbours.get(d);
    }

    /**
     * A mezőn álló játékos ezzel jelzi helyváltoztatási szándékát.
     * @param d A mozgás iránya
     * @param s A játékos maradék ereje
     * @return tmp
     */
    public int TryMove(Direction d, double s){
        return neighbours.get(d).TryMove(d, getThing(), s);
    }

    /**
     * A szomszédos mező a felőle érkező mozgás igényt ennek meghívásával jelzi.
     * @param d A mozgás iránya
     * @param t Az érkezni kívánó Thing
     * @param s A játékos maradék ereje
     * @return tmp
     */
    @SuppressWarnings("Duplicates")
    public int TryMove(Direction d, Thing t, double s){
        int tmp =0;
        if(s < effect) {
            return 0;
        }
        s-=effect;
        if (getThing()==null){
            tmp += t.AcceptMove(this);
        } else{
            tmp += t.MakeCollision(d, getThing(), s);
            if(getThing() == null)
                tmp += t.AcceptMove(this);
        }
        return tmp;
    }

    /**
     * Beállítja a mezőn adott effektet
     * @param effect  Effekt
     */
    public void setEffect(double effect) {
        this.effect = effect;
    }
    public double getEffect() {
        return this.effect;
    }

    /**
     *Objektum kirajzolása
     * @param x sor
     * @param y oszlop
     */
    @Override
    public void Draw(int x, int y) {
        //String path="textures/Field2.jpg";
        String path="F";
        if(MenuFrame.getActiveGameFrame()!=null)MenuFrame.getActiveGameFrame().SetTextureField(x,y,path);
    }

}
