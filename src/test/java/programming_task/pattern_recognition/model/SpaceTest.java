package programming_task.pattern_recognition.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

public class SpaceTest {
    private Space space;

    @Before
    public void setUp() throws Exception {
        space = new Space();
    }

    @Test
    public void emptySpaceTest() {
        Set<Point> points = space.getAllPoints();
        Set<Line> lines = space.getAllLines();

        assertEquals(0, points.size());
        assertEquals(0, lines.size());
    }

    @Test
    public void twoCollinearPointsTest() {
        Point a = new Point(2.0, 3.0);
        Point b = new Point(-2.0, 1023.0);
        Point c = new Point(3.2, 0.0);

        space.addPoint(a);
        space.addPoint(b);
        space.addPoint(c);

        Set<Line> lines = space.getCollinearPoints(2);
        assertEquals(3, lines.size());
    }

    @Test
    public void noThreeCollinearPointsTest() {
        Point a = new Point(2.0, 3.0);
        Point b = new Point(-2.0, 1023.0);
        Point c = new Point(3.2, 0.0);

        space.addPoint(a);
        space.addPoint(b);
        space.addPoint(c);

        Set<Line> lines = space.getCollinearPoints(3);
        assertEquals(0, lines.size());
    }



    @Test
    public void threeCollinearPointsTest() {
        Point a = new Point(2.0, 4.0);
        Point b = new Point(4.0, 6.0);
        Point c = new Point(6.0, 8.0);

        space.addPoint(a);
        space.addPoint(b);
        space.addPoint(c);

        Set<Line> lines = space.getCollinearPoints(3);
        assertEquals(1, lines.size());
    }

    @Test
    public void threeCollinearPointsTwoParallelLineTest() {
        Point a = new Point(2.0, 4.0);
        Point b = new Point(4.0, 6.0);
        Point c = new Point(6.0, 8.0);
        Point d = new Point(3.0, 4.0);
        Point e = new Point(5.0, 6.0);
        Point f = new Point(7.0, 8.0);

        space.addPoint(a);
        space.addPoint(b);
        space.addPoint(c);
        space.addPoint(d);
        space.addPoint(e);
        space.addPoint(f);

        Set<Line> lines = space.getCollinearPoints(3);
        assertEquals(2, lines.size());
    }

    @Test
    public void twoCollinearPointsTwoParallelLineTest() {
        Point a = new Point(2.0, 4.0);
        Point b = new Point(4.0, 6.0);
        Point c = new Point(6.0, 8.0);
        Point d = new Point(3.0, 4.0);
        Point e = new Point(5.0, 6.0);
        Point f = new Point(7.0, 8.0);

        space.addPoint(a);
        space.addPoint(b);
        space.addPoint(c);
        space.addPoint(d);
        space.addPoint(e);
        space.addPoint(f);

        Set<Line> lines = space.getCollinearPoints(2);
        assertEquals(11, lines.size());
    }

    @Test
    public void fourPointThreeCollinearPointsTest() {
        Point a = new Point(2.0, 4.0);
        Point b = new Point(4.0, 6.0);
        Point c = new Point(6.0, 8.0);
        Point d = new Point(-2.0, 1023.0);

        space.addPoint(a);
        space.addPoint(b);
        space.addPoint(c);
        space.addPoint(d);

        Set<Line> lines = space.getCollinearPoints(3);
        assertEquals(1, lines.size());
    }

    @Test
    public void verticalSlopeTest() {
        for(int i = 0; i<10; i++) {
            space.addPoint(new Point(5.0, Double.valueOf(i)));
        }

        Set<Line> lines = space.getCollinearPoints(3);
        assertEquals(1, lines.size());
    }

    @Test
    public void verticalAndValidSlopeTest() {
        for(int i = 0; i<10; i++) {
            space.addPoint(new Point(5.0, Double.valueOf(i)));
            space.addPoint(new Point(Double.valueOf(i), Double.valueOf(i+2)));
        }

        Set<Line> lines = space.getCollinearPoints(3);
        assertEquals(2, lines.size());
    }

    @Test
    public void getOneCollinearPointTest() {
        Point a = new Point(2.0, 3.0);
        Point b = new Point(-2.0, 1023.0);
        Point c = new Point(3.2, 0.0);

        space.addPoint(a);
        space.addPoint(b);
        space.addPoint(c);

        Set<Line> lines = space.getCollinearPoints(1);
        assertEquals(0, lines.size());
    }


    @Test
    public void addPointTest() {
        Point a = new Point(2.0, 3.0);
        Point b = new Point(-2.0, 1023.0);
        Point c = new Point(3.2, 0.0);

        space.addPoint(a);
        space.addPoint(b);
        space.addPoint(c);

        Set<Point> points = space.getAllPoints();
        assertEquals(3, points.size());
    }

    @Test
    public void addSamePointTest() {
        Point a = new Point(2.0, 3.0);

        space.addPoint(a);
        space.addPoint(a);

        Set<Point> points = space.getAllPoints();
        assertEquals(1, points.size());
    }

    @Test
    public void addEqualsPointsTest() {
        Point a = new Point(2.0, 3.0);
        Point b = new Point(2.0, 3.0);

        space.addPoint(a);
        space.addPoint(b);

        Set<Point> points = space.getAllPoints();
        assertEquals(1, points.size());
    }

    @Test
    public void addOneMillionPointTest() {
        Random r = new Random();

        while(space.getAllPoints().size() < 1000000) {
            Double x = -10000 + (10000 - (-10000)) * r.nextDouble();
            Double y = -10000 + (10000 - (-10000)) * r.nextDouble();
            space.addPoint(new Point(x, y));
        }

        assertEquals(1000000, space.getAllPoints().size());
    }

    @Test
    public void getAllLinesTest() {
        Point a = new Point(2.0, 3.0);
        Point b = new Point(-2.0, 1023.0);
        Point c = new Point(3.2, 0.0);

        space.addPoint(a);
        space.addPoint(b);
        space.addPoint(c);

        space.getCollinearPoints(3);
        Set<Line> lines = space.getAllLines();
        assertEquals(3, lines.size());
    }

    @Test
    public void removeAllPointsBeforeComputingCollinearPointsTest() {
        Point a = new Point(2.0, 3.0);
        Point b = new Point(-2.0, 1023.0);
        Point c = new Point(3.2, 0.0);

        space.addPoint(a);
        space.addPoint(b);
        space.addPoint(c);

        space.removeAllPoints();
        assertEquals(0, space.getAllPoints().size());
    }

    @Test
    public void removeAllPointsAfterComputingCollinearPointsTest() {
        Point a = new Point(2.0, 3.0);
        Point b = new Point(-2.0, 1023.0);
        Point c = new Point(3.2, 0.0);

        space.addPoint(a);
        space.addPoint(b);
        space.addPoint(c);

        space.getCollinearPoints(2);
        space.removeAllPoints();
        assertEquals(0, space.getAllPoints().size());
        assertEquals(0, space.getAllLines().size());
    }
}