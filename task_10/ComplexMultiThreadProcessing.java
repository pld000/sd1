import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ComplexMultiThreadProcessing {
    private static final int SIZE = 1000000;
    private static final int THREADS = 4;
    private static final int[] data = new int[SIZE];
    private static volatile int sum = 0;

    public static void main(String[] args) {
        Random random = new Random();
        IntStream.range(0, SIZE).forEach(i -> data[i] = random.nextInt(100));

        Thread[] threads = new Thread[THREADS];
        int chunkSize = SIZE / THREADS;

        for (int i = 0; i < THREADS; i++) {
            final int start = i * chunkSize;
            final int end = (i + 1) * chunkSize;

            threads[i] = new Thread(() -> {
                synchronized (ComplexMultiThreadProcessing.class) {
                    sum += Arrays.stream(data, start, end).sum();;
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < THREADS; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Sum of all elements: " + sum);
    }
}