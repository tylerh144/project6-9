public class Mine extends Space {
    public Mine () {
    }

    @Override
    public void dig () {
        super.dig();
        setFaceVal("[X]");
        //for testing
        System.out.println("The field exploded");
    }
}
