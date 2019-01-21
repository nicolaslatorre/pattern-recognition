package programming_task.pattern_recognition.model;

import org.junit.Before;
import org.junit.Test;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class LineTest {
    private Set<Point> point1;
    private Set<Point> point2;

    @Before
    public void setUp() throws Exception {
        Point a = new Point(2.0, 4.0);
        Point b = new Point(4.0, 6.0);
        Point c = new Point(6.0, 8.0);

        Point d = new Point(2.0, 3.0);
        Point e = new Point(1.0, 4.0);
        Point f = new Point(4.0, 1.0);

        point1 = new HashSet<>();
        point2 = new HashSet<>();

        point1.add(a);
        point1.add(b);
        point1.add(c);

        point2.add(d);
        point2.add(e);
        point2.add(f);
    }

    @Test
    public void createLineWithThreePointsTest() {
        Line line = new Line(point1);
        assertEquals(3, line.getPoints().size());
    }

    @Test
    public void equalsLinesTest() {
        Line line1 = new Line(point1);
        Line line2 = new Line(point1);

        assertTrue(line1.equals(line2));
    }

    @Test
    public void notEqualsLineTest() {
        Line line1 = new Line(point1);
        Line line2 = new Line(point2);

        assertFalse(line1.equals(line2));
    }

    //do test to create a line with less than two points that should throw an exception
}