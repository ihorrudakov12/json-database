import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Problem {
    public static void main(String[] args) {
        Map<String, String> argsMap = new LinkedHashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            argsMap.put(args[i], args[i + 1]);
        }

        if (argsMap.get("mode") != null) {
            System.out.println(argsMap.get("mode"));
        } else {
            System.out.println("default");
        }
    }
}