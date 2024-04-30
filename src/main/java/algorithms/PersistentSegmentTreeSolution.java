package algorithms;

import models.Event;
import models.PersistentSegmentTree;
import models.Point;
import models.Rectangle;

import java.util.*;

public class PersistentSegmentTreeSolution {

    List<Integer> zippedX;

    List<Integer> zippedY;

    PersistentSegmentTree PST;

    public PersistentSegmentTreeSolution(List<Rectangle> rectangles) {
        if (rectangles.isEmpty())
            return;

        zipCoordinates(rectangles);

        List<Event> events = new ArrayList<>();

        for (Rectangle rectangle : rectangles) {
            events.add(new Event(binarySearch(zippedX, rectangle.x1), binarySearch(zippedY, rectangle.y1),
                    binarySearch(zippedY, rectangle.y2), 1));
            events.add(new Event(binarySearch(zippedX, rectangle.x2), binarySearch(zippedY, rectangle.y1),
                    binarySearch(zippedY, rectangle.y2), -1));
        }

        events.sort(Comparator.comparingInt((Event e) -> e.xNumber));

        PST = new PersistentSegmentTree(events, zippedY.size());
    }

    private void zipCoordinates(List<Rectangle> rectangles) {
        if (rectangles.isEmpty())
            return;

        Set<Integer> treeSetX = new TreeSet<>();
        Set<Integer> treeSetY = new TreeSet<>();

        for (Rectangle rectangle : rectangles) {
            treeSetX.add(rectangle.x1);
            treeSetX.add(rectangle.x2);
            treeSetY.add(rectangle.y1);
            treeSetY.add(rectangle.y2);
        }

        zippedX = new ArrayList<>(treeSetX);
        zippedY = new ArrayList<>(treeSetY);
    }

    private int binarySearch(List<Integer> arr, Integer target) {
        int l = 0;
        int r = arr.size();

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr.get(mid) <= target)
                l = mid + 1;
            else
                r = mid;
        }

        return l - 1;
    }

    public int count(Point point) {
        if (zippedX == null) {
            return 0;
        }

        int x = binarySearch(zippedX, point.x);
        int y = binarySearch(zippedY, point.y);

        if (x == -1 || y == -1 || PST.xNodes.size() <= x) {
            return 0;
        }

        Point newPoint = new Point(x, y);
        return PST.findInPST(newPoint);
    }
}
