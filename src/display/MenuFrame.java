package display;
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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;

public class MenuFrame extends JFrame{

    String globalVariable = null;
    BufferedImage background = null;
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
            result = ImageIO.read(new File(globalVariable));
        } catch (IOException e) {
            System.out.println("Rossz volt!");
        }
        return result;
    }
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setTitle("Menu");
        setResizable(false);
        setBounds(100, 100, 600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(null);
        getContentPane().add(panel);

        JButton component = new JButton();
        component.setText("Exit");

        component.setBounds(225, 311, 66, 24);
        panel.add(component);

        JButton btnNewButton = new JButton("Start");
        btnNewButton.setBounds(309, 312, 66, 23);
        panel.add(btnNewButton);

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
        panel.add(listScroller);

        JLabel lblMenu = new JLabel("Menu");
        lblMenu.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        lblMenu.setBounds(275, 65, 66, 30);
        panel.add(lblMenu);

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
        panel.add(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout(0, 0));

        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    globalVariable =  "bHDIt_jgZBISepXSqY5TMgkc3D1kPvraHuw0w308zU0.png";
                    background = loadImage();
                    backgroundPanel.revalidate();
                    backgroundPanel.repaint();
                    panel.repaint();
                    lblMenu.setText(list.getSelectedValue().toString());
                }
            }
        });

    }
}
