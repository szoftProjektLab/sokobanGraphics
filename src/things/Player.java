package things;

import display.IDrawable;
import enums.Direction;
import fields.Field;
import fields.Wall;

public class Player extends Thing implements IDrawable {

    /** A játékos ponjai */
    private int points;
    /** A játékos ereje */
    private double strength;


    /**
     * Player paraméterezett konstrunktora
     * @param s A beallítandó strength-je a játékosnak
     */
    public Player(double s) {
        points = 0;
        strength = s;
    }

    /**
     * A kapott értékkel növeli a Player pontszámának értékét.
     * @param points hozzá adandó pontok száma
     */
    public void AddPoints(int points){
        this.points += points;
    }

    /**
     * Ha a helyet változtató Thing új Field-jén áll egy Thing, ez hívja meg,
     * hogy értesítse
     * @param d A mozgás iránya
     * @param t A használni kívánt mezőt elfogaló Thing
     * @return
     */
    public int MakeCollision (Direction d, Thing t, double s){
        int tmp = t.Collide(d, this, s);
        return tmp;
    }

    /**
     * Ütközeti a Player-t és a Box-ot. Box tolja a Playert.
     * @param d A mozgás iránya
     * @param t Az érkező Box
     * @param s A játékos ereje
     * @return
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
     * majd pontszámát növeli
     * @param d A mozgás iránya
     */
    public void StartMove(Direction d){
        Field tmp = field.GetNeighbour(d);
        tmp.TryMove(d, this, strength);
    }

    /**
     * Megöli a falnak tolt játékost, Wall paraméter esetén fut le ez a metódus
     * @param w A kapott fal, amire lépnie kéne.
     * @return 0
     */
    //////////////////////////// Hiányos
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
       warehouse.PDecrease();
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
        String path;
        if(warehouse.getPlayerCount()==0){
            path="textures/Worker1.png";
        }
        else path="textures/Worker2.png";


        //Valahogy kirajzolni az ablakra


    }
}
