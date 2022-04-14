import java.awt.*;

public class AnalystLeader extends Employee {

    public AnalystLeader(String title){
        super(title, new AnalystType(
                new Identifier[]{
                        Identifier.VIEWER,
                        Identifier.EDITOR,
                        Identifier.ROLE,
                        Identifier.USER
                }
        ));
    }

    @Override
    public boolean compareTo(Object obj) {
        return obj instanceof AnalystLeader;
    }

    @Override
    public Frame features() {
        return null;
    }

    @Override
    public String description() {
        return "Head leader of analysts of the company";
    }

    @Override
    Button load() {
        return null;
    }

    @Override
    void formLayout() {

    }
}
