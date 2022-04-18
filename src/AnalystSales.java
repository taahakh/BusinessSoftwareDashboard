import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AnalystSales extends Employee {

    private final String description = "View all sales kpi's that belong to the analyst group";

    public AnalystSales(String title) {
        super(title, Conts.ANALYST, "Analyst Sales stuff");
    }

    @Override
    public ArrayList<Button> showKPIButtons() {
        return generateKPIButtons(this, Conts.SALES, true);
    }

    @Override
    void formLayout(Panel panel) {
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

    @Override
    public boolean compare(Object obj) {
        return false;
    }

    public int printTotalSales() {
        int sales = 0;
        Business b = Settings.getBusiness();
        ArrayList<KPI> items = b.returnLadderKPI(Conts.ANALYST);
        System.out.println(items);
        for(KPI k : items) {
            if(k instanceof Sales){
                Sales s = (Sales) k;
                sales += s.getSales();
            }
        }
        return sales;
    }
}
