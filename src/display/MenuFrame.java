package display;
import game.Game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JList;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;

public class MenuFrame extends JFrame{

    private String globalVariable = null;
    private BufferedImage background = null;
    private static GameFrame activeGameFrame;
    private JFrame frmMenu;

    /**
     * Create the application.
     */
    public MenuFrame() {
        initialize();
    }

    private BufferedImage loadImage(){
        if(globalVariable == null)
            return null;
        BufferedImage result = null;

        try {
            result = ImageIO.read(new File("Maps//"+globalVariable+".jpg"));
        } catch (IOException e) {
            System.out.println("Nem található a kiválasztott pálya!");
        }
        return result;
    }

    public static GameFrame getActiveGameFrame()
    {
        return activeGameFrame;
    }
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setTitle("Menu");
        setResizable(false);
        setBounds(100, 100, 600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(null);
        getContentPane().add(mainPanel);

        JButton btnExit = new JButton();
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

        JButton btnStart = new JButton("Start");
        btnStart.setBounds(309, 312, 66, 23);
        mainPanel.add(btnStart);

        btnStart.setActionCommand("btnRemove");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("btnRemove")) {
                    Game g = Game.getInstance();
                    g.StartGame("Maps//"+globalVariable+".txt");
                }
            }
        });

        JList<String> list = new JList<String>();
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));

        DefaultListModel<String> model = new DefaultListModel<>();
        for (int i = 1; i < 100; i++)
            model.addElement("Map"+i);
        list.setModel(model);


        listScroller.setBounds(225, 100, 150, 200);
        mainPanel.add(listScroller);

        JLabel labelMenu = new JLabel("Menu");
        labelMenu.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        labelMenu.setBounds(275, 65, 66, 30);
        mainPanel.add(labelMenu);

        JPanel backgroundPanel = new JPanel() {
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
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {

                    globalVariable =  list.getSelectedValue().toString();
                    background = loadImage();
                    backgroundPanel.revalidate();
                    backgroundPanel.repaint();
                    mainPanel.revalidate();
                    mainPanel.repaint();
                    //labelMenu.setText("Maps//"+list.getSelectedValue().toString());
                }
            }
        });

    }
}
