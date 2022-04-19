import java.awt.*;

public class Analyst extends Employee{

    public Analyst(String title) {
        super(title, Conts.ANALYST, "Analyst access to specific sets of analyst KPI's such as Sales and Inventory");
    }

    public Analyst(String title, EmployeeLadder el, String description) {
        super(title, el, description);
    }

    @Override
    void formLayout(Panel panel) {
        panel.add(viewFinances());
    }

    @Override
    public boolean compare(Object obj) {
        return obj instanceof Analyst;
    }

    public Button viewFinances() {
        Finance finance = null;
        for (Management m : Settings.getBusiness().getManagement()) {
            if(m instanceof Finance){
                finance = (Finance) m;
            }
        }
        if(finance != null){
            return finance.viewWindow();
        }

        return new Button();
    }

}
