package lab.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

import lab.common.data.PersonCollectionManager;
import lab.io.IOManager;
import lab.io.Reader;
import lab.util.CommandRunner;

public final class ExecuteScript extends CollectionCommand {

    private CommandRunner runner;
    private Stack<File> bannedFiles = new Stack<>();
    private Stack<IOManager> oldIO = new Stack<>();
    private BufferedReader bufferedReader;

    public ExecuteScript(PersonCollectionManager manager, CommandRunner runner) {
        super(manager);
        this.runner = runner;
    }

    public ExecuteScript(IOManager io, PersonCollectionManager manager, CommandRunner runner) {
        super(io, manager);
        this.runner = runner;
    }

    @Override
    public CommandResponse execute(String arg) {
        try {
            File file = new File(arg);
            FileReader fileReader = new FileReader(file);
            this.setIO(createReader(fileReader), this.getIO().getWritter());
            bannedFiles.push(file);
        } catch (IOException e) {
            return new CommandResponse(CommandResult.ERROR, "Unable to read file");
        }
        oldIO.push(runner.getCommandManager().getIO());
        runner.getCommandManager().setIO(this.getIO());
        String line;
        CommandResponse resp = null;
        String[] parsedLine;
        do {
            line = this.getIO().readLine();
            if (Objects.isNull(line)) {
                break;
            }
            parsedLine = runner.parseCommand(line);
            resp = runner.run(parsedLine[0], parsedLine[1]);
        } while (!resp.getResult().equals(CommandResult.END));
        runner.getCommandManager().setIO(oldIO.pop());
        bannedFiles.pop();
        return new CommandResponse(CommandResult.SUCCESS);
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
