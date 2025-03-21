public class Space {
    private String faceVal;
    private int numNear;
    private boolean isFlagged;

    public Space () {
        faceVal = "[ ]";
        numNear = 0;
        isFlagged = false;
    }

    public String getFaceVal() {
        return faceVal;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void flipFlag() {
        isFlagged = !isFlagged;
    }

    public void setFaceVal(String faceVal) {
        this.faceVal = faceVal;
    }

    public void increaseNum() {
        numNear++;
    }

    public boolean dig () {
        if (!isFlagged) {
            faceVal = " " + numNear + " ";
            return true;
        }
        return false;
    }
}
