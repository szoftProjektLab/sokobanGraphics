package display;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class GameFrame extends JFrame {

    private JPanel contentPane;

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

        JPanel mainPanel = new JPanel();
        contentPane.add(mainPanel, BorderLayout.NORTH);
        mainPanel.setLayout(new BorderLayout(0, 0));

        JLabel labelPlayer1 = new JLabel("<html>Player 1<br/>Score</html>");
        mainPanel.add(labelPlayer1,BorderLayout.WEST);


        JLabel labelPlayer2 = new JLabel("<html>Player 2<br/>Score</html>");
        mainPanel.add(labelPlayer2,BorderLayout.EAST);

        JPanel labelGame = new JPanel();
        contentPane.add(labelGame, BorderLayout.CENTER);
        labelGame.setLayout(new GridLayout(1, 0, 0, 0));
    }

}
