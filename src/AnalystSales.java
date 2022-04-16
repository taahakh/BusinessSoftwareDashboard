import java.awt.*;
import java.util.ArrayList;

public class AnalystSales extends Employee {

    public AnalystSales(String title) {
        super(title, Conts.ANALYST, "Analyst Sales stuff");
    }

    @Override
    public ArrayList<Button> showKPIButtons() {
        return generateKPIButtons(this, Conts.SALES, true);
    }

    @Override
    void formLayout(Panel panel) {

    }

    @Override
    public boolean compareTo(Object obj) {
        return false;
    }
}
