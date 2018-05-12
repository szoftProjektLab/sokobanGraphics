package display;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JLabel;

public class GameFrame extends JFrame {

    private JPanel contentPane;
    private String globalVariable = null;
    private BufferedImage texture = null;
    private JPanel mainPanel;
    private JPanel labelGame;
    int x, y;


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
        setBounds(100, 100, 600, 620);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

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
                if(texture == null)
                    return;

                g.drawImage(texture, x, y,size.width, size.height,x, y, texture.getWidth(), texture.getHeight(), null);
            }
        };
        contentPane.add(labelGame, BorderLayout.CENTER);
        labelGame.setLayout(new GridLayout(1, 0, 0, 0));
    }

    public void SetTexture(int x, int y, String path)
    {
        this.x = x;
        this.y = y;
        this.globalVariable = path;
        labelGame.revalidate();
        labelGame.repaint();
    }

}