import java.awt.*;
import java.util.ArrayList;

public class HrViewer extends Employee{

    // This employee doesn't belong to the HrPool
    // This employee can view all HumanResources from all employee types
    // Thats why it has been assigned AdminType
    // AnalystSales on the other hand is just an employee that can only see Sales for its OWN group e.g Sales
    // This also means that it doesn't inherit HrType features
    public HrViewer(String title) {
        super(title, new AdminType(new Identifier[]{
                Identifier.VIEWER
        }),"", Settings.getBusiness());
    }

    @Override
    public boolean compareTo(Object obj) {
        return obj instanceof HrViewer;
    }

    @Override
    void formLayout(Panel panel) {

    }

    @Override
    public ArrayList<Button> showKPIButtons(){
        return viewAllHr();
    }

    public ArrayList<Button> viewAllHr() {
//        getLadder().
//        return generateKPIButtons(Conts.ADMIN, Conts.HUMAN_RESOURCES, false);
        return generateKPIButtons(this, Conts.HUMAN_RESOURCES, false);

    }
}
