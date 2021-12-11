package ee.alfrol.model;

public class Line {

    private static final String DIRECTION_LR = "left_to_right";
    private static final String DIRECTION_RL = "right_to_left";
    private static final String DIRECTION_TB = "top_to_bottom";
    private static final String DIRECTION_BT = "bottom_to_top";

    private final Point start;
    private final Point end;
    private final String direction;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.direction = determineLineDirection();
    }

    public boolean containsPoint(Point point) {
        if (DIRECTION_LR.equals(direction) || DIRECTION_TB.equals(direction)) {
            return isPointInBounds(start, end, point);
        }
        return isPointInBounds(end, start, point);
    }

    public boolean isHorizontal() {
        return start.y() == end.y();
    }

    public boolean isVertical() {
        return start.x() == end.x();
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    private String determineLineDirection() {
        if (isHorizontal()) {
            return start.x() < end.x() ? DIRECTION_LR : DIRECTION_RL;
        }
        return start.y() < end.y() ? DIRECTION_TB : DIRECTION_BT;
    }

    private boolean isPointInBounds(Point from, Point to, Point point) {
        return from.x() <= point.x() && to.x() >= point.x() && from.y() <= point.y() && to.y() >= point.y();
    }
}
