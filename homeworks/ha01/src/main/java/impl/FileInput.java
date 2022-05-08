package impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileInput {

    /**
     * Reads an array of integers from a resource file.
     *
     * @param path Path to the file within the resources folder.
     * @return Array of integers.
     */
    public int[] readIntsFromFile(String path) {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(path);

        if (inputStream != null) {
            Scanner scanner = new Scanner(inputStream);
            List<Integer> list = new ArrayList<Integer>();

            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }

            int[] array = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i);
            }

            return array;
        }

        int[] emptyArray = new int[0];

        return emptyArray;
    }
}
