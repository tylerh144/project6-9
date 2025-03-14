public class MineSweeperLogic {
    private Space[][] board;
    private int coins;

    public MineSweeperLogic () {
        coins = 0;
    }

    public void startGame () {
        System.out.println("Game starting");
    }

    public void printBoard () {
        for (Space[] r : board) {
            for (Space c : r) {
                System.out.print(c.getFaceVal());
            }
            System.out.println();
        }
    }
}
