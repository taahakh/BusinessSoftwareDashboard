import java.awt.*;

public class HrManager extends Employee{

    public HrManager(String title){
        super(title, Conts.HR, "Gain access to all management controls such as finance and employee list - as well as HR related KPIs");
    }

    @Override
    void formLayout(Panel panel) {
        Operations.addManagementToPanel(panel);
    }

}
