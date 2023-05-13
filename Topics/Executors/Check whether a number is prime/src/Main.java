import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        String[] digits = scanner.nextLine().split(" ");

        for (String digit : digits) {
            int number = Integer.parseInt(digit);
            executor.submit(() -> new PrintIfPrimeTask(number).run());
        }
        executor.shutdown();
    }
}

class PrintIfPrimeTask {
    private final int number;

    public PrintIfPrimeTask(int number) {
        this.number = number;
    }

    public void run() {
        BigInteger bigInteger = BigInteger.valueOf(number);
        boolean probablePrime = bigInteger.isProbablePrime(number);
        if (probablePrime) {
            System.out.println(number);
        }
    }
}