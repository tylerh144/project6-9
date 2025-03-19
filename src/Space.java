public class Space {
    private String faceVal;
    private int numNear;

    public Space () {
        faceVal = "[ ]";
        numNear = 0;
    }

    public String getFaceVal() {
        return faceVal;
    }

    public void setFaceVal(String faceVal) {
        this.faceVal = faceVal;
    }

    public void increaseNum() {
        numNear++;
    }

    public void dig () {
        faceVal = " " + numNear + " ";
    }
}
