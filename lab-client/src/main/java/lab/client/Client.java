package lab.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lab.common.commands.Add;
import lab.common.commands.AddIfMax;
import lab.common.commands.Clear;
import lab.common.commands.Command;
import lab.common.commands.ExecuteScript;
import lab.common.commands.Exit;
import lab.common.commands.FilterLessThanNationality;
import lab.common.commands.GroupCountingByPassportID;
import lab.common.commands.Help;
import lab.common.commands.History;
import lab.common.commands.Info;
import lab.common.commands.MinByCoordinates;
import lab.common.commands.RemoveByID;
import lab.common.commands.RemoveGreater;
import lab.common.commands.Save;
import lab.common.commands.Show;
import lab.common.commands.Update;
import lab.common.data.Coordinates;
import lab.common.data.Location;
import lab.common.data.Person;
import lab.common.json.CoordinatesDeserializer;
import lab.common.json.CoordinatesSerealizer;
import lab.common.json.LocationDeserealizer;
import lab.common.json.LocationSerealizer;
import lab.common.json.PersonCollectionSereailzer;
import lab.common.json.PersonDeserializer;
import lab.common.json.PersonSerealizer;
import lab.common.json.PersonCollectionDeserializer;
import lab.common.util.CollectionManager;
import lab.common.util.CommandManager;
import lab.common.util.CommandRunner;
import lab.common.util.IOManager;
import lab.common.util.Reader;

public final class Client {

    private Client() {
    }

    public static void main(String[] args) {
        String json;
        StringBuilder jsonBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                jsonBuilder.append(line + "\n");
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error when reading file");
            return;
        }

        json = jsonBuilder.toString();

        Collection<Person> collection = new HashSet<>();

        Gson gson = createGson();

        collection = gson.fromJson(json, collection.getClass());

        CollectionManager<Person> manager = new CollectionManager<>(collection);
        CommandManager commandManager = new CommandManager();
        CommandRunner runner = new CommandRunner(commandManager);

        commandManager.setCommands(createCommandsMap(manager, gson, commandManager, runner));
        Scanner scanner = new Scanner(System.in);
        IOManager io = new IOManager(new Reader() {
            public String readLine() {
                System.out.print("$ ");
                return scanner.nextLine();
            }
        });
        runner.setIo(io);
        runner.run();
        scanner.close();
    }

    public static Gson createGson() {
        return new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(new HashSet<Person>().getClass(),
                        new PersonCollectionSereailzer())
                .registerTypeAdapter(new HashSet<Person>().getClass(),
                        new PersonCollectionDeserializer())
                .registerTypeAdapter(Person.class, new PersonSerealizer())
                .registerTypeAdapter(Person.class, new PersonDeserializer())
                .registerTypeAdapter(Location.class, new LocationSerealizer())
                .registerTypeAdapter(Location.class, new LocationDeserealizer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesSerealizer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesDeserializer()).create();
    }

    public static HashMap<String, Command> createCommandsMap(CollectionManager<Person> manager, Gson gson,
            CommandManager commandManager, CommandRunner runner) {
        HashMap<String, Command> cmds = new HashMap<>();
        cmds.put("help", new Help(cmds.values()));
        cmds.put("info", new Info(manager));
        cmds.put("show", new Show(manager));
        cmds.put("add", new Add(manager));
        cmds.put("update", new Update(manager));
        cmds.put("remove_by_id", new RemoveByID(manager));
        cmds.put("clear", new Clear(manager));
        cmds.put("save", new Save(manager, gson));
        cmds.put("execute_script", new ExecuteScript(manager, commandManager));
        cmds.put("exit", new Exit());
        cmds.put("add_if_max", new AddIfMax(manager));
        cmds.put("remove_greater", new RemoveGreater(manager));
        cmds.put("history", new History(runner));
        cmds.put("min_by_coordinates", new MinByCoordinates(manager));
        cmds.put("group_counting_by_passport_i_d", new GroupCountingByPassportID(manager));
        cmds.put("filter_less_than_nationality", new FilterLessThanNationality(manager));
        return cmds;
    }
}
