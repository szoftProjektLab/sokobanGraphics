package display;
import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import game.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class GameFrame extends JFrame {

    private JPanel contentPane;
    private String globalVariable = null;
    private BufferedImage texture = null;
    private JPanel mainPanel;
    private JPanel labelGame;
    private BufferedImage thingTextures[][];
    private BufferedImage fieldTextures[][];

    private int x, y;


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
     * Create the frame.
     */
    public GameFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 810);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        Warehouse wh = Game.getInstance().getRunning();
        thingTextures = new BufferedImage[wh.getRow()][wh.getColumn()];
        fieldTextures = new BufferedImage[wh.getRow()][wh.getColumn()];

        mainPanel = new JPanel();
        contentPane.add(mainPanel, BorderLayout.NORTH);
        mainPanel.setLayout(new BorderLayout(0, 0));

        JLabel labelPlayer1 = new JLabel("<html>Player 1<br/>Score</html>");
        mainPanel.add(labelPlayer1,BorderLayout.WEST);


        JLabel labelPlayer2 = new JLabel("<html>Player 2<br/>Score</html>");
        mainPanel.add(labelPlayer2,BorderLayout.EAST);

        labelGame = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension size = getSize();

                //g.drawImage(texture,x,y,null);
                Warehouse wh = Game.getInstance().getRunning();

                int width = getWidth();
                int height = getHeight();


                for(int i=0;i<wh.getRow();i++){
                    for(int j=0;j<wh.getColumn();j++){
                        texture = fieldTextures[i][j];

                        g.drawImage(texture, (57*j)*getWidth()/574, (55*i)*getHeight()/539,(57*j+57)*getWidth()/574, (55*i+55)*getHeight()/539,0,0, 120, 120, null);
                        if(wh.getField(i,j).getThing()!=null) {
                            texture = thingTextures[i][j];
                            g.drawImage(texture, (57*j)*getWidth()/574, (55*i)*getHeight()/539,(57*j+57)*getWidth()/574, (55*i+55)*getHeight()/539,0,0, 120, 120, null);
                        }
                    }
                }
                //g.drawImage(texture, 0, 0,size.width, size.height,x,y, 60, 60, null);
            }
        };
        contentPane.add(labelGame, BorderLayout.CENTER);
        labelGame.setLayout(new GridLayout(1, 0, 0, 0));
    }
    public void doReinvalidateRepaint()
    {
        //texture = loadImage();

        //texture = loadImage();
        labelGame.revalidate();
        labelGame.repaint();
    }

    public void SetTextureField(int x, int y, String path)
    {
        this.x = x;
        this.y = y;
        this.globalVariable = path;
        texture = loadImage();
        fieldTextures[x][y] = texture;
    }

    public void SetTextureThing(int x, int y, String path)
    {
        this.x = x;
        this.y = y;
        this.globalVariable = path;
        texture = loadImage();
        thingTextures[x][y] = texture;
    }

    public void SetTextureField(int x, int y, String path, Colours c) {
        this.x = x;
        this.y = y;
        this.globalVariable = path;
        texture = loadImage();

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
        fieldTextures[x][y] = texture;
    }


        public void SetTextureThing(int x, int y, String path, Colours c) {
            this.x = x;
            this.y = y;
            this.globalVariable = path;
            texture = loadImage();

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
            thingTextures[x][y] = texture;
    }
}
