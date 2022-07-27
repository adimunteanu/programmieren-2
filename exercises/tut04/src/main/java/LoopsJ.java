public class LoopsJ {

    public static void main(String[] args) {

        // A)
        System.out.println(">>> A <<<");
        for (int i=0; i < 10; i++) {
            System.out.println(i);
        }

        // B)
        System.out.println(">>> B <<<");
        for (int i=0; i < 10; i=i+2) {
            System.out.println(i);
        }

        // C)
        System.out.println(">>> C <<<");
        for (int i=0; i < 10; i++) {
            for (int j=0; j < 10; j++) {
                System.out.println(i + ", " + j);
            }
        }
    }
}