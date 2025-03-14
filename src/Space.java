public class Space {
    private String faceVal;
    private String val;

    public Space (String val) {
        faceVal = "[ ]";
        this.val = val;
    }

    public String getFaceVal() {
        return faceVal;
    }

    public String getVal() {
        return val;
    }

    public void dig () {
        faceVal = val;
    }
}
