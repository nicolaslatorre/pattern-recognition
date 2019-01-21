package programming_task.pattern_recognition.model;

import java.util.*;
import java.util.stream.Collectors;

public class Space {
    private Set<Point> points;
    private Set<Line> lines;

    public Space() {
        points = new HashSet<>();
        lines = new HashSet<>();
    }

    public Set<Line> getCollinearPoints(int numberOfPoints) {
        if( numberOfPoints >= 2) {
            computeCollinearPoints();
            return getLinesWithDesiredNumberOfPoints(numberOfPoints);
        }
        return Collections.emptySet();
    }

    private void computeCollinearPoints() {
        for(Point startPoint : points) {
            Map<Slope, HashSet<Point>> slopesByPoints = computeSlopesForGivenPoint(startPoint);
            Set<Line> linesPassingThroughPoint = createLines(slopesByPoints);
            addLines(linesPassingThroughPoint);
        }
    }

    private Map<Slope, HashSet<Point>> computeSlopesForGivenPoint(Point startPoint) {
        Map<Slope, HashSet<Point>> slopesByPoints = new HashMap<>();
        for(Point currentPoint : points) {
            if( !(currentPoint.equals(startPoint)) ) {
                Slope slope = Slope.from(startPoint, currentPoint);

                if( slopesByPoints.containsKey(slope) ) {
                    slopesByPoints.get(slope).add(currentPoint);
                } else {
                    HashSet<Point> pointsInSlope = new HashSet<>();
                    pointsInSlope.add(startPoint);
                    pointsInSlope.add(currentPoint);
                    slopesByPoints.put(slope, pointsInSlope);

                }
            }
        }
        return slopesByPoints;
    }

    private Set<Line> createLines(Map<Slope, HashSet<Point>> slopesByPoints) {
        return new HashSet<>(slopesByPoints.values()).stream()
                .map( points -> new Line(points) )
                .collect(Collectors.toSet());
    }

    private void addLines(Set<Line> linesPassingThroughPoint) {
        linesPassingThroughPoint.forEach( line -> lines.add(line) );
    }

    private Set<Line> getLinesWithDesiredNumberOfPoints(int numberOfPoints) {
        return lines.stream()
                .filter( line -> line.getNumberOfPoints() >= numberOfPoints )
                .collect(Collectors.toSet());
    }

    public void addPoint(Point point) {
        if ( !(point == null) ) {
            points.add(point);
        }
    }

    public Set<Point> getAllPoints() {
        return points;
    }

    public Set<Line> getAllLines() {
        return lines;
    }

    public void removeAllPoints() {
        removeAllLines();
        points.clear();
    }

    private void removeAllLines() {
        lines.clear();
    }
}
