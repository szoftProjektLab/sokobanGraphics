package fields;

import display.IDrawable;
import display.MenuFrame;
import things.Player;
import things.Box;

public class SpecialHole extends Hole implements IDrawable {

    private boolean open = false;

    /**
     * A rálépő Box meghívja, ha nyitva van, megsemmisíti.
     * @param b A rálépő Box
     * @return 0
     */
    public int Interact(Box b){
        if(open){
            b.Die();
        }
        return 0;
    }

    /**
     * A rálépő Player meghívja, ha nyitva van, megsemmisíti.
     * @param p A rálépő Player
     * @return 0
     */
    public int Interact(Player p){
        if(open){
            p.Die();
        }
        return 0;
    }

    /**
     * Beállítja, hogy nyitva van-e a lyuk
     * @param op Beállítandó állás.
     */
    public void SetOpen(boolean op) { open=op; }

    /**
     *Objektum kirajzolása
     * @param x sor
     * @param y oszlop
     */
    @Override
    public void Draw(int x, int y) {
        String path="textures/Field2.jpg";
        String path1="textures/Hole2.jpg";

        if(open==false){
            if(MenuFrame.getActiveGameFrame()!=null)MenuFrame.getActiveGameFrame().SetTextureField(x,y,path);
        }else{
            if(MenuFrame.getActiveGameFrame()!=null)MenuFrame.getActiveGameFrame().SetTextureField(x,y,path1);
        }
}

}
