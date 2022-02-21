package lab5.common.commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import lab5.common.data.Person;
import lab5.common.util.CollectionManager;
import lab5.common.util.CommandManager;
import lab5.common.util.CommandRunner;
import lab5.common.util.IOManager;
import lab5.common.util.Reader;

public final class ExecuteScript extends CollectionCommand {

    private final String man = "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    private CommandManager commands;
    private Collection<String> bannedFileNames = new HashSet<>();
    private BufferedReader bufferedReader;

    public ExecuteScript(CollectionManager<Person> manager, CommandManager commands) {
        super(manager);
        this.commands = commands;
    }

    public ExecuteScript(IOManager io, CollectionManager<Person> manager, CommandManager commands) {
        super(io, manager);
        this.commands = commands;
    }

    @Override
    public CommandResponse execute(String arg) {
        try {
            this.setIO(new IOManager(createFileReader(arg), this.getIO().getWritter()));
        } catch (FileNotFoundException e) {
            return CommandResponse.FILE_NOT_FOUND;
        }
        IOManager oldIO = commands.getIO();
        commands.setIO(this.getIO());
        CommandRunner runner = new CommandRunner(commands);
        String line = this.getIO().readLine();
        CommandResponse resp = CommandResponse.SUCCESS;
        String[] parsedLine;
        while (!Objects.isNull(line) && !resp.equals(CommandResponse.END)) {
            parsedLine = runner.parseCommand(line);
            if (commands.get(parsedLine[0]).getClass().equals(this.getClass())) {
                if (bannedFileNames.contains(parsedLine[1])) {
                    continue;
                }
                bannedFileNames.add(parsedLine[1]);
            }
            resp = runner.runOne(parsedLine[0], parsedLine[1]);
            line = this.getIO().readLine();
        }
        commands.setIO(oldIO);
        return CommandResponse.SUCCESS;
    }

    private Reader createFileReader(String fileName) throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(fileName));
        return () -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                return "";
            }
        };
    }

    @Override
    public String toString() {
        return "ExecuteScript";
    }

    @Override
    public String getMan() {
        return man;
    }
}
