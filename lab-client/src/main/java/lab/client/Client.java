package lab.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import lab.commands.Add;
import lab.commands.AddIfMax;
import lab.commands.Clear;
import lab.commands.Command;
import lab.commands.ExecuteScript;
import lab.commands.Exit;
import lab.commands.FilterLessThanNationality;
import lab.commands.GroupCountingByPassportID;
import lab.commands.Help;
import lab.commands.History;
import lab.commands.Info;
import lab.commands.MinByCoordinates;
import lab.commands.RemoveByID;
import lab.commands.RemoveGreater;
import lab.commands.Save;
import lab.commands.Show;
import lab.commands.Update;
import lab.common.data.Person;
import lab.common.data.PersonCollectionManager;
import lab.common.json.LocalDateDeserializer;
import lab.common.json.PersonCollectionDeserializer;
import lab.common.json.PersonCollectionSerealizer;
import lab.common.json.PersonDeserializer;
import lab.common.json.PersonSerializer;
import lab.io.IOManager;
import lab.util.CommandManager;
import lab.util.CommandRunner;

public final class Client {

    private Client() {
    }

    public static void main(String[] args) {
        IOManager io = new IOManager();
        if (args.length == 0) {
            io.write("No arguments");
            return;
        }
        File file = new File(args[0]);
        Gson gson = createGson();
        Collection<Person> collection = readCollectionFromFile(file, gson, io);
        if (Objects.isNull(collection)) {
            return;
        }
        PersonCollectionManager manager = new PersonCollectionManager(collection);
        CommandManager commandManager = new CommandManager();
        CommandRunner runner = new CommandRunner(commandManager);
        commandManager.setCommands(createCommandsMap(manager, gson, runner, file));
        io.setReader(() -> {
            System.out.print("% ");
            return io.defaultConsoleReader().readLine();
        });
        runner.setIO(io);
        runner.run();
    }

    @SuppressWarnings("unchecked")
    public static Collection<Person> readCollectionFromFile(File file, Gson gson, IOManager io) {
        StringBuilder jsonBuilder = new StringBuilder();
        if (file.exists() && file.isFile()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line = bufferedReader.readLine();
                while (line != null) {
                    jsonBuilder.append(line);
                    jsonBuilder.append("\n");
                    line = bufferedReader.readLine();
                }
                String result = jsonBuilder.toString().trim();
                if (result.length() != 0) {
                    return gson.fromJson(result, HashSet.class);
                }
                io.write("File is empty");
            } catch (IOException | JsonParseException e) {
                io.write("Can't read collection from file");
            }
        } else {
            io.write("Can't find file");
        }
        return null;
    }

    public static Gson createGson() {
        return new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(HashSet.class,
                        new PersonCollectionSerealizer())
                .registerTypeAdapter(HashSet.class,
                        new PersonCollectionDeserializer())
                .registerTypeAdapter(Person.class, new PersonSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(Person.class, new PersonDeserializer()).create();
    }

    public static Map<String, Command> createCommandsMap(PersonCollectionManager manager, Gson gson,
            CommandRunner runner, File file) {
        HashMap<String, Command> commands = new HashMap<>();
        commands.put("help", new Help(commands.values()));
        commands.put("info", new Info(manager));
        commands.put("show", new Show(manager));
        commands.put("add", new Add(manager));
        commands.put("update", new Update(manager));
        commands.put("remove_by_id", new RemoveByID(manager));
        commands.put("clear", new Clear(manager));
        commands.put("save", new Save(manager, gson, file));
        commands.put("execute_script", new ExecuteScript(manager, runner));
        commands.put("exit", new Exit());
        commands.put("add_if_max", new AddIfMax(manager));
        commands.put("remove_greater", new RemoveGreater(manager));
        commands.put("history", new History(runner));
        commands.put("min_by_coordinates", new MinByCoordinates(manager));
        commands.put("group_counting_by_passport_id", new GroupCountingByPassportID(manager));
        commands.put("filter_less_than_nationality", new FilterLessThanNationality(manager));
        return commands;
    }
}
