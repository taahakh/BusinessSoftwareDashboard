public class StandardType extends EmployeeLadder{

    public StandardType(){
        super(new Identifier[]{
                        Identifier.VIEWER,
                        Identifier.EDITOR,
                },
                "Standard employee. No KPI rules. Any kpis can be added here",
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
