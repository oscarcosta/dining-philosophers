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
        Thread.sleep(millis);
    }

    private String getHand(Fork fork) {
        return rightHanded && fork == firstFork ? "right" : "left";
    }

    private void pickUpForks() throws InterruptedException {
        firstFork.pickUp();
        System.out.printf("Philosopher %d picked up %s fork\n", id, getHand(firstFork));

        secondFork.pickUp();
        System.out.printf("Philosopher %d picked up %s fork\n", id, getHand(secondFork));
    }

    private void putDownForks() {
        firstFork.putDown();
        System.out.printf("Philosopher %d put down %s fork\n", id, getHand(firstFork));

        secondFork.putDown();
        System.out.printf("Philosopher %d put down %s fork\n", id, getHand(secondFork));
    }

    @Override
    public void run() {
        try {
            while (true) {
                doAction("thinking");

                pickUpForks();

                doAction("eating");

                putDownForks();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
