import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Admin extends Employee{

    public Admin(String title){
        super(title, new AdminType(), "Access rights etc");
    }

    @Override
    public boolean compareTo(Object obj) {
        return obj instanceof Admin;
    }

    @Override
    void formLayout(Panel panel) {
        panel.add(printKPIGroups());
        panel.add(printRanks());
    }

    public Button printKPIGroups() {
        Button b = new Button("Print KPI groups");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                TextArea area = new TextArea(kpiGroups());
                area.setEditable(false);
                p.add(area);
                p.launch();
            }
        });
        return b;
    }

    public Button printRanks() {
        Button b = new Button("Print employee ranks");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                TextArea area = new TextArea(employeeGroups());
                area.setEditable(false);
                p.add(area);
                p.launch();
            }
        });
        return b;
    }


    private String kpiGroups() {
        Business b = Settings.getBusiness();
        String items = "";
        for (String ranks : Settings.availableRanks) {
            EmployeeLadder el = Settings.getType(ranks);
            if(el != null) {
                items += ranks+"\n";
                for (Employee e : b.getEmployees()) {
                    if(e.getLadder().compareTo(el)){
                        items += "--->" + e.getTitle() +"\n";
                    }
                }
                items += "------------------------\n";
            }
        }
        return items;
    }

    private String employeeGroups() {
        StringBuilder items = new StringBuilder();
        for (String rank : Settings.availableEmployees) {
            items.append(rank).append("\n");
            items.append(Operations.viewEmployees(rank));
            items.append("|*--------------------------------*|\n\n");
        }
        return items.toString();
    }
}
