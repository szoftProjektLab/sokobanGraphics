package things;

import display.IDrawable;
import enums.Direction;
import fields.Field;
import fields.Steppable;
import fields.Wall;
import game.Warehouse;

/**
 * Tárgyak absztrakt ősosztálya
 */
public abstract class Thing  implements IDrawable {

    /**
     * Az aktuális raktár
     */
    Warehouse warehouse;
    /**
     * Az aktuális mező
     */
    Steppable field;

    /**
     * Beálíltjuk az aktuális raktárat
     * @param w Raktár
     */
    public void setWarehouse(Warehouse w){
        warehouse = w;
    }

    /**
     * Ha a helyet változtató Thing új Field-jén áll egy Thing, ez hívja meg,
     * hogy értesítse
     * @param d A mozgás iránya
     * @param t A használni kívánt mezőt elfogaló Thing
     * @return tmp
     */
    public abstract int MakeCollision (Direction d, Thing t, double s);

    /**
     * A leszármazottak felüldefiniálják. Az érkező Thing hívja.
     * @param d A mozgás iránya
     * @param b Az érkező Box
     * @return 0
     */
    public abstract int Collide (Direction d, Box b, double s);

    /**
     * A leszármazottak felüldefiniálják. Az érkező Thing hívja.
     * @param d A mozgás iránya
     * @param p Az érkező Player
     * @return 0
     */
    public abstract int Collide (Direction d, Player p, double s);

    /**
     * Végrehajtja a léptetést. A használni kívánt szabad mező hívja
     * @param f az új mező
     * @return tmp
     */
    public abstract int AcceptMove(Field f);

    /**
     * Nem csinál semmit
     * @param w A kapott fal, amire lépnie kéne.
     * @return 0
     */
    public abstract int AcceptMove(Wall w);

    /**
     * A leszármazottak felüldefiniálják
     */
    public abstract void Die();

    /**
     * Beállítja amelyik Field-en áll
     * @param f Bellítandó mező.
     */
    public void SetField(Steppable f)
    {
        field = f;
    }

    /**
     *Objektum kirajzolása
     * @param x sor
     * @param y oszlop
     */
    @Override
    public void Draw(int x, int y) {}
}
