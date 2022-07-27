public class FunctionsJ {

    // A)
    public int multiply(int x, int y) {
        return x * y;
    }

    // B)
    public double divide(double x, double y) {
        if (y == 0) {
            return 0; // eigentlich würden wir hier gerne eine Warnung ausgeben, statt einer Zahl zurückzugeben
        }

        return x / y;
    }

    // C)
    public void printSum(int x, int y) {
        System.out.println(x + " + " + y + " = " + (x+y));
    }

    public static void main(String[] args) {
        FunctionsJ f = new FunctionsJ();

        // A)
        System.out.println("A");
        System.out.println(f.multiply(10, 5));
        System.out.println(f.multiply(10, -5));

        // B)
        System.out.println("B");
        System.out.println(f.divide(10, 5));
        System.out.println(f.divide(10, 0));

        // C)
        System.out.println("C");
        f.printSum(10, 5);
        f.printSum(10, -5);
    }
}