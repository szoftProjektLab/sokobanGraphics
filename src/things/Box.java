package things;

import display.IDrawable;
import display.MenuFrame;
import enums.Direction;
import fields.Field;
import fields.Wall;

/**
 * Ládát megvalósító osztály
 */
public class Box extends Thing implements IDrawable {

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
     * Egy, a doboz által elfoglalt mezőre érkezni kívánó doboz(Box) hívja meg.
     * További mozgást kezdeményez a megadott irányban. Ha a mozgások során színes láda kerül neki
     * megfelelő helyre, visszaadja / továbbítja a megszerzett pontszámot a hívó felé.
     * @param d A mozgás iránya
     * @param b Az érkező Box
     * @return field.TryMove(d,s)
     */
    public int Collide(Direction d, Box b, double s){
        return field.TryMove(d,s);
    }

    /**
     * Egy, a doboz által elfoglalt mezőre érkezni kívánó játékos(Player) hívja meg.
     * További mozgást kezdeményez a megadott irányban. Ha a mozgások során színes láda kerül neki
     * megfelelő helyre, visszaadja / továbbítja a megszerzett pontszámot a hívó felé.
     * @param d A mozgás iránya
     * @param p Az érkező Player
     * @return field.TryMove(d,s)
     */
    public int Collide(Direction d, Player p, double s){
        return field.TryMove(d,s);
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
        tmp += f.Add(this);
        return tmp;
    }

    /**
     * Nem csinál semmit
     * @param w A kapott fal, amire lépnie kéne.
     * @return 0
     */
    public int AcceptMove(Wall w){ return 0; }

    /**
     *Objektum kirajzolása
     * @param x sor
     * @param y oszlop
     */
    @Override
    public void Draw(int x, int y) {
        String path="B";
        if(MenuFrame.getActiveGameFrame()!=null)MenuFrame.getActiveGameFrame().SetTextureThing(x,y,path);
    }
}
