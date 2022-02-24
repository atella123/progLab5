package lab.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Objects;

import lab.common.data.PersonCollectionManager;
import lab.io.IOManager;
import lab.io.Reader;
import lab.util.CommandManager;
import lab.util.CommandRunner;

public final class ExecuteScript extends CollectionCommand {

    private CommandManager commands;
    private ArrayDeque<File> bannedFiles = new ArrayDeque<>();
    private ArrayDeque<IOManager> oldIO = new ArrayDeque<>();
    private BufferedReader bufferedReader;

    public ExecuteScript(PersonCollectionManager manager, CommandManager commands) {
        super(manager);
        this.commands = commands;
    }

    public ExecuteScript(IOManager io, PersonCollectionManager manager, CommandManager commands) {
        super(io, manager);
        this.commands = commands;
    }

    @Override
    public CommandResponse execute(String arg) {
        try {
            FileReader fileReader = new FileReader(new File(arg));
            this.setIO(createReader(fileReader), this.getIO().getWritter());
        } catch (IOException e) {
            return CommandResponse.FILE_NOT_FOUND;
        }
        oldIO.addFirst(commands.getIO());
        commands.setIO(this.getIO());
        CommandRunner runner = new CommandRunner(commands);
        String line;
        CommandResponse resp = null;
        String[] parsedLine;
        do {
            line = this.getIO().readLine();
            parsedLine = runner.parseCommand(line);
            if (commands.get(parsedLine[0]).getClass().equals(this.getClass())) {
                File file = new File(parsedLine[1]);
                if (bannedFiles.contains(file)) {
                    continue;
                }
                bannedFiles.addFirst(file);
            }
            resp = runner.run(parsedLine[0], parsedLine[1]);
        } while (!Objects.isNull(line) && !resp.equals(CommandResponse.END));
        commands.setIO(oldIO.pollFirst());
        bannedFiles.pollFirst();
        return CommandResponse.SUCCESS;
    }

    private Reader createReader(FileReader fileReader) {
        bufferedReader = new BufferedReader(fileReader);
        return () -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                return null;
            }
        };
    }

    @Override
    public String toString() {
        return "ExecuteScript";
    }

    @Override
    public String getMan() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
