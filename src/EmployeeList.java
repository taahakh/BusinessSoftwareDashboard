import java.awt.*;
import java.util.ArrayList;

public class EmployeeList extends Hiring {

    private ArrayList<String> description;

    public EmployeeList(String name) {
        super(name);
        description = new ArrayList<>();
    }

    @Override
    public boolean compare(Object obj) {
        return obj instanceof EmployeeList;
    }

    @Override
    MangementFrame window() {
        return null;
    }

    @Override
    public void removeItem(String name) {
        for (int i=0; i<getList().size(); i++){
            if(getList().get(i).equals(name)){
                getList().remove(i);
                description.remove(i);
                return;
            }
        }
    }

    public String viewEmployeesOnly(){
        return super.viewList();
    }

}
