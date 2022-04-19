import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AnalystSales extends Analyst {

    private final String description = "View all sales kpi's that belong to the analyst group";

    public AnalystSales(String title) {
        super(title,"View all sales kpi's that belong to the analyst group");
    }

    @Override
    public ArrayList<Button> showKPIButtons() {
        return generateKPIButtons(this, Conts.SALES, true);
    }

    @Override
    void formLayout(Panel panel) {
        super.formLayout(panel);
        Button b = new Button("Print total sales");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.setLabel("Print total sales: " + printTotalSales());
            }
        });
        panel.add(b);
        panel.add(new Label(description));
    }

    public int printTotalSales() {
        int sales = 0;
        Business b = Settings.getBusiness();
        ArrayList<KPI> items = b.returnLadderKPI(Conts.ANALYST);
        for(KPI k : items) {
            // Using instanceof due to the fact its a unique function for Sales
            // There was no point in introducing a polymorphic way to retrieve sales just to add extra baggage to the code
            if(k instanceof Sales){
                Sales s = (Sales) k;
                sales += s.getSales();
            }
        }
        return sales;
    }
}
