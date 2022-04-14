import java.awt.*;

public class DefaultEmployee extends Employee{
    public DefaultEmployee(){
        super();
    }

    @Override
    Button load() {
        return null;
    }

    @Override
    public boolean compareTo(Object obj) {
        return obj instanceof DefaultEmployee;
    }

    @Override
    public Frame features() {
        return null;
    }

    @Override
    public String description() {
        return "null";
    }
}
