package programming_task.pattern_recognition.model;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Point {
    @NotNull(message = "The coordinate x cannot be null.")
    private final Double x;
    @NotNull(message = "The coordinate y cannot be null.")
    private final Double y;

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public String toString() {
        return "{x: " + x + ", y: " + y + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x.equals(point.x) &&
                y.equals(point.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
