public class RaceConditionExample {

    private static int counter = 0;

    public static void main(String[] args) {
        int numberOfThreads = 10;
        Thread[] threads = new Thread[numberOfThreads];
        // Метод join запускался в отдельном цикле, в результате чего потоки не ждали завершения дргугих потоков
        // и разные потоки увеличивали одно и то же значение глобального каунтера
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    counter++;
                }
            });
            threads[i].start();

            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final counter value: " + counter);
    }
}
