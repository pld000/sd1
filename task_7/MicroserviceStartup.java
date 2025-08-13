import java.util.concurrent.CyclicBarrier;

public class MicroserviceStartup {
    public static void main(String[] args) {
        int services = 3;
        CyclicBarrier barrier = new CyclicBarrier(services, () ->
                System.out.println("\nStart: \n")
        );

        for (int i = 1; i <= services; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    System.out.println("Service " + id + " is init...");
                    Thread.sleep((long) (1000 + Math.random() * 3000));
                    System.out.println("Service " + id + " is ready.");
                    barrier.await(); // ждём остальных сервисов
                    System.out.println("Service " + id + " is getting data.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
