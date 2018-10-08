import java.util.concurrent.Semaphore;

public class Fork {

    private Semaphore mutex = new Semaphore(1);

    public void pickUp() throws InterruptedException {
        mutex.acquire();
    }

    public void putDown() {
        mutex.release();
    }
}
