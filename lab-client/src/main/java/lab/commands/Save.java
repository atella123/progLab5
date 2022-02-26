package lab.commands;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import lab.common.data.PersonCollectionManager;
import lab.io.IOManager;

public final class Save extends CollectionCommand {

    private Gson gson;

    public Save(PersonCollectionManager manager, Gson gson) {
        super(manager);
        this.gson = gson;
    }

    public Save(IOManager io, PersonCollectionManager manager, Gson gson) {
        super(io, manager);
        this.gson = gson;
    }

    @Override
    public CommandResponse execute(String arg) {
        String json = gson.toJson(this.getManager().getCollectionCopy());
        try (FileWriter fileWriter = new FileWriter(arg)) {
            fileWriter.write(json);
        } catch (IOException e) {
            return new CommandResponse(CommandResult.ERROR, "Can't write to file");
        } catch (NullPointerException e) {
            return new CommandResponse(CommandResult.ERROR, "File not found");
        }
        return new CommandResponse(CommandResult.SUCCESS);
    }

    @Override
    public String toString() {
        return "Save";
    }

    @Override
    public String getMan() {
        return "save : сохранить коллекцию в файл";
    }
}
