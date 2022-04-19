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
                "this is admin type"
                );
    }

    public AdminType(Identifier[] iden) {
        super(iden,"Type: ADMIN\n" +
                "Access Rights: ADMIN, USER, ROLE, VIEWER, EDITOR","Has access to administrative permissions, all kpis and management tools");
    }


    // No rules. Any KPI allowed
    @Override
    public boolean check(String kpi) {
        return true;
    }

}
