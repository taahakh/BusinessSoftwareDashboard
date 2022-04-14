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
    public boolean compareTo(Object obj) {
        return obj instanceof AnalystType;
    }

    @Override
    public String description() {
        return "this is for analysts";
    }

    @Override
    public String showInfoMetric() {
        return "Contains KPI types for Analysts";
    }
}
