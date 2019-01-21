package programming_task.pattern_recognition.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import programming_task.pattern_recognition.model.Line;
import programming_task.pattern_recognition.model.Point;
import programming_task.pattern_recognition.model.Space;

import javax.print.attribute.standard.Media;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Validated
public class PatternRecognitionController {
    private Space space;

    public PatternRecognitionController() {
        this.space = new Space();
    }

    @GetMapping("/space")
    public Set<Point> getSpace() {
        return space.getAllPoints();
    }

    @GetMapping("/lines/{n}")
    public Set<Set<Point>> getCollinearPoints(
            @PathVariable(value = "n", required = true)
            @Min(value = 2, message = "A line should contain at least two points, n must be greater than or equal to 2.") int n) {
        Set<Line> lines = space.getCollinearPoints(n);
        return lines.stream()
                .map( line -> line.getPoints())
                .collect(Collectors.toSet());
    }

    @PostMapping(value = "/point")
    public ResponseEntity<?> addPoint(@RequestBody @Valid Point point) {
        HttpHeaders responseHeader = new HttpHeaders();
        if( !(space.getAllPoints().contains(point)) ) {
            space.addPoint(point);
            return new ResponseEntity<>(point, responseHeader, HttpStatus.CREATED);
        }

        return new ResponseEntity<>("The point you tried to add already exists in the Space.", responseHeader, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/space")
    public void removeAllPoints() {
        space.removeAllPoints();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("Validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
