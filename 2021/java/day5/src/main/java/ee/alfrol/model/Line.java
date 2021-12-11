package ee.alfrol.model;

public record Line(Point start, Point end) {

    public boolean containsPoint(Point point) {
        boolean isPointBetweenStartAndEnd = point.isBetween(start, end);
        return isDiagonal() ? isPointBetweenStartAndEnd && satisfiesLineEquation(point) : isPointBetweenStartAndEnd;
    }

    public boolean isHorizontal() {
        return start.y() == end.y();
    }

    public boolean isVertical() {
        return start.x() == end.x();
    }

    public boolean isDiagonal() {
        return !(isHorizontal() || isVertical());
    }

    private boolean satisfiesLineEquation(Point point) {
        double slope = ((double) (end.y() - start.y())) / (end.x() - start.x());
        double b = start.y() - (slope * start.x());
        return point.y() == ((int) (slope * point.x()) + b);
    }
}
