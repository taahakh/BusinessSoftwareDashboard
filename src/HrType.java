import java.awt.*;

public class HrType extends EmployeeLadder{

    public HrType() {
        super(new Identifier[]{
                Identifier.VIEWER,
                Identifier.EDITOR,
        }, "description", "showInfoMetric");
    }

    @Override
    public boolean compareTo(Object obj) {
        return obj instanceof HrType;
    }

    @Override
    boolean check(String kpi) {
        return kpi.equals(Conts.MARKETING) || kpi.equals(Conts.HUMAN_RESOURCES);
    }
}
