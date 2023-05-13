package server;

import client.Args;
import com.google.gson.Gson;
import helper.GsonHelper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.util.concurrent.Callable;

public class DataBaseProcessor implements Callable<Boolean> {
    DataInputStream input;
    DataOutputStream output;

    public DataBaseProcessor(DataInputStream input, DataOutputStream output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public Boolean call() throws Exception {
        boolean isExit = false;
        Response response = new Response();
        DataBase db = new DataBase();
        DatabaseInvoker databaseInvoker = new DatabaseInvoker();
        Args args = new GsonHelper<Args>().deserializeJson(input.readUTF(), Args.class);
        String command = args.getType();
        Object res = "";
        switch (command) {
            case "set":
                databaseInvoker.setCommand(new SetCommand(db, args.getKey(), args.getValue()));
                databaseInvoker.executeCommand();
                sendMessage(response.setResponse("OK"));
                break;
            case "delete":
                databaseInvoker.setCommand(new DeleteCommand(db, args.getKey()));
                res = databaseInvoker.executeCommand();
                response = res == null ? response.setResponse("ERROR").setReason("No such key")
                        : response.setResponse("OK");
                sendMessage(response);
                break;
            case "get":
                databaseInvoker.setCommand(new GetCommand(db, args.getKey()));
                res = databaseInvoker.executeCommand();
                response = res == null ? response.setResponse("ERROR").setReason("No such key")
                        :  response.setResponse("OK").setValue(res);
                sendMessage(response);
                break;
            case "exit":
                response.setResponse("OK");
                sendMessage(response);
                isExit = true;
                break;
        }
        return isExit;
    }

    public synchronized void sendMessage(Response response) throws IOException {
        output.writeUTF(new GsonHelper<Response>().serializeJson(response));
    }
}
