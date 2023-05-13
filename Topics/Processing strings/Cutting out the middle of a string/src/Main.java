import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> word = new ArrayList<>(Arrays.asList(scanner.nextLine().split("")));
        if (word.size() % 2 == 0) {
            word.remove(word.size() / 2 - 1);
            word.remove(word.size() / 2);
        } else {
            word.remove(word.size() / 2);
        }
        word.forEach(System.out::print);
    }
}