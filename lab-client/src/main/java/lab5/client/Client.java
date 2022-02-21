package lab5.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lab5.common.commands.Add;
import lab5.common.commands.AddIfMax;
import lab5.common.commands.Clear;
import lab5.common.commands.Command;
import lab5.common.commands.ExecuteScript;
import lab5.common.commands.Exit;
import lab5.common.commands.FilterLessThanNationality;
import lab5.common.commands.GroupCountingByPassportID;
import lab5.common.commands.Help;
import lab5.common.commands.History;
import lab5.common.commands.Info;
import lab5.common.commands.MinByCoordinates;
import lab5.common.commands.RemoveByID;
import lab5.common.commands.RemoveGreater;
import lab5.common.commands.Save;
import lab5.common.commands.Show;
import lab5.common.commands.Update;
import lab5.common.data.Coordinates;
import lab5.common.data.Location;
import lab5.common.data.Person;
import lab5.common.json.CoordinatesDeserializer;
import lab5.common.json.CoordinatesSerealizer;
import lab5.common.json.LocationDeserealizer;
import lab5.common.json.LocationSerealizer;
import lab5.common.json.PersonCollectionSereailzer;
import lab5.common.json.PersonDeserializer;
import lab5.common.json.PersonSerealizer;
import lab5.common.json.personCollectionDeserializer;
import lab5.common.util.CollectionManager;
import lab5.common.util.CommandManager;
import lab5.common.util.CommandRunner;
import lab5.common.util.IOManager;
import lab5.common.util.Reader;

public final class Client {
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

        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(collection.getClass(),
                        new PersonCollectionSereailzer())
                .registerTypeAdapter(collection.getClass(),
                        new personCollectionDeserializer())
                .registerTypeAdapter(Person.class, new PersonSerealizer())
                .registerTypeAdapter(Person.class, new PersonDeserializer())
                .registerTypeAdapter(Location.class, new LocationSerealizer())
                .registerTypeAdapter(Location.class, new LocationDeserealizer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesSerealizer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesDeserializer()).create();

        collection = gson.fromJson(json, collection.getClass());

        CollectionManager<Person> manager = new CollectionManager<>(collection);
        CommandManager commandManager = new CommandManager();
        CommandRunner runner = new CommandRunner(commandManager);

        Map<String, Command> cmds = new HashMap<>();
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
        // BasicParser.parseWhileError(new IOManager(), BasicParser::parseInt, "kk");
        commandManager.setCommands(cmds);
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
}
