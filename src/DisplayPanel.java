import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class DisplayPanel extends JPanel implements  MouseListener, ActionListener {
    private int x;
    private int y;

    private String timeLeft;
    private double time;
    private int dimensions;

    private JTextField textField;
    private JButton setDim;
    private JButton start;
    private JButton back;
    private Timer timer;
    private BufferedImage title;
    private BufferedImage flag;
    private BufferedImage boom;
    private BufferedImage win;

    private boolean gameOver;
    private boolean menu;
    private int spacesDug;
    private int totalMines;
    private int flags;

    private MineSweeperLogic logic;

    public DisplayPanel() {
        x = 80;
        y = 120;

        setSize(500, 500);

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
            flag = ImageIO.read(new File("src\\flag.png"));
            boom = ImageIO.read(new File("src\\boom.png"));
            win = ImageIO.read(new File("src\\star.png"));
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

            if (gameOver && spacesDug == dimensions * dimensions - totalMines) {
                if (dimensions <= 10) {
                    g2d.drawImage(win, 0, 0, 500, 500, null);
                } else {
                    g2d.drawImage(win, 0, 0, dimensions*30+100, dimensions*30+100, null);
                }
            }

            g2d.drawString("Flags left: " + flags, 50, 100);
            ArrayList<Space> booms = logic.getBooms();

            for (int i = 1; i <= dimensions; i++) {
                for (int j = 1; j <= dimensions; j++) {
                    Space s = logic.getBoard()[i][j];
                    Rectangle tile = s.getTile();

                    if (s.isFlagged()) {
                        g.setColor(Color.decode("#7eed39"));
                        g2d.drawString(s.getFaceVal(), x + 5, y + 20);
                        g.setColor(Color.BLACK);
                        g2d.drawImage(flag, x, y, null);
                    } else {
                        int numNear = s.getNumNear();
                        g.setColor(Color.decode("#7eed39"));
                        if (s.getFaceVal().equals(" " + numNear + " ")) {
                            if (numNear == 0) {
                                g.setColor(Color.WHITE);
                            } else if (numNear == 1) {
                                g.setColor(Color.BLUE);
                            } else if (numNear == 2) {
                                g.setColor(Color.decode("#32a852"));
                            } else if (numNear == 3) {
                                g.setColor(Color.RED);
                            } else if (numNear == 4) {
                                g.setColor(Color.decode("#8732a8"));
                            } else if (numNear == 5) {
                                g.setColor(Color.ORANGE);
                            } else if (numNear == 6) {
                                g.setColor(Color.CYAN);
                            } else if (numNear == 7) {
                                g.setColor(Color.decode("#4d000a"));
                            } else if (numNear == 8) {
                                g.setColor(Color.BLACK);
                            }
                        }
                        if (s instanceof Mine && gameOver) {
                            g.setColor(Color.BLACK);


                            if (booms.contains(s)) {
                                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .66f));
                                g2d.drawImage(boom, x - 130, y - 150, null);
                                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
                            }
                        }
                        g2d.drawString(s.getFaceVal(), x + 5, y + 20);
                        g.setColor(Color.BLACK);
                    }

                    tile.setLocation(x, y);
                    g2d.draw(tile);
                    x+=30;
                }
                y += 30;
                x = 80;
            }
            x = 80;
            y = 120;
            validate();
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point mouseClick = e.getPoint();
        if (!gameOver) {
            for (int i = 1; i <= dimensions; i++) {
                for (int j = 1; j <= dimensions; j++) {
                    Space s = logic.getBoard()[i][j];
                    if (s.getTile().contains(mouseClick)) {
                        if (e.getButton() == MouseEvent.BUTTON1) {

                            if (!s.isFlagged() && !s.isDug()) {
                                s.dig();
                                spacesDug++;
                                System.out.println("dig"); //for testing
                                if (s instanceof Mine) {
                                    gameOver = true;
                                    repaint();
                                }
                            } else if (s.isDug() && s.getFlagsNear() == s.getNumNear()) {
                                for (int ii = i-1; ii <= i+1; ii++) {
                                    for (int jj = j-1; jj <= j+1; jj++) {
                                        Space ss = logic.getBoard()[ii][jj];
                                        if (!ss.isFlagged() && !ss.isDug()) {
                                            ss.dig();
                                            if (ss instanceof Mine) {
                                                gameOver = true;
                                                repaint();
                                            } else {

                                                if (ii >= 1 && ii <= dimensions && jj >= 1 && jj <= dimensions) {
                                                    spacesDug++;
                                                    System.out.println("dig");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            //win
                            if (spacesDug == dimensions * dimensions - totalMines) {
                                System.out.println("winner"); //for testing
                                timer.stop();
                                timeLeft = "You win";
                                gameOver = true;
                                add(back);
                                validate();
                                repaint();
                            }
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            if (!s.isDug()) {
                                s.flipFlag();
                                if (s.isFlagged()) {
                                    flags--;
                                    for (int ii = i-1; ii <= i+1; ii++) {
                                        for (int jj = j-1; jj <= j+1; jj++) {
                                            logic.getBoard()[ii][jj].addFlags(1);
                                        }
                                    }
                                    System.out.println("added flags");
                                } else {
                                    flags++;
                                    for (int ii = i-1; ii <= i+1; ii++) {
                                        for (int jj = j-1; jj <= j+1; jj++) {
                                            logic.getBoard()[ii][jj].addFlags(-1);
                                        }
                                    }
                                    System.out.println("removed flags");
                                }
                                System.out.println("flip"); //for testing
                            }
                        }
                    }
                }
            }
        }
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

            if (gameOver) {
                time = -1;
            }

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
                    spacesDug = 0;
                    totalMines = logic.getTotalMines();
                    flags = totalMines;
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