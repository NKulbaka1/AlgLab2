package algorithms;

import models.Point;
import models.Rectangle;

import java.util.*;

public class MapSolution {
    int[][] map;

    List<Integer> zippedX;

    List<Integer> zippedY;

    public MapSolution(List<Rectangle> rectangles) {
        zipCoordinates(rectangles);

        map = new int[zippedX.size()][zippedY.size()];

        BruteForceSolution bruteForceSolution = new BruteForceSolution(rectangles);

        for (int i = 0; i < zippedX.size(); i++) {
            for (int j = 0; j < zippedY.size(); j++) {
                Point point = new Point(zippedX.get(i), zippedY.get(j));
                map[i][j] = bruteForceSolution.count(point);
            }
        }
    }

    private void zipCoordinates(List<Rectangle> rectangles) {
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
        int x = binarySearch(zippedX, point.x);
        int y = binarySearch(zippedY, point.y);

        if (x == -1 || y == -1)
            return 0;

        return map[x][y];
    }
}
