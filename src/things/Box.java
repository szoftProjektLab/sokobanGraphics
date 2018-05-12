package things;

import enums.Direction;
import fields.Field;
import fields.Hole;
import fields.Switch;
import fields.Wall;

public class Box extends Thing {

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
     * Egy, a doboz által elfoglalt mezőre érkezni kívánó játékos(Player) vagy doboz(Box) hívja meg.
     * További mozgást kezdeményez a megadott irányban. Ha a mozgások során színes láda kerül neki
     * megfelelő helyre, visszaadja / továbbítja a megszerzett pontszámot a hívó felé.
     * @param d A mozgás iránya
     * @param t Az érkező Thing
     * @return
     */
    public int Collide(Direction d, Box t, double s){
        int tmp = field.TryMove(d,s);
        return tmp;
    }

    public int Collide(Direction d, Player t, double s){
        int tmp = field.TryMove(d,s);
        return tmp;
    }

    /**
     * Hole-ra érkezve meghívódik ez a metódust
     */
    public void Die(){
        field.Remove(this);
    }

    /**
     * Rálépteti a Box-ot a kapott Field-re,
     * (Field. Switch, Hole paraméter esetén fut le ez a metódus)
     * @param f A kapott Field, amire lépnie kéne.
     * @return tmp A Field a ráhelyezett Box-al.
     */
    public int AcceptMove(Field f){
        int tmp = 0;
        field.Remove(this);
        tmp = f.Add(this);
        return tmp;
    }

    /**
     * Nem csinál semmit
     * @param w A kapott fal, amire lépnie kéne.
     * @return 0
     */
    public int AcceptMove(Wall w){ return 0; }
}
