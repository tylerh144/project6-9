import java.awt.*;

public class Space {
    private String faceVal;
    private int numNear;
    private boolean isFlagged;
    private boolean isDug;
    private Rectangle tile;

    public Space () {
        faceVal = "[ ]";
        numNear = 0;
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

    public void dig () {
        faceVal = " " + numNear + " ";
        isDug = true;
    }
}
