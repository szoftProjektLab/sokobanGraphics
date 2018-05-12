import enums.Direction;
import fields.Field;
import fields.Hole;
import fields.Wall;
import things.Player;
import game.Warehouse;


public class Main {

    public static void main(String[] args) {

        Warehouse w = new Warehouse();

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
        f1.Add(p11); //Interactot hív
        f2.Add(p12);
        p11.StartMove(Direction.Right);

        System.out.println("\n");

        //Falra playert tol???

    }
}
