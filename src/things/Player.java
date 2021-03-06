package things;

import display.IDrawable;
import display.MenuFrame;
import enums.Direction;
import fields.Field;
import fields.Wall;
import game.Game;

/**
 * Játékost megvalósító osztály
 */
public class Player extends Thing implements IDrawable {

    /** A játékos ponjai */
    private int points;
    /** A játékos ereje */
    private double strength;
    /**
     * Játékos azonosítója, konstruktorban adunk értéket
     */
    private int id;

    /**
     * A játékos mozgási ereje 3, azonosító beálíltása
     */
    public Player(int id1) {
        points = 0;
        strength = 4;
        id = id1;
    }

    /**
     * A kapott értékkel növeli a Player pontszámának értékét.
     * @param points hozzá adandó pontok száma
     */
    public void AddPoints(int points){
        this.points += points;
        Game.getInstance().getRunning().checkCB();
    }

    /**
     * Ha a helyet változtató Thing új Field-jén áll egy Thing, ez hívja meg,
     * hogy értesítse
     * @param d A mozgás iránya
     * @param t A használni kívánt mezőt elfogaló Thing
     * @return t.Collide(d, this, s)
     */
    public int MakeCollision (Direction d, Thing t, double s){
        return t.Collide(d, this, s);
    }

    /**
     * Ütközeti a Player-t és a Box-ot. Box tolja a Playert.
     * @param d A mozgás iránya
     * @param t Az érkező Box
     * @param s A játékos ereje
     * @return tmp
     */
    public int Collide (Direction d, Box t, double s){
        int tmp = 0;
        tmp = field.TryMove(d, strength);
        return tmp;
    }

    /**
     * Ütközeti a Player-t és egy másik Player-t, amely során nem törénik semmi,
     * mert nem tudják egymást tolni.
     * @param d A mozgás iránya
     * @param t Az érkező Player(Thing)
     * @param s A játékos ereje
     * @return
     */
    public int Collide (Direction d, Player t, double s){
        return 0;
    }

    /**
     * Az egész mozgatást egy irányba elkezdi,
     * ha nem fal van a az adott irányban
     * majd pontszámát növeli
     * @param d A mozgás iránya
     */
    public void StartMove(Direction d){
        Field tmp = field.GetNeighbour(d);
        int p;
        if(! (tmp instanceof Wall)) {
            p = tmp.TryMove(d, this, strength);
            AddPoints(p);
        }
    }

    /**
     * Pontokat visszaadó metódus
     * @return Pontok
     */
    public int GetPoints()
    {
        return points;
    }

    /**
     * Megöli a falnak tolt játékost, Wall paraméter esetén fut le ez a metódus
     * @param w A kapott fal, amire lépnie kéne.
     * @return 0
     */
    public int AcceptMove(Wall w){
        Die();
        return 0;
    }

    /**
     * Rálépteti a Player-t a kapott Field-re,
     * (Field. Switch, Hole paraméter esetén fut le ez a metódus)
     * @param f A kapott Field, amire lépnie kéne.
     * @return tmp A Field a ráhelyezett Player-el.
     */
    public int AcceptMove(Field f){
        int tmp = 0;
        field.Remove(this);
        tmp = f.Add(this);
        return tmp;
    }

    /**
     * Lyukba esés, esetleg amikor egy ládát rátolnak,
     * ebben az esetben ez a mezőről törli és
     * a játékosok számát csökkenti eggyel.
     */
    public void Die(){
       warehouse.PDecrease(id);
       field.Remove(this);
    }

    /**
     * Növeli a Field effect-jének az értékét.
     */
    public void PlaceHoney(double effect){
        field.setEffect(effect);
    }

    /**
     * Csökkenti a Field effect-jének az értékét.
     */
    public void PlaceOil(double effect){
        field.setEffect(effect);
    }

    /**
     *Objektum kirajzolása
     * @param x sor
     * @param y oszlop
     */
    @Override
    public void Draw(int x, int y) {
        String path="";
        if(id==1){
            path="W1";
        }
        else if(id==2){
            path="W2";
        }
        if(!path.equals("")&& MenuFrame.getActiveGameFrame()!=null)MenuFrame.getActiveGameFrame().SetTextureThing(x,y,path);
    }
}
