package impl;

import util.Partition;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {

    /**
     * Do not modify this method.
     */
    public static void main(String[] args) {
        int[] sortedArray = readAndSort("numbers.txt");
        System.out.println(Arrays.toString(sortedArray));
    }

    /**
     * Reads an array of integers from a given resource file using FileInput.readIntsFromFile and sorts the resulting
     * array using multi-threaded QuickSort provided by QuicksortRunnable.
     *
     * @param filePath Path to the file containing the integer array.
     * @return Sorted array of integers.
     */
    public static int[] readAndSort(String filePath) {
        FileInput fileInput = new FileInput();

        int[] array = fileInput.readIntsFromFile(filePath);
        Partition p = new Partition(array, 0, array.length - 1);

        Thread quickSortMainThread = new Thread(new QuicksortRunnable(p));
        quickSortMainThread.start();

        try {
            quickSortMainThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return array;
    }
}
