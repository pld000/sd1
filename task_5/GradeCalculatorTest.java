import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class GradeCalculatorTest {
    @Test()
    public void testCalculateAverageWithNullList() {
        GradeCalculator calculator = new GradeCalculator();
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateAverage(null);
        });
    }

    @Test()
    public void testCalculateAverageWithEmptyList() {
        GradeCalculator calculator = new GradeCalculator();
        List<Integer> grades = List.of();
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateAverage(grades);
        });
    }

    @Test()
    public void testCalculateAverageWithNegativeGrade() {
        GradeCalculator calculator = new GradeCalculator();
        List<Integer> grades = List.of(1, 3, 4, -5);
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateAverage(grades);
        });
    }

    @Test()
    public void testCalculateAverageWithZeroGrade() {
        GradeCalculator calculator = new GradeCalculator();
        List<Integer> grades = List.of(1, 3, 0, 4, 5);
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateAverage(grades);
        });
    }

    @Test()
    public void testCalculateAverageWithPositiveGradeThatGreaterThanFive() {
        GradeCalculator calculator = new GradeCalculator();
        List<Integer> grades = List.of(1, 3, 4, 5, 9);
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateAverage(grades);
        });
    }

    @Test()
    public void testCalculateAverageWithPositiveGrade() {
        GradeCalculator calculator = new GradeCalculator();
        List<Integer> grades = List.of(1, 3, 4, 5, 2);
        assertEquals(3.0, calculator.calculateAverage(grades), 0.001);
    }
}
