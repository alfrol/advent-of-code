package ee.alfrol;

import ee.alfrol.model.Line;
import ee.alfrol.model.Point;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        String input = FileUtils.readFile(Main.class, "/input.txt").orElseThrow();

        List<Line> lines = getLines(input);
        List<Line> horizontalAndVerticalLines = getLinesByCriteria(lines, line -> line.isVertical() || line.isHorizontal());
        Point largestPoint = getLargestPoint(lines);
        int overlappingPointsCount = getOverlappingPointsCount(horizontalAndVerticalLines, largestPoint);

        System.out.println(overlappingPointsCount);
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
            if (line.getStart().x() > x) {
                x = line.getStart().x();
            } else if (line.getEnd().x() > x) {
                x = line.getEnd().x();
            }
            if (line.getStart().y() > y) {
                y = line.getStart().y();
            } else if (line.getEnd().y() > y) {
                y = line.getEnd().y();
            }
        }

        return new Point(x, y);
    }

    public static int getOverlappingPointsCount(List<Line> lines, Point largestPoint) {
        final int maxOverlapsCount = 1;
        int overlappingPointsCount = 0;

        for (int x = 0; x <= largestPoint.x(); x++) {
            for (int y = 0; y <= largestPoint.y(); y++) {
                Point point = new Point(x, y);
                int overlapsCount = 0;

                for (Line line : lines) {
                    if (line.containsPoint(point)) {
                        overlapsCount += 1;
                    }

                    if (overlapsCount > maxOverlapsCount) {
                        overlappingPointsCount += 1;
                        break;
                    }
                }
            }
        }

        return overlappingPointsCount;
    }
}
