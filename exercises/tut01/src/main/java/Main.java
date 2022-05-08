public class Main {
	public static void main(String[] args) {
		Thread threadA = new LoggerThread("A ");
		Thread threadB = new LoggerThread("B ");
		threadA.start();
		threadB.start();


		Thread loggerC = new Thread(new LoggerRunnable("C "));
		Thread loggerD = new Thread(new LoggerRunnable("D "));
		loggerC.start();
		loggerD.start();

	}
}