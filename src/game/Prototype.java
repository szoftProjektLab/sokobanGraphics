package game;

import enums.Direction;
import things.Player;
import things.Thing;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Prototype {
    /**
     * Field referencia, azonosító párokat tartalmaz.
     */
    private Map<Object, String> fields;
    /**
     * Thing referencia, azonosító párokat tartalmaz.
     */
    private Map<Object, String> things;
    /**
     * Player azonosító, referencia párokat tartalmaz.
     */
    private Map<String, Object > players;
    /**
     * aktuális warehouseról egy példány.
     */
    private Warehouse ware;
    /**
     * Önmagából egy példány.
     */
    private static Prototype prototype = new Prototype();

    /**
     *Konstruktor
     */
    private Prototype() {
        fields = new HashMap<Object, String>();
        things = new HashMap<Object, String>();
        players = new HashMap<String, Object>();
    }

    /**
     * @return Önmagából létrehozott példányát visszaadja
     */
    public static Prototype getInstance() {
        return prototype;
    }

    /**
     * Beállítja az aktuális warehoust
     * @param a akt. Warehouse
     */
    public void AddWarehouse(Warehouse a) {
        ware = a;
    }

    public void ReplaceField(Object a, String newName)
    {
        fields.remove(a);
        fields.put(a,newName);
    }

    /**
     * Field hozzáadása fields listához.
     * @param a Field
     * @param b Azonosító
     */
    public void AddField(Object a, String b){
        fields.put(a,b);
    }

    /**
     * Thing hozzáadása fields listához.
     * @param a Thing
     * @param b Azonosító
     */
    public void AddThing(Object a, String b){
        things.put(a,b);
    }

    /**
     * Field azonosítójának módosítása.
     * @param a Field
     * @param b Azonosító
     */
    public void ModifyField(Object a, String b){
        fields.remove(a);
        things.put(a,b);
    }

    /**
     * Thing azonosítójának módosítása.
     * @param a Thing
     * @param b Azonosító
     */
    public void ModifyThing(Object a, String b){
        fields.remove(a);
        things.put(a,b);
    }

    /**
     * Játékos hozzáadása a listához.
     * @param b Azonosító
     * @param a Player
     */
    public void AddPlayer(String b, Object a){
        players.put(b,(Object)a);
    }

    /**
     * Thing azonosítót visszaadja.
     * @param a Thing referencia.
     * @return Thing azonosító
     */
    private String GetThingName(Object a) {
        return things.get(a);
    }

    /**
     * Field azonosítót visszaadja.
     * @param a Field referencia.
     * @return Field azonosító
     */
    private String GetFieldName(Object a) {
        return fields.get(a);
    }


    /**
     * Minden tárolt dolog ürítése.
     */
    public void Clear() {
        things.clear();
        fields.clear();
        ware = null;
    }

    /**
     * A tesztelő Prototype osztállyal való kommunikációjának lebonyolítása.
     */
    public void Start() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //System.setProperty( "user.dir", "C:\\Users\\aron1\\OneDrive\\Dokumentumok\\GitHub\\proto\\out\\production\\Proto\\Input\\" );
        while (true) {
            try {
                System.out.printf("Command:\n->");
                String s = br.readLine();
                String[] st = s.split(" ");
                switch (st[0]) {
                    case "loadInput":
                        loadInput(st[1]);
                        break;
                    case "saveOutput":
                        saveOutput(st[1]);
                        break;
                    case "movePlayer":
                        movePlayer(st[1], st[2]);
                        if(ware!=null)
                        Draw();
                        break;
                    case "createEffect":
                        createEffect(st[1], st[2]);
                        Draw();
                        break;
                    case "checkDifference":
                        checkDifference(st[1], st[2]);
                        break;
                    case "loadMap":
                        loadMap(st[1]);
                        Draw();
                        break;
                    case "exit":
                        exit();
                        break;
                    default:
                        break;
                }
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
    }

    void Draw()
    {
        for(int j=0;j<ware.getRow();j++){
            for(int i=0;i<ware.getColumn();i++){
                System.out.print(fields.get(ware.getField(j,i)));
            }
            System.out.println();
        }
        System.out.println();
        Thing t;
        for(int j=0;j<ware.getRow();j++){
            for(int i=0;i<ware.getColumn();i++){
                if((t = ware.getThing(j,i))!=null)
                    System.out.print(things.get(ware.getThing(j, i)));
                else
                    System.out.print("n");
            }
            System.out.println();
        }
    }

    /**
     * Input betöltése
     * @param filePath Input file
     */
    private void loadInput(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String s;
           // System.setProperty( "user.dir", "C:\\Users\\aron1\\OneDrive\\Dokumentumok\\GitHub\\proto\\out\\production\\Proto\\Input\\" );
            while(!((s=br.readLine()).equals("exit"))){
                String[] st = s.split(" ");
                switch (st[0]) {
                    case "saveOutput":
                        saveOutput(st[1]);
                        break;
                    case "movePlayer":
                        movePlayer(st[1], st[2]);
                        System.out.println("Player " + st[1] + " moved:");
                        Draw();
                        break;
                    case "createEffect":
                        createEffect(st[1], st[2]);
                        break;
                    case "checkDifference":
                        checkDifference(st[1], st[2]);
                        break;
                    case "loadMap":
                        loadMap(st[1]);
                        System.out.println("Map loaded:");
                        Draw();
                        break;
                    default:
                        break;
                }
            }
            exit();
        }catch(IOException e){
            System.out.println(e.toString());
        }


    }

    /**
     * Output file kimentése
     * @param filePath Output file
     */
    private void saveOutput(String filePath) {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
            for(int j=0;j<ware.getRow();j++){
                for(int i=0;i<ware.getColumn();i++){
                    pw.write(fields.get(ware.getField(j,i)));
                    if(i!=ware.getColumn()-1)pw.write(",");
                }
                pw.println();
            }
            pw.println();
            Thing t;
            for(int j=0;j<ware.getRow();j++){
                for(int i=0;i<ware.getColumn();i++){
                    if((t = ware.getThing(j,i))!=null) {
                        pw.write(things.get(ware.getThing(j, i)));
                    }
                    else
                    {
                        pw.write("n");
                    }
                    if(i!=ware.getColumn()-1)pw.write(",");
                }
                pw.println();
            }
            pw.close();
        }catch(IOException e){
            System.out.println(e.toString());
        }
    }

    /**
     * Adott player mozog a megfelelő irányba
     * @param playername Player azonosító
     * @param direction irány
     */
    private void movePlayer(String playername, String direction) {
        Player a =(Player)players.get(playername);
        a.StartMove(Direction.valueOf(direction));
    }

    /**
     * Adott Player effectet rak maga alá.
     * @param playername Player azonosító
     * @param effect Effect azonosító
     */
    private void createEffect(String playername, String effect) {
        Player a =(Player)players.get(playername);
        if(effect.equals("O")){
            a.PlaceOil();
        }
        else if(effect.equals("H")){
            a.PlaceHoney();
        }
    }

    /**
     * Különbség detektálása
     * @param filePath1 egyik fájl
     * @param filePath2 másik fájl
     */
    private void checkDifference(String filePath1, String filePath2) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath1));
            BufferedReader br1 = new BufferedReader(new FileReader(filePath1));
            String s1,s2;
            boolean b=true;

            while((s1=br.readLine())!=null && (s2=br1.readLine())!=null){
             if(!(s1.equals(s2))){
                 b=false;
                 System.out.println("Hiba:");
                 System.out.println(s1);
                 System.out.println("");
                 System.out.println(s2);
             }

            }
            if(b)System.out.println("-- 100%-os egyezes\n\n");
            br.close();
            br1.close();
        }catch(IOException e){
            System.out.println(e.toString());
        }

    }

    /**
     *Pálya betöltése
     * @param filePath Pálya fájl
     */
    private void loadMap(String filePath) {
        Game a = Game.getInstance();
        a.StartGame(filePath);
    }

    /**
     * Kilépés a tesztesetből.
     */
    private void exit() {
        Clear();
    }

}

