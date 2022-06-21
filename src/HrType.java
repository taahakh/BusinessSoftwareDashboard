public class HrType extends KPIGroup {

    public HrType() {
        super(new Identifier[]{
                Identifier.VIEWER,
                Identifier.EDITOR,
        }, "HumanResources group. Access Hr kpis", "You can only add marketing and human resources to this group");
    }

    @Override
    public boolean check(String kpi) {
        return kpi.equals(Conts.MARKETING) || kpi.equals(Conts.HUMAN_RESOURCES);
    }
}
