package lab.common.exceptions;

public class IsNotEnumClass extends RuntimeException {

    public IsNotEnumClass() {
    }

    public IsNotEnumClass(String arg0) {
        super(arg0);
    }

    public IsNotEnumClass(Throwable arg0) {
        super(arg0);
    }

    public IsNotEnumClass(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public IsNotEnumClass(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
