import java.awt.*;

public class Analyst extends Employee{

    public Analyst(String title) {
        super(title, Conts.ANALYST, "Analyst access to specific sets of analyst KPI's such as Sales and Inventory");
    }

    public Analyst(String title, KPIGroup el, String description) {
        super(title, el, description);
    }


    public Analyst(String title, String description) {
        super(title, Conts.ANALYST, description);
    }

    @Override
    void formLayout(Panel panel) {
        panel.add(viewFinances());
    }

    // View finances of the business
    public Button viewFinances() {
        Finance type = new Finance("");
        for (Management m : Settings.getBusiness().getManagement()) {
            if(m.compare(type)){
                return m.viewWindow();
            }
        }
        return new Button();
    }

}
