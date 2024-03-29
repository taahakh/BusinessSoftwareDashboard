/*
* No rules define what KPI type can go through
* */

public class StandardType extends KPIGroup {

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
    public boolean check(String kpi) {
        return true;
    }
}
