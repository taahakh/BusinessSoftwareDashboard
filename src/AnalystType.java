public class AnalystType extends KPIGroup {

    private final String[] links = {
            Conts.ANALYST, Conts.ANALYST_LEADER, Conts.ANALYST_SALES
    };

    private static final String DESC = "You can only add Sales and Inventory to the analyst group";
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
    public boolean check(String kpi) {
        return kpi.equals(Conts.MARKETING) || kpi.equals(Conts.SALES) || kpi.equals(Conts.INVENTORY);
    }

    public String[] getLinks() {
        return links;
    }
}
