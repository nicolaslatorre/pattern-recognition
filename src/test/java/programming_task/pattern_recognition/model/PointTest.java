package programming_task.pattern_recognition.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void createPointTest() {
        Double x = 2.0;
        Double y = 3.0;

        Point point = new Point(x, y);

        assertEquals(x, point.getX());
        assertEquals(y, point.getY());
    }

    @Test
    public void equalsPointsTest() {
        Double x = 1.0;
        Double y = 4.0;

        Point point1 = new Point(x, y);
        Point point2 = new Point(x, y);

        assertTrue(point1.equals(point2));
    }

    @Test
    public void equalsPointsCreatedWithIntAndDoubleCoordinateTest() {
        Double x1 = Double.valueOf(1);
        Double y1 = Double.valueOf(4);

        Double x2 = 1.0;
        Double y2 = 4.0;

        Point point1 = new Point(x1, y1);
        Point point2 = new Point(x2, y2);

        assertTrue(point1.equals(point2));
    }


    @Test
    public void notEqualPointsTest() {
        Double x1 = 2.0;
        Double y1 = 3.0;

        Double x2 = 1.5;
        Double y2 = 4.0;

        Point point1 = new Point(x1, y1);
        Point point2 = new Point(x2, y2);

        assertFalse(point1.equals(point2));

    }
}