public class Space {
    private String faceVal;
    private String val;
    private int numNear;

    public Space (String val) {
        faceVal = "[ ]";
        this.val = val;
        numNear = 0;
    }

    public String getFaceVal() {
        return faceVal;
    }

    public String getVal() {
        return val;
    }

    public void increaseNum() {
        numNear++;
    }

    public void dig () {
        faceVal = val;
    }
}
