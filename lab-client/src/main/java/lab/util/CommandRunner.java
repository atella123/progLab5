package lab.util;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import lab.commands.Command;
import lab.commands.CommandResponse;
import lab.commands.CommandResult;
import lab.io.IOManager;

public class CommandRunner {

    private IOManager io;
    private CommandManager cmds;
    private final Integer historySize = 11;
    private Queue<Command> history = new ArrayBlockingQueue<>(historySize);

    public CommandRunner(CommandManager cmds) {
        this.io = new IOManager();
        this.cmds = cmds;
    }

    public CommandRunner(IOManager io, CommandManager cmds) {
        this.io = io;
        this.cmds = cmds;
    }

    public void run() {
        CommandResponse resp = null;
        do {
            try {
                String[] cmd = parseCommand(io.readLine());
                resp = run(cmd[0], cmd[1]);
                if (resp.hasPrintableResult()) {
                    io.write(resp.getMessage());
                }
            } catch (NullPointerException e) {
                io.write("Unknown command");
            }
        } while (!resp.getResult().equals(CommandResult.END));
    };

    public CommandResponse run(String cmd, String arg) {
        history.add(cmds.get(cmd));
        return cmds.get(cmd).execute(arg);
    }

    public Collection<Command> getHistory() {
        return history;
    }

    public String[] parseCommand(String arg) {
        String[] cmd = {arg.split("\\s+", 2)[0], null};
        if (arg.matches("\\w+\\s+.+")) {
            cmd[1] = arg.split(" +", 2)[1];
        }
        return cmd;
    }

    public IOManager getIo() {
        return io;
    }

    public CommandManager getCommandManager() {
        return cmds;
    }

    public void setIo(IOManager io) {
        this.io = io;
    }
}
