/*
* Note that we cannot minimise the 4 text strings
* */
public class AnalystType extends KPIGroup {

    private final String[] links = {
            Conts.ANALYST, Conts.ANALYST_LEADER, Conts.ANALYST_SALES
    };

    public AnalystType() {
        super(
                new Identifier[]{
                        Identifier.VIEWER,
                        Identifier.EDITOR
                },
                "You can only add Sales and Inventory to the analyst group",
                "Contains KPI types for Analysts"
        );
    }

    public AnalystType(Identifier[] access) {
        super(access, "You can only add Sales and Inventory to the analyst group", "Contains KPI types for Analysts");
    }


    @Override
    public boolean check(String kpi) {
        return kpi.equals(Conts.MARKETING) || kpi.equals(Conts.SALES) || kpi.equals(Conts.INVENTORY);
    }

    public String[] getLinks() {
        return links;
    }
}
