package impl;

import util.Monitor;
import util.Partition;

public class QuicksortRunnable extends Monitor implements Runnable {

    protected Partition p;


    /**
     * Do not modify.
     *
     * @param p Contains all information required for a recursion step.
     */
    public QuicksortRunnable(Partition p) {
        super(p);
        this.p = p;
    }

    private synchronized int partition(int array[], int left, int right) {
        int pivot = array[right];
        int pivotIndex = left - 1;

        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) {
                pivotIndex++;

                int aux = array[pivotIndex];
                array[pivotIndex] = array[j];
                array[j] = aux;
            }
        }

        pivotIndex++;

        int aux = array[pivotIndex];
        array[pivotIndex] = array[right];
        array[right] = aux;

        return pivotIndex;
    }

    @Override
    public void run() {
        if (p.left < p.right) {
            int pivot = partition(p.array, p.left, p.right);

            Partition leftPartition = new Partition(p.array, p.left, pivot - 1);
            Thread leftThread = new Thread(new QuicksortRunnable(leftPartition));
            leftThread.start();

            Partition rightPartition = new Partition(p.array, pivot + 1, p.right);
            Thread rightThread = new Thread(new QuicksortRunnable(rightPartition));
            rightThread.start();

            try {
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
