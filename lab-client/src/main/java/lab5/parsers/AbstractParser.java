package lab5.parsers;

import java.util.Scanner;

import lab5.common.util.LineReader;
import lab5.common.util.Writter;

public abstract class AbstractParser {

    protected Writter writter = System.out::println;
    protected LineReader reader = new Scanner(System.in)::nextLine;

    public AbstractParser(Writter writter, LineReader reader) {
        this.writter = writter;
        this.reader = reader;
    }

    public Writter getWritter() {
        return writter;
    }

    public void setWritter(Writter writter) {
        this.writter = writter;
    }

    public LineReader getReader() {
        return reader;
    }

    public void setReader(LineReader reader) {
        this.reader = reader;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((reader == null) ? 0 : reader.hashCode());
        result = prime * result + ((writter == null) ? 0 : writter.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractParser other = (AbstractParser) obj;
        if (reader == null) {
            if (other.reader != null)
                return false;
        } else if (!reader.equals(other.reader))
            return false;
        if (writter == null) {
            if (other.writter != null)
                return false;
        } else if (!writter.equals(other.writter))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AbstractParser";
    }

}
