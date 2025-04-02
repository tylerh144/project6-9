import javax.swing.JFrame;
import java.awt.*;

public class SampleFrame {

    public SampleFrame() {
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //can change this to panel.getSize()
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        DisplayPanel panel = new DisplayPanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}