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
        Panel p = basicPanel();
        p.add(new TextField("hello"));
        return p;
    }

    @Override
    Panel showKpis() {
        return null;
    }

    @Override
    public String showInfoMetric() {
        return "Analyst";
    }
}
