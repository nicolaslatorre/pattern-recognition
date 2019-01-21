package programming_task.pattern_recognition.model;

import java.util.Objects;
import java.util.Set;

public class Line {
    private final Set<Point> points;

    public Line(Set<Point> points) {
        this.points = points;
    }

    public Set<Point> getPoints() {
        return points;
    }

    public int getNumberOfPoints() {
        return points.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return points.equals(line.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }
}
