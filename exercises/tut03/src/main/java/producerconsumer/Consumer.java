package producerconsumer;

import java.util.Queue;

public class Consumer extends Thread {
    private Queue<Integer> queue;

    public Consumer(Container queue, String name) {
        super(name);
        this.queue = queue;
    }

    public void run() {
        while (true) {
            queue.remove();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }
    }
}
