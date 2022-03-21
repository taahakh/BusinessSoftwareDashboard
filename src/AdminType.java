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
        return null;
    }
}
