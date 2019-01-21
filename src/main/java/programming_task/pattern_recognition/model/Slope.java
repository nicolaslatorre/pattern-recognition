package programming_task.pattern_recognition.model;

import java.util.Objects;

public abstract class Slope {
    protected Double slope;

    public static Slope from(Point start, Point end) {
        Double numerator = (end.getY() - start.getY());
        Double denominator = (end.getX() - start.getX());

        Double slope = (numerator / denominator);
        if (slope.isInfinite()) {
            return new VerticalSlope();
        }

        return new ValidSlope(slope);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Slope)) return false;
        Slope slope1 = (Slope) o;
        return slope.equals(slope1.slope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slope);
    }
}
