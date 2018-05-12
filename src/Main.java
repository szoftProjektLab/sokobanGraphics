import display.Colours;
import enums.*;
import fields.*;
import game.*;
import windows.*;
import java.awt.EventQueue;
import things.*;


public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuFrame window = new MenuFrame();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        /*Warehouse w = new Warehouse();

        Field f1 = new Field();
        Field f2 = new Wall();
        f1.SetNeighbour(Direction.Right, f2);
        Player p = new Player(3);
        f1.Add(p);
        p.StartMove(Direction.Right);

        System.out.println("\n");

        Field f3 = new Field();
        Hole h1= new Hole();
        f3.SetNeighbour(Direction.Right, h1);
        Player p2 = new Player(3);
        f3.Add(p2);
        p2.StartMove(Direction.Right);

        System.out.println("\n");

        Field f11 = new Field();
        Field f12 = new Wall();
        f1.SetNeighbour(Direction.Right, f2);
        Player p11 = new Player(3);
        Player p12 = new Player(3);
        f11.Add(p11); //Interactot hív
        f12.Add(p12);
        p11.StartMove(Direction.Right);

        System.out.println("\n");*/

        Box b= new Box();
        ColouredBox cb1=new ColouredBox();
        ColouredBox cb2=new ColouredBox();
        Colours color1=new Colours();
        Colours color2=new Colours();
        cb1.SetColour(color1);
        cb2.SetColour(color2);
        if(cb1.GetColour()==cb2.GetColour()) System.out.println("True");
        else System.out.println("False");

        //Falra playert tol???

    }
}
