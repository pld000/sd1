import java.util.concurrent.Semaphore;

public class PrinterAccess {
    private final Semaphore semaphore = new Semaphore(2); // only two printers

    public void printDocument(String doc) {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " print doc: " + doc);
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " printing is finished: " + doc);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        PrinterAccess printer = new PrinterAccess();
        for (int i = 1; i <= 5; i++) {
            final int docId = i;
            new Thread(() -> printer.printDocument("Document: " + docId)).start();
        }
    }
}