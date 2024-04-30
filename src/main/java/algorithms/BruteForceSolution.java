package algorithms;

import models.Point;
import models.Rectangle;

import java.util.List;

public class BruteForceSolution {
    List<Rectangle> rectangles;

    public BruteForceSolution(List<Rectangle> rectangles) {
        this.rectangles = rectangles;
    }

    public int count(Point point) {
        int result = 0;

        for (Rectangle rectangle : rectangles) {
            if (point.x >= rectangle.x1 && point.x < rectangle.x2 && point.y >= rectangle.y1 && point.y < rectangle.y2)
                result++;
        }

        return result;
    }
}
