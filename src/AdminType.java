import java.awt.*;

public class AdminType extends EmployeeLadder{

    public AdminType(){
        super(new Identifier[]{
                Identifier.ADMIN,
                Identifier.USER,
                Identifier.ROLE,
                Identifier.VIEWER,
                Identifier.EDITOR,
        },
                "Type: ADMIN\n" +
                        "Access Rights: ADMIN, USER, ROLE, VIEWER, EDITOR",
                "this is admin type"
                );
    }

    public AdminType(Identifier[] iden) {
        super(iden,"","");
    }

    @Override
    public boolean compareTo(Object obj) {
        return obj instanceof AdminType;
    }

    @Override
    Frame features() {
        return null;
    }

    @Override
    boolean check(String kpi) {
        return true;
    }
}
