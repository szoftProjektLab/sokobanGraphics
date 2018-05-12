package fields;

import display.IDrawable;
import things.Box;
import things.Player;

public class Hole extends Field implements IDrawable {

    /**
     * Amely Player rálép az meghívja, a Hole azt megsemmisíti.
     * @param p A rálépő Player
     * @return 0
     */
    public int Interact(Player p){
        p.Die();
        return 0;
    }

    public int Interact(Box b){
        b.Die();
        return 0;
    }

    /**
     *Objektum kirajzolása
     * @param x sor
     * @param y oszlop
     */
    @Override
    public void Draw(int x, int y) {
        String path="textures/Hole2.jpg";

        //Valahogy kirajzolni az ablakra


    }
}
