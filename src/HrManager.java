import java.awt.*;

public class HrManager extends Employee{

    public HrManager(String title){
        super(title, Conts.HR, "");
    }

    @Override
    public boolean compare(Object obj) {
        return obj instanceof HrManager;
    }

    @Override
    void formLayout(Panel panel) {
        Operations.addManagementToPanel(panel);
    }

}
