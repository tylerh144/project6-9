public class Mine extends Space {
    public Mine () {
    }

    @Override
    public void dig () {
        setFaceVal("[X]");
//        System.out.println("The field exploded");
    }

    @Override
    public void increaseNum() {
    }
}
