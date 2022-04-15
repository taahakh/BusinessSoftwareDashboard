import java.awt.*;

public class AnalystType extends EmployeeLadder{

    private final String[] links = {
            Conts.ANALYST, Conts.ANALYST_LEADER
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
    public boolean compareTo(Object obj) {
        return obj instanceof AnalystType;
    }

    @Override
    Frame features() {
        return null;
    }

    public String[] getLinks() {
        return links;
    }
}
