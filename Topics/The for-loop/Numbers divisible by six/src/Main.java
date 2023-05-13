import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfNums = scanner.nextInt();
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < numOfNums; i++) {
            nums.add(scanner.nextInt());
        }
        int sum = 0;
        for (int num : nums) {
            if (num % 6 == 0) {
                sum += num;
            }
        }
        System.out.println(sum);
    }
}