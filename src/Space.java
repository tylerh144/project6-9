import java.awt.*;

public class Space {
    private String faceVal;
    private int numNear;
    private int flagsNear;
    private boolean isFlagged;
    private boolean isDug;
    private Rectangle tile;

    public Space () {
        faceVal = "[ ]";
        numNear = 0;
        flagsNear = 0;
        isFlagged = false;
        isDug = false;
        tile = new Rectangle(30, 30);
    }

    public Rectangle getTile() {
        return tile;
    }

    public String getFaceVal() {
        return faceVal;
    }

    public int getFlagsNear() {
        return flagsNear;
    }

    public int getNumNear() {
        return numNear;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public boolean isDug() {
        return isDug;
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

    public void addFlags(int i) {
        flagsNear += i;
    }

    public void dig () {
        faceVal = " " + numNear + " ";
        isDug = true;
    }
}
