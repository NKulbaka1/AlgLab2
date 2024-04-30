package models;

public class Node {

    public Node left;
    public Node right;
    public int leftRange;
    public int rightRange;
    public int sum;

    public Node(Node left, Node right, int leftRange, int rightRange, int sum) {
        this.left = left;
        this.right = right;
        this.rightRange = rightRange;
        this.leftRange = leftRange;
        this.sum = sum;
    }
}
