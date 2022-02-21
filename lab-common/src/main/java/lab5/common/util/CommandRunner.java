package lab5.common.util;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import lab5.common.commands.Command;
import lab5.common.commands.CommandResponse;

public class CommandRunner {

    protected IOManager io;
    protected CommandManager cmds;
    protected Queue<Command> history = new ArrayBlockingQueue<>(11);

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
                history.add(cmds.get(cmd[0]));
                resp = cmds.get(cmd[0]).execute(cmd[1]);
                if (resp != CommandResponse.SUCCESS) {
                    io.write(resp.toString());
                }
            } catch (NullPointerException e) {
                io.write("Unknown command");
            }
        } while (resp != CommandResponse.END);
    };

    public CommandResponse runOne(String cmd, String arg) {
        history.add(cmds.get(cmd));
        return cmds.get(cmd).execute(cmd);
    }

    public Collection<Command> getHistory() {
        return history;
    }

    public String[] parseCommand(String arg) {
        String[] cmd = { arg.split("\\s+", 2)[0], null };
        if (arg.matches("\\w+\\s+.+")) {
            cmd[1] = arg.split(" +", 2)[1];
        }
        return cmd;
    }

    public IOManager getIo() {
        return io;
    }

    public void setIo(IOManager io) {
        this.io = io;
    }
}
