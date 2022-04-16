import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AnalystSales extends Employee {

    public AnalystSales(String title) {
        super(title, new AnalystType(), "Analyst Sales stuff");
    }

    @Override
    public ArrayList<Button> showKPIButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        ArrayList<KPI> kpis = Settings.getBusiness().getKPILadderList(new AnalystType());
        for(KPI x : kpis) {
            if(x.compareTo(Conts.SALES)){
                Button button = new Button(x.getClassName() + ": " + x.getIndicatorName());
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        x.showKpi(true).setVisible(true);
                    }
                });
                buttons.add(button);
            }
        }
        return buttons;
    }

    @Override
    void formLayout(Panel panel) {

    }

    @Override
    public boolean compareTo(Object obj) {
        return false;
    }
}
