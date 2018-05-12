package fields;

import display.IDrawable;
import display.MenuFrame;
import enums.Direction;
import things.Box;
import things.Player;
import things.Thing;

public class Wall extends Field implements IDrawable {

    /**
     * A szomszédos mező a felőle érkező mozgás igényt ennek meghívásával jelzi.
     * Szükséges mert különben nem a Wall paraméterű AcceptMove-ot hívja meg
     * @param d A mozgás iránya
     * @param t Az érkezni kívánó Thing
     * @param s A játékos maradék ereje
     * @return tmp
     */
    @Override
    @SuppressWarnings("Duplicates")
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

    public int Add(Player p){
        this.thing = p;
        p.SetField(this);
        return Interact(p);
    }

    public int Add(Box b){ return 0;}

    /**
     *Objektum kirajzolása
     * @param x sor
     * @param y oszlop
     */
    @Override
    public void Draw(int x, int y) {
        String path="textures/Wall.jpg";
        if(MenuFrame.getActiveGameFrame()!=null)MenuFrame.getActiveGameFrame().SetTextureField(x,y,path);
    }
}
