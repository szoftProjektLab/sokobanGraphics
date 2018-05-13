package display;
import enums.Direction;
import game.Game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import game.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

/**
 * Játékot megvalósító ablak
 */
public class GameFrame extends JFrame {

    /**
     *  Fő Jpanel amin az többi alpanel található
     */
    private JPanel contentPane;
    /**
     * Kép betöltésére használt string változó
     */
    private String globalVariable = null;
    /**
     * Kép betöltését eltároló BufferedImage
     */
    private BufferedImage texture = null;
    /**
     * Scoreboard-ot és a labelGame-et tároló JPanel
     */
    private JPanel mainPanel;
    /**
     * A Panel, amire a játék van rajzolva
     */
    private JPanel labelGame;
    /**
     * BufferedImage mátrix, ahol a tárgyak textúráját tároljuk
     */
    private BufferedImage thingTextures[][];
    /**
     * BufferedImage mátrix, ahol a fieldek textúráját tároljuk
     */
    private BufferedImage fieldTextures[][];
    /**
     * Előre eltárolt felhasznált képek HashMap-ja String kulcsszavakkal
     */
    private HashMap<String, BufferedImage> fieldImages = new HashMap<String, BufferedImage>();
    /**
     * Player1 játékos Score-ját megjelenítő JLabel
     */
    private JLabel labelPlayer1;
    /**
     * Player2 játékos Score-ját megjelenítő JLabel
     */
    private JLabel labelPlayer2;

    /**
     * Képbetöltéshez felhasznált metódus
     * @return betöltött kép
     */
    private BufferedImage loadImage(){
        if(globalVariable == null)
            return null;
        BufferedImage result = null;
        try {
            result = ImageIO.read(new File(globalVariable));
        } catch (IOException e) {
            System.out.println("Rossz volt!");
        }
        return result;
    }

    /**
     * Frame-et megvalósító konstruktor
     */
    public GameFrame() {
        setTitle("Sokoban");
        Image im = Toolkit.getDefaultToolkit().getImage("textures/icon.jpg");
        setIconImage(im);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 810);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, -6));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);


        Warehouse wh = Game.getInstance().getRunning();
        thingTextures = new BufferedImage[wh.getRow()][wh.getColumn()];
        fieldTextures = new BufferedImage[wh.getRow()][wh.getColumn()];

        mainPanel = new JPanel();
        contentPane.add(mainPanel, BorderLayout.NORTH);
        mainPanel.setBorder(new EmptyBorder(5, 155, 5, 160));
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new BorderLayout(0, 0));

        JLabel labelPlayer1 = new JLabel("P1 -"+Game.getInstance().getRunning().getPlayer(0).GetPoints());
        labelPlayer1.setFont(new Font("Arial Black", Font.BOLD, 22));
        labelPlayer1.setForeground(Color.WHITE);
        mainPanel.add(labelPlayer1,BorderLayout.WEST);

        JLabel labelPlayer2 = new JLabel("P2 -"+Game.getInstance().getRunning().getPlayer(1).GetPoints());
        labelPlayer2.setFont(new Font("Arial Black", Font.BOLD, 22));
        labelPlayer2.setForeground(Color.WHITE);
        mainPanel.add(labelPlayer2,BorderLayout.EAST);

        labelGame = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                Warehouse wh = Game.getInstance().getRunning();

                BufferedImage oilTexture = fieldImages.get("O");
                BufferedImage honeyTexture = fieldImages.get("HO");

                for(int i=0;i<wh.getRow();i++){
                    for(int j=0;j<wh.getColumn();j++){
                        texture = fieldTextures[i][j];
                        g.drawImage(texture, (57*j)*getWidth()/574, (55*i)*getHeight()/539,(57*j+57)*getWidth()/574, (55*i+55)*getHeight()/539,0,0, 120, 120, null);

                        if(wh.getField(i,j).getEffect()==0.5)
                            g.drawImage(oilTexture, (57*j)*getWidth()/574, (55*i)*getHeight()/539,(57*j+57)*getWidth()/574, (55*i+55)*getHeight()/539,0,0, 120, 120, null);
                        else if(wh.getField(i,j).getEffect()==1.5)
                            g.drawImage(honeyTexture, (57*j)*getWidth()/574, (55*i)*getHeight()/539,(57*j+57)*getWidth()/574, (55*i+55)*getHeight()/539,0,0, 120, 120, null);

                        if(wh.getField(i,j).getThing()!=null) {
                            texture = thingTextures[i][j];
                            g.drawImage(texture, (57*j)*getWidth()/574, (55*i)*getHeight()/539,(57*j+57)*getWidth()/574, (55*i+55)*getHeight()/539,0,0, 120, 120, null);
                        }
                    }
                }
                labelPlayer1.setText("P1 - "+Game.getInstance().getRunning().getPlayer(0).GetPoints());
                labelPlayer2.setText("P2 - "+Game.getInstance().getRunning().getPlayer(1).GetPoints());
            }
        };
        contentPane.add(labelGame, BorderLayout.CENTER);
        labelGame.setLayout(new GridLayout(1, 0, 0, 0));

        //Bufferelés
        globalVariable="textures/Field2.jpg"; fieldImages.put("F", loadImage());
        globalVariable="textures/Hole2.jpg"; fieldImages.put("H", loadImage());
        globalVariable="textures/Switchclosed.jpg"; fieldImages.put("SC", loadImage());
        globalVariable="textures/Switchopen.jpg"; fieldImages.put("SO", loadImage());
        globalVariable="textures/Wall.jpg"; fieldImages.put("W", loadImage());
        globalVariable="textures/Worker1.png";fieldImages.put("W1", loadImage());
        globalVariable="textures/Worker2.png";fieldImages.put("W2", loadImage());
        globalVariable="textures/box.jpg";fieldImages.put("B", loadImage());
        globalVariable="textures/honey.png";fieldImages.put("HO", loadImage());
        globalVariable="textures/oil.png";fieldImages.put("O", loadImage());

        globalVariable=null;

        /**
         * Mozgást és olaj/méz lehelyezést megvalósító KeyListener
         */
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg) {
                if (Game.getInstance().getEndGame()){
                    return;
                }
                Warehouse wh = Game.getInstance().getRunning();
                switch (arg.getKeyCode()){
                    case KeyEvent.VK_W:
                        wh.getPlayer(0).StartMove(Direction.Up);
                        break;
                    case KeyEvent.VK_A:
                        wh.getPlayer(0).StartMove(Direction.Left);
                        break;
                    case KeyEvent.VK_S:
                        wh.getPlayer(0).StartMove(Direction.Down);
                        break;
                    case KeyEvent.VK_D:
                        wh.getPlayer(0).StartMove(Direction.Right);
                        break;
                    case KeyEvent.VK_E:
                        wh.getPlayer(0).PlaceHoney(1.5);
                        break;
                    case KeyEvent.VK_Q:
                        wh.getPlayer(0).PlaceOil(0.5);
                        break;
                    case KeyEvent.VK_UP:
                        wh.getPlayer(1).StartMove(Direction.Up);
                        break;
                    case KeyEvent.VK_LEFT:
                        wh.getPlayer(1).StartMove(Direction.Left);
                        break;
                    case KeyEvent.VK_RIGHT:
                        wh.getPlayer(1).StartMove(Direction.Right);
                        break;
                    case KeyEvent.VK_DOWN:
                        wh.getPlayer(1).StartMove(Direction.Down);
                        break;
                    case KeyEvent.VK_NUMPAD1:
                        wh.getPlayer(1).PlaceHoney(1.5);
                        break;
                    case KeyEvent.VK_NUMPAD2:
                        wh.getPlayer(1).PlaceOil(0.5);
                        break;
                }


                //////////////////////////////////////////////////////////////////////
                if (Game.getInstance().getEndGame()){
                    JPanel endpanel = new JPanel();
                    endpanel.setBackground(new Color(0, 0, 0, 123));
                    add(endpanel);
                }
                //////////////////////////////////////////////////////////////////////

                wh.DrawMap();
                doReinvalidateRepaint();
            }
        });
    }

    /**
     * A játék paneljének jelezzük, elévült a tartalma, frissítsen
     */
    public void doReinvalidateRepaint()
    {
        labelGame.revalidate();
        labelGame.repaint();
    }

    /**
     * Field Textúrát beállító függvény
     * @param x Field sora
     * @param y Field oszlopa
     * @param path  Field elérhetősége a HashMap-ben (String kulcs)
     */
    public void SetTextureField(int x, int y, String path)
    {
        texture=fieldImages.get(path);
        fieldTextures[x][y] = texture;
    }
    /**
     * Thing Textúrát beállító függvény
     * @param x Thing sora
     * @param y Thing oszlopa
     * @param path  Thing elérhetősége a HashMap-ben (String kulcs)
     */
    public void SetTextureThing(int x, int y, String path)
    {
        texture=fieldImages.get(path);
        thingTextures[x][y] = texture;
    }

    /**
     * BufferedImage típusú változók érték szerinti átadásához
     * @param bi Klónozandó BufferedImage
     * @return bemeneti paraméter értékével megegyező új BufferedImage példány
     */
    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    /**
     * Átszínezést megvalósító metódus
     * @param c Szín szerint újraszínezés
     * @param texture   A kép, amin dolgozunk
     * @return  Újraszínezett kép
     */
    public BufferedImage Repaint(Colours c, BufferedImage texture)
    {
        for (int col = 0; col < texture.getWidth(); col++) {
            for (int row = 0; row < texture.getHeight(); row++) {
                Color color = new Color(texture.getRGB(col, row));
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                int r_a = r * c.getRed() / 255;
                int r_b = g * c.getGreen() / 255;
                int r_c = b * c.getBlue() / 255;

                texture.setRGB(col, row, new Color(r_a, r_b, r_c).getRGB());
            }
        }
        return texture;
    }

    /**
     * ColouredField textúráját beállító függvény
     * @param x ColouredField sora
     * @param y ColouredField oszlopa
     * @param path  ColouredField elérése, ami a HashMap-en String kulcs
     * @param c ColouredField színe
     */
    public void SetTextureField(int x, int y, String path, Colours c) {
        texture= deepCopy(fieldImages.get(path));//OK
        texture = Repaint(c,texture);
        fieldTextures[x][y] = texture;
    }

    /**
     * ColouredBox textúráját beállító függvény
     * @param x ColouredBox sora
     * @param y ColouredBox oszlopa
     * @param path  ColouredBox elérése, ami a HashMap-en String kulcs
     * @param c ColouredBox színe
     */
        public void SetTextureThing(int x, int y, String path, Colours c) {
            texture= deepCopy(fieldImages.get(path));
            texture = Repaint(c,texture);
            thingTextures[x][y] = texture;
    }
}
