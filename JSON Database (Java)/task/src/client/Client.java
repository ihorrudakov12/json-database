package client;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import helper.GsonHelper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 34522;
    String path = System.getProperty("user.dir") + "/src/client/data/";


    public void runClient(Map<String, String> argsMap) {
        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output  = new DataOutputStream(socket.getOutputStream())
        ) {
            System.out.println("Client started!");
            Args argsModel;
            if (argsMap.get("-in") != null) {
                argsModel = readRequestParametersFromFile(argsMap.get("-in"));
            } else {
                Gson gson = new Gson();
                argsModel = new Args(argsMap.get("-t"), gson.toJsonTree(argsMap.get("-k")),
                        gson.toJsonTree(argsMap.get("-v")));
            }
            String json = new GsonHelper<Args>().serializeJson(argsModel);
            output.writeUTF(json);
            System.out.println("Sent: " + json);
            String receivedMsg = input.readUTF();
            System.out.println("Received: " + receivedMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Args readRequestParametersFromFile(String fileParameter) {
        Args args;
        try (Reader reader = Files.newBufferedReader(Paths.get(path + fileParameter), StandardCharsets.UTF_8)) {
            args = new Gson().fromJson(reader, Args.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return args;
    }
}
