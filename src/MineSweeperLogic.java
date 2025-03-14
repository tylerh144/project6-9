import java.util.Scanner;

public class MineSweeperLogic {
    private Space[][] board;
    private int coins;
    private Scanner scan;

    public MineSweeperLogic () {
        coins = 0;
        scan = new Scanner(System.in);
    }

    public void startGame () {
        System.out.println("Game starting");
    }

    private void setBoard (int dimensions) {
        board = new Space[dimensions+2][dimensions+2];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                //borders
                if (r == 0 || r == board.length-1 || c == 0 || c == board.length-1) {
                    board[r][c] = new Border();
                }
            }
        }

        //ask for first move
        //place mines
        //set each space's number
    }

    //mines = dimensions^2 * some number (random?)

    private void printBoard () {
        for (Space[] r : board) {
            for (Space c : r) {
                System.out.print(c.getFaceVal());
            }
            System.out.println();
        }
    }
}