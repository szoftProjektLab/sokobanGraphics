package game;

import fields.*;
import things.Box;
import things.ColouredBox;
import things.Player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Játékot megvalósító osztály
 */
public class Game {

    /**
     * Saját magáról példány <<Singleton>>
     */
    private static Game game = new Game();
    /**
     * Raktárról tárolt referencia
     */
    private Warehouse running;

    /**
     * A magából készült példány elérése.
     * @return A saját magából létrehozott példány.
     */
    public static Game getInstance(){
        return game;
    }

    /**
     * Játék kezdését biztosító folyamat, egy fájlból beolvassa a mátrix oszlopainak és sorainak a számát,
     * ami alapján létrehoz egy raktárat és elkezdi inicializálni ezt a megadott fájl szerint, majd a
     * legvégén a raktárra bízza a szomszédjainak a beállítását
     */
    public void StartGame(String file){
        String line = null;
        running = new Warehouse();
        //int playercount=0;


        try {
            // BufferedReader-be csomagolt FileReader, ami alapján a raktárat, és azoknak az entitásait felépítjük
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            //Sorok száma
            int row = Integer.parseInt(bufferedReader.readLine());
            //Oszlopok száma
            int column = Integer.parseInt(bufferedReader.readLine());

            running.SetDimensions(row,column);
            //Üres sor átlépése
            bufferedReader.readLine();
            //Az első iteráció, ami a soron
            for(int curRow = 0; curRow<row; curRow++)
            {
                //Hasznos sor beolvasása
                line = bufferedReader.readLine();
                //A kapott sort kisebb String-ekre tördeljük
                String[] sorok = line.split(",");
                //Második iteráció, ami az oszlopokon megy végig
                for(int curColumn = 0; curColumn<column; curColumn++)
                {
                    //Sorok alapján a megfelelő mező létrehozása, prototípus-ba átadjuk, és hozzáadjuk a raktárhoz
                    switch(sorok[curColumn])
                    {
                        //Mező létrehozása
                        case "F":
                            Field f = new Field();
                            running.setField(curRow,curColumn,f);
                            break;
                        //Fal létrehozása
                        case"W":
                            Wall w = new Wall();
                            running.setField(curRow,curColumn,w);
                            break;
                        //Lyuk létrehozása
                        case "J":
                            Hole h = new Hole();
                            running.setField(curRow,curColumn,h);
                            break;
                        //Kapcsoló létrehozása
                        case"S":
                            Switch s = new Switch();
                            running.setField(curRow,curColumn,s);
                            break;
                        //Színesmező (végső doboznak a helye) létrehozása
                            case "C":
                                ColouredField cf = new ColouredField();
                                running.setField(curRow,curColumn,cf);
                                break;
                        //Nyitott speciális Lyuk
                        case "L":
                            SpecialHole sh = new SpecialHole();
                            sh.SetOpen(true);
                            running.setField(curRow,curColumn,sh);
                            break;
                        //Zárt speciális lyuk
                            case "Q":
                                SpecialHole sh2 = new SpecialHole();
                                sh2.SetOpen(false);
                                running.setField(curRow,curColumn,sh2);
                                break;
                        //Mézzel bekent mező
                        case"H":
                            Field f2 = new Field();
                            f2.setEffect(1.5);
                            running.setField(curRow,curColumn,f2);
                            break;
                        //Olajjal bekent mező
                        case"O":
                            Field f3 = new Field();
                            f3.setEffect(0.5);
                            running.setField(curRow,curColumn,f3);
                            break;

                        default:
                            break;
                    }
                }
            }
            //Üres sor beolvasása
            bufferedReader.readLine();

            //Ugyanaz, mint az előző sor - oszlop iteráció, csak itt most a mezőkön álló entitásokra
            for(int curRow = 0; curRow<row; curRow++)
            {
                line = bufferedReader.readLine();
                String[] sorok = line.split(",");
                for(int curColumn = 0; curColumn<column; curColumn++)
                {
                    switch(sorok[curColumn])
                    {
                        //Első játékos létrehozása
                        case"A":
                            Player a = new Player(1);
                            a.setWarehouse(running);
                            //running.setPlayerCount(++playercount);
                            running.getField(curRow,curColumn).Add(a);
                            running.addPlayer(a);
                            break;
                        //Második játékos létrehozása
                        case "B":
                            Player b = new Player(2);
                            b.setWarehouse(running);
                            //running.setPlayerCount(++playercount);
                            running.getField(curRow,curColumn).Add(b);
                            running.addPlayer(b);
                            break;
                        //Láda létrehozása (nem ad pontot)
                        case"D":
                            Box d = new Box();
                            d.setWarehouse(running);
                            running.getField(curRow,curColumn).Add(d);
                            break;
                        //Színes láda létrehozása (a láda, amit ha a megfelelő helyre tolnak, ad pontot)
                        case "K":
                            ColouredBox k = new ColouredBox();
                            k.setWarehouse(running);
                            running.getField(curRow,curColumn).Add(k);
                            break;
                        //Ha esetleg üresen álló mező jön (Például 'n', akkor nem veszünk fel semmit)
                        default:
                            break;
                    }
                }
            }
            bufferedReader.readLine();

            //Párosítást megvalósító függvény (Színesláda - Színesmezőhöz, illetve Kapcsoló - Speciális Lyukhoz)
            while( (line = bufferedReader.readLine())!=null) {
                // A sorok ;-vel elválasztva, ezzel megkapjuk a párosítás típusát,
                // illetve a párosítandó objektumok két koordinátáját
                String[] sorok = line.split(";");
                if (sorok.length == 3) {
                    // Első objektum koordinátája
                    String[] koord1 = sorok[1].split(",");
                    // Második objektum koordinátája
                    String[] koord2 = sorok[2].split(",");

                    // Első string alapján a típus megkülönböztetése
                    switch (sorok[0]) {
                        //Színesláda - Színesmezőhöz párosítás megvalósítása
                        case "C":
                            //Színesmező példányosítása
                            ColouredField cb = (ColouredField) running.getField(Integer.parseInt(koord1[0]), Integer.parseInt(koord1[1]));
                            //Színesmezőhöz rendeljük a színesládát
                            cb.SetBox((ColouredBox) running.getThing(Integer.parseInt(koord2[0]), Integer.parseInt(koord2[1])));
                            running.increaseColouredBoxCount();
                            cb.InitColour();
                            break;
                        //Kapcsoló - Speciális Lyuk párosítás megvalósítása
                        case "S":
                            //Kapcsoló példányosítása
                            Switch s = (Switch) running.getField(Integer.parseInt(koord1[0]), Integer.parseInt(koord1[1]));
                            //Kapcsolóhöz rendeljük a Speciális Lyukat
                            s.SetHole((SpecialHole) running.getField(Integer.parseInt(koord2[0]), Integer.parseInt(koord2[1])));
                            break;
                        default:
                            break;
                    }
                }
            }
            //Bezárjük a bufferedReader-t
            bufferedReader.close();

        }
        //Ha nem találnánk a fájl-t, akkor kezeljük a hibát
        catch(FileNotFoundException ex) {
            System.out.println("Unable to find the file '" + file + "'");
        }
        //Abban az esetben, ha valami probléma keletkezik a fájl olvasása közben
        catch(IOException ex) {
            System.out.println("Error reading from the file '" + file + "'");
        }
        //Raktárra bízzuk a szomszédok beállítását
        running.StartingProcess();
     }

     /**
     * Jelenlegi raktár referencia beállítása
     * @param r Raktár
     */
    public void setRunning(Warehouse r){running = r; }

    public Warehouse getRunning()
    {
        return running;
    }

    /**
     * Játék befejezését biztosító folyamat
     */
    public void EndGame(){
            System.out.println("A játék véget ért!");
    }
}
