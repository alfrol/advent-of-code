package ee.alfrol.model;

import java.util.ArrayList;
import java.util.List;

public class Lanternfish {

    private static final int TIMER_RESET_VALUE = 6;
    private static final int CHILD_INITIAL_TIMER = TIMER_RESET_VALUE + 2;

    private int timer;
    private final List<Lanternfish> children = new ArrayList<>();

    public Lanternfish(int timer) {
        this.timer = timer;
    }

    public void simulate() {
        if (timer == 0) {
            timer = TIMER_RESET_VALUE;
            children.forEach(Lanternfish::simulate);
            children.add(new Lanternfish(CHILD_INITIAL_TIMER));
        } else {
            timer--;
            children.forEach(Lanternfish::simulate);
        }
    }

    public int countChildren() {
        return children.size() + children.stream().mapToInt(Lanternfish::countChildren).sum();
    }
}
