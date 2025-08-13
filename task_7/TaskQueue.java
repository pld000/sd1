import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class TaskQueue {
    private final Queue<String> queue = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();

    public void addTask(String task) {
        lock.lock();
        try {
            queue.offer(task);
            System.out.println(Thread.currentThread().getName() + " add task: " + task);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        TaskQueue taskQueue = new TaskQueue();
        Runnable producer = () -> {
            for (int i = 0; i < 3; i++) {
                taskQueue.addTask("Задача " + i);
            }
        };

        Thread t1 = new Thread(producer, "Producer-1");
        Thread t2 = new Thread(producer, "Producer-2");

        t1.start();
        t2.start();
    }
}
