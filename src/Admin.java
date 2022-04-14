import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class Admin extends Employee{

    public Admin(String title){
        super(title, new AdminType());
    }

    @Override
    public boolean compareTo(Object obj) {
        return obj instanceof Admin;
    }

    @Override
    public Frame features() {
        return null;
    }

    @Override
    public String description() {
        return "Access rights etc";
    }

    @Override
    Button load() {
        return null;
    }

    @Override
    void formLayout() {

    }

    public Button printRanks() {
        Button b = new Button("Print ranks");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Popup p = new Popup();
//
//
//
//                p.launch();
                ranks();
            }
        });
        return b;
    }

    private void ranks() {
        Map<EmployeeLadder, ArrayList<KPI>> ladder = getBusiness().getLadderKpis();
        Business b = Settings.getBusiness();
//        String rank = "";
        for (String ranks : Settings.availableRanks) {
            EmployeeLadder el = Settings.getType(ranks);
            if(el != null) {
                System.out.println(ranks);
                for (Employee e : b.getEmployees()) {
                    if(e.getLadder().compareTo(el)){
                        System.out.println("--->" + e.getTitle());
                    }
                }
                System.out.println("---------------------");
            }
        }

    }
}
