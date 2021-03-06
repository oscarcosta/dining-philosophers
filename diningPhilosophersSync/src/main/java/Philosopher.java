import java.util.concurrent.ThreadLocalRandom;

public class Philosopher implements Runnable {

    private final int id;
    private final Fork firstFork, secondFork;
    private final boolean rightHanded;

    public Philosopher(int id, Fork rightFork, Fork leftFork, boolean rightHanded) {
        this.id = id;
        if (rightHanded) {
            this.firstFork = rightFork;
            this.secondFork = leftFork;
        } else {
            this.firstFork = leftFork;
            this.secondFork = rightFork;
        }
        this.rightHanded = rightHanded;
    }

    private void doAction(String action) throws InterruptedException {
        long millis = ThreadLocalRandom.current().nextInt(0, 100);
        System.out.printf("Philosopher %d is '%s' for %d millis\n", id, action, millis);
        //Thread.sleep(millis);
    }

    private String getHand(Fork fork) {
        if (firstFork.equals(fork)) {
            return rightHanded ? "right" : "left";
        } else {
            return rightHanded ? "left" : "right";
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                doAction("thinking");

                synchronized (firstFork) {
                    System.out.printf("Philosopher %d picked up %s fork\n", id, getHand(firstFork));
                    synchronized (secondFork) {
                        System.out.printf("Philosopher %d picked up %s fork\n", id, getHand(secondFork));

                        doAction("eating");

                        System.out.printf("Philosopher %d put down %s fork\n", id, getHand(firstFork));
                    }
                    System.out.printf("Philosopher %d put down %s fork\n", id, getHand(secondFork));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
