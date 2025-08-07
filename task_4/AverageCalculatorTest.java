import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class AverageCalculatorTest {
    @Test()
    public void averageCalculatorTest() {
        int[] t1 = {1, 2, 4, 5, 8};
        int r1 = AverageCalculator.calculateAverage(t1);
        Assertions.assertEquals(4, r1, "Wrong average");

        int[] t2 = {1, 2, 4, 5, -8};
        int r2 = AverageCalculator.calculateAverage(t2);
        Assertions.assertTrue(r2 > 0, "Average always bigger then zero");
    }
}
