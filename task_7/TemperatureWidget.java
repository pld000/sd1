import java.util.concurrent.*;
import java.util.concurrent.Flow.*;

public class TemperatureWidget {

    public static void main(String[] args) throws InterruptedException {
        TemperatureSensor sensor = new TemperatureSensor();
        TemperatureLogger logger = new TemperatureLogger();
        sensor.subscribe(logger);
        sensor.start();
        Thread.sleep(5000);
        sensor.stop();
    }
}

class TemperatureSensor implements Publisher<Integer> {
    private SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
    private ScheduledExecutorService executor;
    private volatile boolean running;

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        publisher.subscribe(subscriber);
    }

    public void start() {
        running = true;
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            if (running) {
                int temp = (int)(Math.random() * 50);
                publisher.submit(temp);
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        running = false;
        executor.shutdown();
        publisher.close();
    }
}

class TemperatureLogger implements Subscriber<Integer> {
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Integer temperature) {
        System.out.println("Temperature: " + temperature + "°C");
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {}

    @Override
    public void onComplete() {
        System.out.println("Finished!");
    }
}