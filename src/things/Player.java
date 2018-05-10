package things;

import enums.Direction;
import fields.Field;
import fields.Wall;
import game.Prototype;

public class Player extends Thing {

    /** A játékos ponjai */
    private int points;
    private double strength;

    public Player()
    {
        points = 0;
        strength = 3;
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
     * @param t Az érkező Box, vagy Player
     * @param s A játékos ereje
     * @return
     */
    public int Collide (Direction d, Box t, double s){
        int tmp = 0;
        tmp = field.TryMove(d, strength);
        return tmp;
    }

    public int Collide (Direction d, Player t, double s){
        //System.out.println("Player: Collide player"); //OK
        return 0;
    }


    /**
     * Az egész mozgatást egy irányba elkezdi,
     * majd pontszámát növeli
     * @param d A mozgás iránya
     */
    public void StartMove(Direction d){
        Field tmp = field.GetNeighbour(d);
        /*
        if (tmp instanceof Wall){ //5.3.8 OK
            //fal
        }
        else
        */
            tmp.TryMove(d, this, strength);
    }

    /**
     * Nem csinál semmit
     * @param w A kapott fal, amire lépnie kéne.
     * @return 0
     */

    public int AcceptMove(Wall w){
        System.out.println("PlayerWallAcceptmove");
        //Die();
        return 0;
    }

    public int AcceptMove(Field f){ //Field, Switch, Hole ra ez
        int tmp = 0;

        field.Remove(this);
        tmp = f.Add(this);
        System.out.println("PlayerFieldAcceptmove");
        return tmp;
    }

    /**
     * Lyukba esés, esetleg amikor egy ládát rátolnak,
     * ebben az esetben ez a mezőről törli és
     * a játékosok számát csökkenti eggyel.
     */
    public void Die(){
       //warehouse.PDecrease(); /////////////////////////////////////////////////////Ez kell
        field.Remove(this);
        System.out.println("PlayerDie");
    }

    /**
     * Növeli a Field effect-jének az értékét.
     */
    public void PlaceHoney(){
        field.setEffect(1.5);
        Prototype.getInstance().ReplaceField(field,"H");
    }

    /**
     * Csökkenti a Field effect-jének az értékét.
     */
    public void PlaceOil(){
        field.setEffect(0.5);
        Prototype.getInstance().ReplaceField(field,"O");
    }
}
