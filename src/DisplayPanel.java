import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Font;

public class DisplayPanel extends JPanel implements  MouseListener, ActionListener {
//    private int rectX;
//    private int rectY;
//    private Rectangle rect1;
//    private int rect2X;
//    private int rect2Y;
//    private Rectangle rect2;
//    private Color rectColor;

    private String timeLeft;
    private String boardStats;
    private double time;
    private int dimensions;

    private JTextField textField;
    private JButton setDim;
    private JButton start;

    private boolean gameOver;

    private MineSweeperLogic logic;

    public DisplayPanel() {
//        rectX = 50;
//        rectY = 30;
//        rect1 = new Rectangle(70, 30);
//        rect2X = 230;
//        rect2Y = 5;
//        rect2 = new Rectangle(20, 20);
//        rectColor = Color.RED;
        logic = new MineSweeperLogic(10);

        setDim = new JButton("Submit");
        setDim.addActionListener(this);
        add(setDim);

        start = new JButton("Start Game");
        start.addActionListener(this);
        add(start);

        gameOver = true;
        dimensions = 10;
        timeLeft = "Time: " + time + "s";

        // ticks every 100ms
        Timer timer = new Timer(100, this);
        timer.start();

        textField = new JTextField(10);
        add(textField);

        addMouseListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

//        rect1.setLocation(rectX, rectY);
//        g2d.setStroke(new BasicStroke(3));
//        g2d.setColor(rectColor);
//        g2d.draw(rect1);
//
//        rect2.setLocation(rect2X, rect2Y);
//        g2d.setStroke(new BasicStroke(2));
//        g2d.setColor(Color.BLUE);
//        g2d.draw(rect2);
//
//        // can use the intersects method on a Rectangle object
//        // to determine if one Rectangle object touches another Rectangle object
//        // (could alternatively use contains if you want to check if
//        // one Rectangle entirely contains another within its boundaries)
//        if (rect1.intersects(rect2)) {
//            rectColor = Color.MAGENTA;
//        }


        start.setLocation(200, 200);
        textField.setLocation(350, 400);
        setDim.setLocation(350, 425);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.setColor(Color.BLACK);

        if (gameOver) {
            add(start);
            add(textField);
            add(setDim);
        } else {
            g2d.drawString(timeLeft, 50, 50);
            boardStats = "Mines remaining: ";
            g2d.drawString(boardStats, 50, 100);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {
//        if (e.getButton() == MouseEvent.BUTTON1) {
//            Point mouseClickLocation = e.getPoint();
//            message = "mouse click: (" + mouseClickLocation.getX() + ", " + mouseClickLocation.getY() + ")";
//            if (rect1.contains(mouseClickLocation)) {
//                rectColor = Color.GREEN;
//            } else {
//                rectColor = Color.RED;
//            }
//
//            repaint();
//        }
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer) {
            time-=0.1;

            timeLeft = "Time: " + Math.round(time*10)/10.0 + "s";

            if (time < 0) {
                timeLeft = "Game over";
                gameOver = true;
            }
            repaint();
        } else if (e.getSource() instanceof JButton) {
                JButton casted = (JButton) e.getSource();
                if (casted == setDim) {
                    String input = textField.getText();
                    if (input.matches("[0-9]+")) {
                        if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= 30) {
                            dimensions = Integer.parseInt(textField.getText());
                        } else {
                            dimensions = 10;
                        }
                    } else {
                        dimensions = 10;
                    }
                    System.out.println(dimensions);
                    repaint();
                }

                if (casted == start) {
                    gameOver = false;
                    time = dimensions*dimensions*2;
                    remove(start);
                    remove(textField);
                    remove(setDim);
                    logic.setDimensions(dimensions);
                    logic.startGame();
                    repaint();
                }
        }
    }
}