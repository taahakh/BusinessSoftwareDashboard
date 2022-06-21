/*
* Note we cannot minimise copied string text
* */
public class AdminType extends KPIGroup {

    public AdminType(){
        super(new Identifier[]{
                Identifier.ADMIN,
                Identifier.USER,
                Identifier.ROLE,
                Identifier.VIEWER,
                Identifier.EDITOR,
        },
                "Has access to administrative permissions, all kpis and management tools",
                "You have access to ADMIN, USER, ROLE features"
                );
    }

    public AdminType(Identifier[] iden) {
        super(iden,"Has access to administrative permissions, all kpis and management tools","You have access to ADMIN, USER, ROLE features");
    }


    // No rules. Any KPI allowed
    @Override
    public boolean check(String kpi) {
        return true;
    }

}
