package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    public static void main(String[] args) {
        System.out.println("Verwendung von wait und notify");

        int maxSize = 10;
        Container buffer = new Container(10);
        Thread producer = new Producer(buffer, "PRODUCER");
        Thread consumer = new Consumer(buffer, "CONSUMER");
        producer.start();
        consumer.start();
    }
}
