package producerconsumer;

import java.util.Queue;
import java.util.Random;

public class Producer extends Thread {
    private Container queue;

    public Producer(Container queue, String name) {
        super(name);
        this.queue = queue;
    }

    public void run() {
        Random random = new Random();
        while (true) {
            int i = random.nextInt(100 - 0) + 0;
            queue.add(i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }
}
