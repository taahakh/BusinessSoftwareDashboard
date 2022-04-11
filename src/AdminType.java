import java.awt.*;

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
    boolean compareTo(Object obj) {
        if(obj instanceof AdminType){
            return true;
        }
        return false;
    }

    @Override
    String description() {
        return "this is admin type";
    }

    @Override
    public Panel showRights() {
        Panel p = Panels.basicPanel();
        return p;
    }

    @Override
    public Frame showKpis() {
        Frame f = Panels.basicWindow();

        return f;
    }
}
