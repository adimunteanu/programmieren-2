package producerconsumer;

import java.util.LinkedList;

public class Container extends LinkedList<Integer> {
    private int maxSize;

    public Container(int maxSize) {
        super();
        this.maxSize = maxSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public synchronized boolean add(Integer i) {
        try {
            while (this.size() == maxSize) {
                System.out.println("Queue ist voll. Der Produzent wartet.");
                wait();
            }
        } catch (InterruptedException e) {}

        System.out.println("Hinzuzufügen: " + i);
        boolean erfolg = super.add(i);
        notifyAll();

        return erfolg;
    }

    @Override
    public synchronized  Integer remove() {
        try {
            while(this.isEmpty()) {
                System.out.println("Queue ist leer. Der Konsument wartet");
                wait();
            }
        } catch (InterruptedException e) {}

        Integer wert = super.remove();
        System.out.println("Zu entfernen: " + wert);
        notifyAll();

        return wert;
    }
}
