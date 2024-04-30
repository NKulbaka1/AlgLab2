package models;

import java.util.ArrayList;
import java.util.List;

public class PersistentSegmentTree {
    public List<Node> xNodes = new ArrayList<>();

    public PersistentSegmentTree(List<Event> events, int size) {
        Node root = buildPST(new int[size], 0, size);

        int xNumber = events.get(0).xNumber;
        for (Event event : events) {
            if (xNumber != event.xNumber) {
                xNodes.add(root);
                xNumber = event.xNumber;
            }

            root = addNodeToPST(root, event.leftBorder, event.rightBorder, event.status);
        }
    }

    public Node buildPST(int[] array, int left, int right) {
        if (right - left == 1) {
            return new Node(null, null, left, right, array[left]);
        }

        int middle = (left + right) / 2;

        Node leftNode = buildPST(array, left, middle);
        Node rightNode = buildPST(array, middle, right);

        return new Node(leftNode, rightNode, leftNode.leftRange,
                rightNode.rightRange, leftNode.sum + rightNode.sum);
    }

    public Node addNodeToPST(Node root, int left, int right, int value) {
        if (left <= root.leftRange && right >= root.rightRange) {
            return new Node(root.left, root.right, root.leftRange, root.rightRange, root.sum + value);
        }

        if (root.leftRange >= right || root.rightRange <= left) {
            return root;
        }

        Node newRoot = new Node(root.left, root.right, root.leftRange, root.rightRange, root.sum);

        newRoot.left = addNodeToPST(newRoot.left, left, right, value);
        newRoot.right = addNodeToPST(newRoot.right, left, right, value);

        return newRoot;
    }

    public int findInPST(Point point) {
        return searchInPST(xNodes.get(point.x), point.y);
    }

    private int searchInPST(Node root, int number) {
        if (root == null)
            return 0;

        int middleValue = (root.leftRange + root.rightRange) / 2;
        if (number < middleValue)
            return root.sum + searchInPST(root.left, number);
        else
            return root.sum + searchInPST(root.right, number);
    }
}
