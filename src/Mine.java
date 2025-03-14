public class Mine extends Space {
    public Mine () {
        super("[X]");
    }

    @Override
    public void dig () {
        super.dig();
        System.out.println("The field exploded");
    }
}
