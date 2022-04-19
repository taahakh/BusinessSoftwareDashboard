import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends Employee{

    public Admin(String title){
        super(title, new AdminType(), "Complete access over the business and hasIdentifier access to all KPIs and Operations");
    }

    @Override
    void formLayout(Panel panel) {
        panel.setLayout(new GridLayout(0,1));
        panel.add(print("Print kpi groups", kpiGroups()));
        panel.add(print("Print employee ranks", employeeGroups()));
        Operations.addManagementToPanel(panel);
    }

    public Button print(String name, String val) {
        Button b = new Button(name);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup popup = new Popup();
                TextArea area = new TextArea(val);
                area.setEditable(false);
                popup.add(area);
                popup.launch();
            }
        });
        return b;
    }


    // Prints who belong to which group
    private String kpiGroups() {
        Business b = Settings.getBusiness();
        StringBuilder items = new StringBuilder();
        for (String ranks : Settings.getAvailableRanks()) {
            KPIGroup group = Settings.getType(ranks);
            if(group != null) {
                items.append(ranks).append("\n");
                for (Employee e : b.getEmployees()) {
                    if(e.getGroup().compare(group)){
                        items.append("--->").append(e.getUsername()).append("\n");
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
