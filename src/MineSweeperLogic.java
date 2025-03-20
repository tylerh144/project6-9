import java.util.Scanner;

public class MineSweeperLogic {
    private Space[][] board;
    private int coins;
    private int totalMines;
    private int dimensions;

    public MineSweeperLogic (int d) {
        coins = 0;
        totalMines = 0;
        dimensions = d;
    }

    public int getTotalMines() {
        return totalMines;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }

    public void startGame () {
        System.out.println("Game starting");
        setBoard();
        printBoard();
    }

    private void setBoard () {
        totalMines = (int) (Math.pow(dimensions, 2.33) / 10 + 1);
        board = new Space[dimensions+2][dimensions+2];

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                board[r][c] = new Space();
            }
        }

        int mines = totalMines;

        while (mines > 0) {
            int r = (int) (Math.random() * dimensions) + 1;
            int c = (int) (Math.random() * dimensions) + 1;

            if (!(board[r][c] instanceof Mine)) {
                //place mine
                board[r][c] = new Mine();
                mines--;
                //increase num near for adj tiles
                for (int i = r-1; i <= r+1; i++) {
                    for (int j = c-1; j <= c+1; j++) {
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
        System.out.println("Mines: " + totalMines);
        System.out.println("Tiles: " + dimensions * dimensions);
        System.out.println("Density: " + (double) totalMines / dimensions / dimensions);
    }
}