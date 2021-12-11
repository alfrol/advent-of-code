package ee.alfrol.model;

public record Point(int x, int y) {

    public boolean isBetween(Point a, Point b) {
        return isComponentBetween(x, a.x, b.x) && isComponentBetween(y, a.y, b.y);
    }

    private boolean isComponentBetween(int component, int a, int b) {
        return Math.min(a, b) <= component && component <= Math.max(a, b);
    }
}
