package server;

public class DatabaseInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public Object executeCommand() {
        return command.execute();
    }
}
