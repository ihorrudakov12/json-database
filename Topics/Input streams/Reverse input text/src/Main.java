import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder string = new StringBuilder(1);
        String word = reader.readLine();
        StringBuilder result = new StringBuilder(word);
        System.out.println(result.reverse());
    }
}