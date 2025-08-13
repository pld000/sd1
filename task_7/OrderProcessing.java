import java.util.concurrent.CompletableFuture;

public class OrderProcessing {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Order processing...");
            sleep(1000);
            return "Order №123";
        }).thenApply(order -> {
            System.out.println("Sending: " + order);
            sleep(1500);
            return order + " send";
        }).thenAccept(status ->
                System.out.println("Status: " + status)
        );

        System.out.println("Others tasks are processed...");
        sleep(3000);
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
