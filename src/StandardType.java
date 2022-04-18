public class StandardType extends EmployeeLadder{

    public StandardType(){
        super(new Identifier[]{
                        Identifier.VIEWER,
                        Identifier.EDITOR,
                },
                "Standard employee. No KPI rules",
                "Standard type"
        );
    }

    @Override
    public boolean compare(Object obj) {
        return obj instanceof StandardType;
    }

    @Override
    boolean check(String kpi) {
        return true;
    }
}
