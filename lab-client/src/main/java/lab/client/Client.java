package lab.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
import lab.common.json.LocalDateDesetializer;
import lab.common.json.PersonCollectionDeserializer;
import lab.common.json.PersonCollectionSereailzer;
import lab.common.json.PersonDeserializer;
import lab.common.json.PersonSerealizer;
import lab.io.IOManager;
import lab.io.Reader;
import lab.util.CommandManager;
import lab.util.CommandRunner;

public final class Client {

    private Client() {
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        String json;
        StringBuilder jsonBuilder = new StringBuilder();
        Collection<Person> collection = new HashSet<>();
        Gson gson = createGson(collection);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                jsonBuilder.append(line + "\n");
                line = bufferedReader.readLine();
            }

            json = jsonBuilder.toString();
            // this cast is ok
            collection = gson.fromJson(json, collection.getClass());
        } catch (Exception e) {
            System.out.println("Error when reading file");
            return;
        }

        PersonCollectionManager manager = new PersonCollectionManager(collection);
        CommandManager commandManager = new CommandManager();
        CommandRunner runner = new CommandRunner(commandManager);

        commandManager.setCommands(createCommandsMap(manager, gson, commandManager,
                runner));
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

    public static Gson createGson(Collection<Person> collection) {
        return new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(collection.getClass(),
                        new PersonCollectionSereailzer())
                .registerTypeAdapter(collection.getClass(),
                        new PersonCollectionDeserializer())
                .registerTypeAdapter(Person.class, new PersonSerealizer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDesetializer())
                .registerTypeAdapter(Person.class, new PersonDeserializer()).create();
    }

    public static HashMap<String, Command> createCommandsMap(PersonCollectionManager manager, Gson gson,
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
        cmds.put("execute_script", new ExecuteScript(manager, runner));
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
