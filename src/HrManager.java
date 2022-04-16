import java.awt.*;

public class HrManager extends Employee{

    public HrManager(String title){
        super(title, new HrType(), "", Settings.getBusiness());
    }

    @Override
    public boolean compareTo(Object obj) {
        return obj instanceof HrManager;
    }

    @Override
    void formLayout(Panel panel) {

    }
}
