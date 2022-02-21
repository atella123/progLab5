package lab5.common.commands;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import lab5.common.data.Person;
import lab5.common.util.CollectionManager;
import lab5.common.util.IOManager;

public class Save extends CollectionCommand {

    private final String man = "save : сохранить коллекцию в файл";
    private Gson gson;

    public Save(CollectionManager<Person> manager, Gson gson) {
        super(manager);
        this.gson = gson;
    }

    public Save(IOManager io, CollectionManager<Person> manager, Gson gson) {
        super(io, manager);
        this.gson = gson;
    }

    @Override
    public CommandResponse execute(String arg) {
        String json = gson.toJson(this.getManager().getCollection());
        try (FileWriter fileWriter = new FileWriter(arg)) {
            fileWriter.write(json);
        } catch (IOException e) {
            return CommandResponse.CANT_USE_FILE;
        } catch (NullPointerException e) {
            return CommandResponse.FILE_NOT_FOUND;
        }
        return CommandResponse.SUCCESS;
    }

    @Override
    public String toString() {
        return "Save";
    }

    @Override
    public String getMan() {
        return man;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((gson == null) ? 0 : gson.hashCode());
        result = prime * result + ((man == null) ? 0 : man.hashCode());
        result = prime * result + ((this.getManager() == null) ? 0 : this.getManager().hashCode());
        return result;
    }

}
