import java.awt.*;
import java.util.ArrayList;

public class HrViewer extends Employee{

    // This employee doesn't belong to the HrPool
    // This employee can view all HumanResources from all employee types
    // Thats why it hasIdentifier been assigned AdminType
    // AnalystSales on the other hand is just an employee that can only see Sales for its OWN group e.g Sales
    // This also means that it doesn't inherit HrType features

    private final String description = "HumanResources KPI's can be viewed here only. All the HR KPI's also belong in the other types such as standard";

    public HrViewer(String title) {
        super(title, new AdminType(new Identifier[]{
                Identifier.VIEWER
        }),"View all HR kpis here across the business. This means that it doesn't just only view HR kpi's in the HR group");
    }

//    @Override
//    public boolean compare(Object obj) {
//        return obj instanceof HrViewer;
//    }

    @Override
    void formLayout(Panel panel) {
        panel.add(new Label(description));
    }

    @Override
    public ArrayList<Button> showKPIButtons(){
        return viewAllHr();
    }

    public ArrayList<Button> viewAllHr() {
        return generateKPIButtons(this, Conts.HUMAN_RESOURCES, false);
    }


}
