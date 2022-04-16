import java.awt.*;
import java.util.ArrayList;

public class AnalystSales extends Employee {

    public AnalystSales(String title) {
        super(title, new AnalystType(), "Analyst Sales stuff");
    }

    @Override
    public ArrayList<Button> showKPIButtons() {
        return generateKPIButtons(Conts.ANALYST, Conts.SALES, true);
    }

    @Override
    void formLayout(Panel panel) {

    }

    @Override
    public boolean compareTo(Object obj) {
        return false;
    }
}
