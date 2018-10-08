
public class DiningPhilosophers {

    private final Philosopher[] philosophers;
    private final Fork[] forks;

    public DiningPhilosophers(int nPhilosophers) {
        this.philosophers = new Philosopher[nPhilosophers];
        this.forks = new Fork[nPhilosophers];

        // initialize the N forks
        for (int i = 0; i < nPhilosophers; i++) {
            forks[i] = new Fork();
        }

        // initialize the N philosophers
        for (int i = 0; i < nPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % nPhilosophers], i != 0);
        }
    }

    public void execute() {
        for (Philosopher philosopher : philosophers) {
            new Thread(philosopher).start();
        }
    }

    public static void main(String[] args) {
        int nPhilosophers = Integer.valueOf(args[0]);

        DiningPhilosophers diningPhilosophers = new DiningPhilosophers(nPhilosophers);
        diningPhilosophers.execute();
    }
}
