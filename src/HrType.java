public class HrType extends KPIGroup {

    public HrType() {
        super(new Identifier[]{
                Identifier.VIEWER,
                Identifier.EDITOR,
        }, "description", "You can only add marketing and human resources to this group");
    }

//    @Override
//    public boolean compare(Object obj) {
//        return obj instanceof HrType;
//    }

    @Override
    boolean check(String kpi) {
        return kpi.equals(Conts.MARKETING) || kpi.equals(Conts.HUMAN_RESOURCES);
    }
}
