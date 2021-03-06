package fields;

import display.IDrawable;
import display.MenuFrame;
import things.Box;
import things.ColouredBox;
import things.Player;

/**
 * Lyukat megvalósító osztály
 */
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

    /**
     * Amely Box rálép az meghívja, a Hole azt megsemmisíti.
     * @param b A rálépő Box
     * @return 0
     */
    public int Interact(Box b){
        b.Die();
        return 0;
    }
    /**
     * Amely ColouredBox rálép az meghívja, a Hole azt megsemmisíti.
     * @param b A rálépő ColouredBox
     * @return 0
     */
    public int Interact(ColouredBox b){
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
        String path="H";
        if(MenuFrame.getActiveGameFrame()!=null)MenuFrame.getActiveGameFrame().SetTextureField(x,y,path);
    }
}
