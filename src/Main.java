import display.*;
import display.MenuFrame;
import java.awt.EventQueue;
import things.*;


public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                        MenuFrame window = new MenuFrame();
                        window.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
