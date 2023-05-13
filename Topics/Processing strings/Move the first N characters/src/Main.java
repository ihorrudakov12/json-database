import javax.print.attribute.standard.PresentationDirection;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inline = scanner.nextLine().split(" ");
        String s = "";
        int n = 0;
        if (inline.length == 2) {
            s = inline[0];
            n = Integer.parseInt(inline[1]);
        }
        if (n <= s.length()) {
            String result = s.substring(n) + s.substring(0, n);
            System.out.println(result);
        } else {
            System.out.println(s);
        }
    }
}