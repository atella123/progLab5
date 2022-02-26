package lab.util;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;

import lab.commands.Command;
import lab.commands.CommandResponse;
import lab.commands.CommandResult;
import lab.io.IOManager;

public class CommandRunner {

    private static final Integer HISTORY_SIZE = 11;
    private IOManager io;
    private CommandManager cmds;
    private ArrayBlockingQueue<Command> history = new ArrayBlockingQueue<>(HISTORY_SIZE);

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
                String nextLine = io.readLine();
                if (Objects.isNull(nextLine)) {
                    return;
                }
                String[] cmd = parseCommand(nextLine);
                resp = runCommand(cmds.get(cmd[0]), cmd[1]);
            } catch (NullPointerException e) {
                resp = new CommandResponse(CommandResult.ERROR, "Unknown command");
            }
            if (resp.hasPrintableResult() && !resp.getResult().equals(CommandResult.SUCCESS)) {
                io.write(resp.getMessage());
            }
        } while (!resp.getResult().equals(CommandResult.END));
    }

    public CommandResponse runCommand(Command cmd, String arg) {
        if (history.remainingCapacity() == 0) {
            history.poll();
        }
        history.add(cmd);
        return cmd.execute(arg);
    }

    public Collection<Command> getHistory() {
        return history;
    }

    public String[] parseCommand(String arg) {
        String[] cmd = {arg.split("\\s+", 2)[0].replace(" ", ""), null};
        if (arg.matches("\\w+\\s+.+")) {
            cmd[1] = arg.split(" +", 2)[1];
        }
        return cmd;
    }

    public IOManager getIO() {
        return io;
    }

    public CommandManager getCommandManager() {
        return cmds;
    }

    public void setIO(IOManager newIO) {
        this.io = newIO;
    }
}
