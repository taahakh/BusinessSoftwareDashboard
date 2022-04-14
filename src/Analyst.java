import java.awt.*;

public class Analyst extends Employee{
    public Analyst(){
        super(new AnalystType());
    }

    @Override
    Button load() {
        return null;
    }

    @Override
    public boolean compareTo(Object obj) {
        return obj instanceof Analyst;
    }

    @Override
    public Frame features() {
        return null;
    }

    @Override
    public String description() {
        return "null";
    }

    public Analyst(String title) {
        super(title, new AnalystType());
    }
}
