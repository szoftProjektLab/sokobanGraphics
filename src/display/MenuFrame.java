package display;
import game.Game;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListCellRenderer;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.event.ActionEvent;

public class MenuFrame extends JFrame{

    /**
     * Kép betöltésére használt string változó
     */
    private String globalVariable = null;

    /**
     * Háttérképet tároló BufferedImage
     */
    private BufferedImage background = null;

    /**
     *  Aktív játék ablakra referencia
     */
    private static GameFrame activeGameFrame;

    /**
     * Fő JPanel, amin a JList, és a háttérkép panele, egy JLabel illetve gombok vannak
     */
    private JPanel mainPanel;

    /**
     * Háttérkép JPanel-ja, ahova a háttérkép kerül
     */
    private JPanel backgroundPanel;

    /**
     * Lista, amiből a pályákat lehet kiválasztani
     */
    private JList<String> list;

    /**
     * Default model Listához felhasználva
     */
    private DefaultListModel<String> model;

    /**
     * Görgethetőséget megvalósító JScrollPane
     */
    private JScrollPane listScroller;

    /**
     * Szöveget kiíró JLabel
     */
    private JLabel labelMenu;

    /**
     * Start gomb
     */
    private JButton btnStart;

    /**
     * Exit gomb
     */
    private JButton btnExit;

    /**
     * Ablak létrehozása
     */
    public MenuFrame() {
        setTitle("Sokoban");
        Image im = Toolkit.getDefaultToolkit().getImage("textures/icon.jpg");
        setIconImage(im);
        setResizable(false);
        setBounds(100, 100, 600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(null);
        getContentPane().add(mainPanel);

        /* Default menü háttér ameddig nem választunk ki egy pályát a listából*/
        try {
            background = ImageIO.read(new File("textures/menubackground.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnExit = new JButton();
        btnExit.setText("Exit");

        btnExit.setActionCommand("btnExit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("btnExit"))
                    System.exit(0);
            }
        });

        btnExit.setBounds(225, 311, 66, 24);
        mainPanel.add(btnExit);

        btnStart = new JButton("Start");
        btnStart.setBounds(309, 312, 66, 23);
        mainPanel.add(btnStart);

        btnStart.setActionCommand("btnRemove");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("btnRemove")) {
                    Game g = Game.getInstance();
                    setVisible(false);
                    g.StartGame("Maps/"+globalVariable+".txt");
                    activeGameFrame = new GameFrame();
                    activeGameFrame.setVisible(true);
                    Game.getInstance().getRunning().DrawMap();
                }
            }
        });

        list = new JList<String>();
        list.setFont(new Font("Arial",Font.BOLD,16));
        list.setFixedCellHeight(40);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)list.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        list.setVisibleRowCount(-1);



        listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));

        listScroller.getViewport().getView().setBackground(new Color(151,205,255));
        listScroller.getViewport().getView().setForeground(Color.BLACK);

        model = new DefaultListModel<>();
        for (int i = 1; i < 6; i++)
            model.addElement("Map"+i);
        list.setModel(model);


        listScroller.setBounds(225, 100, 150, 200);
        listScroller.setBorder(BorderFactory.createEmptyBorder());
        listScroller.getViewport().setBackground(Color.RED);
        mainPanel.add(listScroller);

        labelMenu = new JLabel("Menu");
        labelMenu.setFont(new Font("Arial Black", Font.BOLD, 26));
        labelMenu.setForeground(Color.WHITE);
        labelMenu.setBounds(260, 65, 86, 30);
        mainPanel.add(labelMenu);

        backgroundPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension size = getSize();
                if(background == null)
                  return;
                g.drawImage(background, 0, 0,size.width, size.height,0, 0, background.getWidth(), background.getHeight(), null);
            }
        };

        backgroundPanel.setBounds(0, 0, 594, 471);
        mainPanel.add(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout(0, 0));


        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    globalVariable =  list.getSelectedValue().toString();
                    background = loadImage();
                    backgroundPanel.revalidate();
                    backgroundPanel.repaint();
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
        });
    }

    /**
     * Képbetöltéshez felhasznált metódus
     * @return betöltött kép
     */
    private BufferedImage loadImage(){
        if(globalVariable == null)
            return null;
        BufferedImage result = null;
        try {
            result = ImageIO.read(new File("Maps/"+globalVariable+".jpg"));
        } catch (IOException e) {
            System.out.println("Nem található a kiválasztott pálya!");
        }
        return result;
    }
    /**
     * Játékablakot visszaadó függvény
     * @return játékablak
     */
    public static GameFrame getActiveGameFrame()
    {
        return activeGameFrame;
    }
}
