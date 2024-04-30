package models;

public class Event {
    public int xNumber;
    public int leftBorder;
    public int rightBorder;
    public int status;

    public Event(int xNumber, int leftBorder, int rightBorder, int status) {
        this.xNumber = xNumber;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.status = status;
    }
}
