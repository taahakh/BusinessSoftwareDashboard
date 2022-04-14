public class AdminType extends EmployeeLadder{

    public AdminType(){
        super(new Identifier[]{
                Identifier.ADMIN,
                Identifier.USER,
                Identifier.ROLE,
                Identifier.VIEWER,
                Identifier.EDITOR,
        });
    }

    @Override
    public String showInfoMetric() {
        return "Type: ADMIN\n" +
                "Access Rights: ADMIN, USER, ROLE, VIEWER, EDITOR";
    }

    @Override
    public boolean compareTo(Object obj) {
        return obj instanceof AdminType;
    }

    @Override
    public String description() {
        return "this is admin type";
    }
}
