import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[3];
        for (int i = 0; i < 3; i++) {
            numbers[i] = scanner.nextInt();
        }
        int counter = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > 0) {
                counter++;
            }
        }
        System.out.println(counter == 1);
    }
}