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
    public Panel showRights() {
        Panel p = Panels.basicPanel();
        p.add(new Label("hello"));
        return p;
    }

    @Override
    public Panel showKpis() {
        Panel p = Panels.basicPanel();
        return p;
    }

    @Override
    public String showInfoMetric() {
        return "Analyst";
    }
}
