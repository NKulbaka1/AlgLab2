import algorithms.BruteForceSolution;
import algorithms.MapSolution;
import algorithms.PersistentSegmentTreeSolution;
import models.Point;
import models.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void preprocessingTest() {
        System.out.println("Preprocessing test");

        System.out.println("\nMap solution: ");
        for (int i = 0; i <= 1000; i+= 50) {
            List<Rectangle> rectangles = generateRectangles(i);

            long start = System.nanoTime();
            MapSolution mapSolution = new MapSolution(rectangles);
            long finish = System.nanoTime();

            System.out.println(finish - start);
        }

        System.out.println("\nPersistent segment tree solution: ");
        for (int i = 0; i <= 1000; i+= 50) {
            List<Rectangle> rectangles = generateRectangles(i);

            long start = System.nanoTime();
            PersistentSegmentTreeSolution persistentSegmentTreeSolution = new PersistentSegmentTreeSolution(rectangles);
            long finish = System.nanoTime();

            System.out.println(finish - start);
        }
    }

    public static void queriesTest() {
        System.out.println("\nQueries test");

        System.out.println("\nBrute force solution: ");
        for (int i = 0; i <= 1000; i+= 50) {
            List<Rectangle> rectangles = generateRectangles(i);
            List<Point> points = generatePoints(i);

            BruteForceSolution bruteForceSolution = new BruteForceSolution(rectangles);

            long start = System.nanoTime();
            for (Point point : points) {
                bruteForceSolution.count(point);
            }
            long finish = System.nanoTime();

            System.out.println(finish - start);
        }

        System.out.println("\nMap solution: ");
        for (int i = 0; i <= 1000; i+= 50) {
            List<Rectangle> rectangles = generateRectangles(i);
            List<Point> points = generatePoints(i);

            MapSolution mapSolution = new MapSolution(rectangles);

            long start = System.nanoTime();
            for (Point point : points) {
                mapSolution.count(point);
            }
            long finish = System.nanoTime();

            System.out.println(finish - start);
        }

        System.out.println("\nPersistent segment tree solution: ");
        for (int i = 0; i <= 1000; i+= 50) {
            List<Rectangle> rectangles = generateRectangles(i);
            List<Point> points = generatePoints(i);

            PersistentSegmentTreeSolution persistentSegmentTreeSolution = new PersistentSegmentTreeSolution(rectangles);

            long start = System.nanoTime();
            for (Point point : points) {
                persistentSegmentTreeSolution.count(point);
            }
            long finish = System.nanoTime();

            System.out.println(finish - start);
        }
    }

    private static List<Rectangle> generateRectangles(int n) {
        List<Rectangle> rectangles = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            rectangles.add(new Rectangle(10 * i, 10 * i, 10 * (2 * n - i), 10 * (2 * n - i)));
        }

        return rectangles;
    }

    private static List<Point> generatePoints(int n) {
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            points.add(new Point((int) ((Math.pow(101 * i, 31) % (20 * n))), (int) ((Math.pow(103 * i, 31) % (20 * n)))));
        }

        return points;
    }
}
