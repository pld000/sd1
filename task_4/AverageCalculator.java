public class AverageCalculator {
    public static int calculateAverage(int[] items) {
        int result = 0;
        for (int item : items) {
            result += item;
        }

        return result / items.length;
    }
}
