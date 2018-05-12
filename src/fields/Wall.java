package fields;

import enums.Direction;
import things.Box;
import things.Player;
import things.Thing;

public class Wall extends Field {

    /**
     * A szomszédos mező a felőle érkező mozgás igényt ennek meghívásával jelzi.
     * Szükséges mert különben nem a Wall paraméterű AcceptMove-ot hívja meg
     * @param d A mozgás iránya
     * @param t Az érkezni kívánó Thing
     * @param s A játékos maradék ereje
     * @return
     */
    @Override
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

    //public void Remove(Thing t){ } //???

    public int Add(Player t){
        this.thing = t;
        t.SetField(this);
        int tmp = Interact(t);
        return tmp;
    }

    public int Add(Box t){ return 0;}
}
