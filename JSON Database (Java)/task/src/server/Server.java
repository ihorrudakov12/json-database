package server;

import client.Args;
import helper.GsonHelper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {
    static final int PORT = 34522;

    public void runServer() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server started!");
            while (true) {
                Socket socket = null;
                try {
                    socket = server.accept();
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    Future<Boolean> future = executor.submit(() ->
                            new DataBaseProcessor(dataInputStream, dataOutputStream).call());
                    if (future.get()) {
                        executor.shutdown();
                        break;
                    }
                } catch (IOException | ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
