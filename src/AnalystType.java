import java.awt.*;

public class AnalystType extends EmployeeLadder{

    public AnalystType() {
        super(
                new Identifier[]{
                        Identifier.VIEWER,
                        Identifier.EDITOR
                }
        );
    }

    public AnalystType(Identifier[] access) {
        super(access);
    }

    @Override
    boolean compareTo(Object obj) {
        return obj instanceof AnalystType;
    }

    @Override
    String description() {
        return "this is for analysts";
    }

    @Override
    public Panel showRights() {
        Panel p = Panels.basicPanel();
        p.add(new Label("hello"));
        return p;
    }

    @Override
    public Frame showKpis() {
        Frame f = Panels.basicWindow();
        return f;
    }

    @Override
    public String showInfoMetric() {
        return "Contains KPI types for Analysts";
    }
}
