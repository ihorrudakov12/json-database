package client;

import com.google.gson.JsonElement;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> argsMap = new LinkedHashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            argsMap.put(args[i], args[i + 1]);
        }
        Client client = new Client();
        client.runClient(argsMap);
    }
}
