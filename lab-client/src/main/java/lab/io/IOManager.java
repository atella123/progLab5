package lab.io;

import java.util.Scanner;

public class IOManager {

    private Reader reader;
    private Writter writter;

    public IOManager(Writter writter) {
        reader = () -> new Scanner(System.in).nextLine();
        this.writter = writter;
    }

    public IOManager(Reader reader) {
        this.reader = reader;
        writter = (String s) -> System.out.println(s);
    }

    public IOManager(Reader reader, Writter writter) {
        this.reader = reader;
        this.writter = writter;
    }

    public IOManager() {
        reader = () -> new Scanner(System.in).nextLine();
        writter = (String s) -> System.out.println(s);
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Writter getWritter() {
        return writter;
    }

    public void setWritter(Writter writter) {
        this.writter = writter;
    }

    public void setIO(Reader newReader, Writter newWritter) {
        this.setReader(newReader);
        this.setWritter(newWritter);
    };

    public void write(String s) {
        writter.write(s);
    };

    public String readLine() {
        return reader.readLine();
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        IOManager other = (IOManager) obj;
        if (reader == null) {
            if (other.reader != null) {
                return false;
            }
        } else if (!reader.equals(other.reader)) {
            return false;
        }
        if (writter == null) {
            if (other.writter != null) {
                return false;
            }
        } else if (!writter.equals(other.writter)) {
            return false;
        }
        return true;
    };

}
