import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DisplayPanel extends JPanel implements  MouseListener, ActionListener {
    private int rectX;
    private int rectY;
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
    private JButton back;
    private Timer timer;
    private BufferedImage title;

    private boolean gameOver;
    private boolean menu;

    private MineSweeperLogic logic;

    public DisplayPanel() {
        rectX = 80;
        rectY = 120;

        logic = new MineSweeperLogic(10);

        setDim = new JButton("Set Dimensions");
        setDim.addActionListener(this);
        add(setDim);

        start = new JButton("Start Game");
        start.addActionListener(this);
        add(start);

        back = new JButton("Back");
        back.addActionListener(this);

        gameOver = true;
        menu = true;
        dimensions = 10;
        timeLeft = "Time: " + time + "s";

        // ticks every 100ms
        timer = new Timer(100, this);
        timer.start();

        textField = new JTextField(10);
        add(textField);

        try {
            title = ImageIO.read(new File("src\\title.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        addMouseListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        start.setLocation(200, 400);
        textField.setLocation(350, 400);
        setDim.setLocation(350, 425);
        back.setLocation(0, 0);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.setColor(Color.BLACK);

        if (menu) {
                add(start);
                add(textField);
                add(setDim);
                g.drawImage(title, 0, 150, null);
        } else {
            g2d.drawString(timeLeft, 50, 50);
            boardStats = "Mines remaining: ";
            g2d.drawString(boardStats, 50, 100);

            for (int i = 1; i <= dimensions; i++) {
                for (int j = 1; j <= dimensions; j++) {
                    Rectangle tile = new Rectangle(20, 20);
                    tile.setLocation(rectX, rectY);
                    g2d.draw(tile);
                    rectX+=20;
                }
                rectY+=20;
                rectX = 80;
            }
            rectX = 80;
            rectY = 120;
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
                timer.stop();
                if (!menu) {
                    add(back);
                    validate();
                }
            }
            repaint();
        } else if (e.getSource() instanceof JButton) {
                JButton casted = (JButton) e.getSource();

                //set dimensions
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

                //start game
                if (casted == start) {
                    gameOver = false;
                    menu = false;
                    time = dimensions*dimensions*2;
                    removeAll();
                    timer.start();
                    logic.startGame(dimensions);
                    repaint();
                }

                //back to menu
                if (casted == back) {
                    menu = true;
                    remove(back);
                    repaint();
                }
        }
    }
}