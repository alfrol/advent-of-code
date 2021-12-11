package ee.alfrol;

import ee.alfrol.model.Line;
import ee.alfrol.model.Point;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    private static final int MAX_COUNT_OF_OVERLAPPING_LINES = 2;


    public static void main(String[] args) {
        String input = FileUtils.readFile(Main.class, "/input.txt").orElseThrow();

        List<Line> lines = getLines(input);
        List<Line> horizontalAndVerticalLines =
                getLinesByCriteria(lines, line -> !line.isDiagonal());

        System.out.println(countPoints(horizontalAndVerticalLines));
        System.out.println(countPoints(lines));
    }

    public static int countPoints(List<Line> lines) {
        Point largestPoint = getLargestPoint(lines);
        return countPointsWhereAtLeastXLinesOverlap(lines, largestPoint);
    }

    public static List<Line> getLines(String input) {
        List<Line> lines = new ArrayList<>();

        for (String line : input.split("\n")) {
            String[] components = line.split(" -> ");
            Point[] points = new Point[2];

            for (int i = 0; i < 2; i++) {
                int[] coordinates = Arrays.stream(components[i].split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                points[i] = new Point(coordinates[0], coordinates[1]);
            }

            lines.add(new Line(points[0], points[1]));
        }

        return lines;
    }

    public static List<Line> getLinesByCriteria(List<Line> lines, Predicate<Line> criteria) {
        return lines.stream().filter(criteria).toList();
    }

    public static Point getLargestPoint(List<Line> lines) {
        int x = 0;
        int y = 0;

        for (Line line : lines) {
            if (line.start().x() > x) {
                x = line.start().x();
            } else if (line.end().x() > x) {
                x = line.end().x();
            }

            if (line.start().y() > y) {
                y = line.start().y();
            } else if (line.end().y() > y) {
                y = line.end().y();
            }
        }

        return new Point(x, y);
    }

    public static int countPointsWhereAtLeastXLinesOverlap(List<Line> lines, Point largestPoint) {
        int pointsCount = 0;

        for (int x = 0; x <= largestPoint.x(); x++) {
            for (int y = 0; y <= largestPoint.y(); y++) {
                if (checkPointHasAtLeastXOverlappingLines(lines, new Point(x, y))) {
                    pointsCount += 1;
                }
            }
        }

        return pointsCount;
    }

    private static boolean checkPointHasAtLeastXOverlappingLines(List<Line> lines, Point point) {
        int overlapsCount = 0;

        for (Line line : lines) {
            if (line.containsPoint(point)) {
                overlapsCount += 1;
            }

            if (overlapsCount >= MAX_COUNT_OF_OVERLAPPING_LINES) {
                return true;
            }
        }
        return false;
    }
}
