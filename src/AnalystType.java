import java.awt.*;

public class AnalystType extends EmployeeLadder{

    private final String[] links = {
            Conts.ANALYST, Conts.ANALYST_LEADER, Conts.ANALYST_SALES
    };

    private static final String DESC = "this is for analysts";
    private static final String METRIC = "Contains KPI types for Analysts";

    public AnalystType() {
        super(
                new Identifier[]{
                        Identifier.VIEWER,
                        Identifier.EDITOR
                },
                DESC,
                METRIC
        );
    }

    public AnalystType(Identifier[] access) {
        super(access, DESC, METRIC);
    }

    @Override
    public boolean compare(Object obj) {
        return obj instanceof AnalystType;
    }

    @Override
    boolean check(String kpi) {
        return kpi.equals(Conts.MARKETING) || kpi.equals(Conts.SALES) || kpi.equals(Conts.INVENTORY);
    }

    public String[] getLinks() {
        return links;
    }
}
