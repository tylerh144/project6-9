import java.util.Scanner;

public class MineSweeperLogic {
    private Space[][] board;
    private int coins;

    public MineSweeperLogic () {
        coins = 0;
    }

    public void startGame () {
        System.out.println("Game starting");
        setBoard(20);
        printBoard();
    }

    private void setBoard (int dimensions) {
        board = new Space[dimensions+2][dimensions+2];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                board[r][c] = new Space();
            }
        }

        int mines = dimensions^2 / 5;

        while (mines > 0) {
            int r = (int) (Math.random() * dimensions) + 1;
            int c = (int) (Math.random() * dimensions) + 1;

            if (!(board[r][c] instanceof Mine)) {
                //place mine
                board[r][c] = new Mine();
                mines--;
                //increase num near for adj tiles
                for (int i = r-1; i < r+2; i++) {
                    for (int j = c-1; j < c+2; j++) {
                        board[i][j].increaseNum();
                    }
                }
            }
        }

    }

    private void printBoard () {
        //prints without border
        for (int i = 1; i <= board.length-2; i++) {
            for (int j = 1; j <= board.length-2; j++) {
                board[i][j].dig();
                System.out.print(board[i][j].getFaceVal());
            }
            System.out.println();
        }
    }
}