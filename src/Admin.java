import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        panel.add(print("Print kpi groups", kpiGroups()));
        panel.add(print("Print employee ranks", employeeGroups()));
    }

    public Button print(String name, String val) {
        Button b = new Button(name);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                TextArea area = new TextArea(val);
                area.setEditable(false);
                p.add(area);
                p.launch();
            }
        });
        return b;
    }


    private String kpiGroups() {
        Business b = Settings.getBusiness();
        StringBuilder items = new StringBuilder();
        for (String ranks : Settings.getAvailableRanks()) {
            EmployeeLadder el = Settings.getType(ranks);
            if(el != null) {
                items.append(ranks).append("\n");
                for (Employee e : b.getEmployees()) {
                    if(e.getLadder().compareTo(el)){
                        items.append("--->").append(e.getTitle()).append("\n");
                    }
                }
                items.append("------------------------\n");
            }
        }
        return items.toString();
    }

    private String employeeGroups() {
        StringBuilder items = new StringBuilder();
        for (String rank : Settings.getAvailableEmployees()) {
            items.append(rank).append("\n");
            items.append(Operations.viewEmployees(rank));
            items.append("|*--------------------------------*|\n\n");
        }
        return items.toString();
    }
}
