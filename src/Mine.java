public class Mine extends Space {
    public Mine () {
    }

    @Override
    public boolean dig () {
        if (!isFlagged()) {
            setFaceVal("[X]");
            //System.out.println("The field exploded");
            return true;
        }
        return false;

    }

    //maybe delete
    @Override
    public void increaseNum() {
    }
}
