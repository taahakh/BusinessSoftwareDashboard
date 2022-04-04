import java.awt.*;
import java.util.Set;

public class DashboardFrame extends Frame {

    public void closeFrame(){
        this.dispose();
    }

    public DashboardFrame() {
        User user = Login.getLoggedIn();
        Business b = Settings.getBusiness();
        Employee em = Settings.getEmployee();

        Panel layout;
        Button[] buttons;
        Label area;

        this.setLayout(new FlowLayout());
        layout = new Panel();
        layout.setLayout(new GridLayout(0,1));
        layout.setVisible(true);

        area = new Label(em.getLadder().showInfoMetric());

//        System.out.println("Business sattus: " + Settings.getBusiness().getKPIList());
//        System.out.println("Business type: " + Settings.getEmployee().whatType());
//        System.out.println("Business type: " + Settings.getEmployee().getBusiness().getKPIList());
//        System.out.println(Settings.getEmployee().getLadder().getLevelList());
        System.out.println("Business list: " + Settings.getBusiness().getTotalKpis());
        System.out.println("Business name: "+ Settings.getBusiness().getName());
        Settings.save();

        System.out.println("-------------------");

        for (Employee e: Settings.getBusiness().getEmployees()){
//            System.out.println(e.getTitle()+ " " + e.getLadder().getLevelList());
        }

        System.out.println("-------------------");

        layout.add(area);

        this.add(layout);
        // Settings
        this.add(em.showSettingsButton());
        // Key performance indicators
//        for(Button x: em.showKPIButtons()) {
//            this.add(x);
//        }

        this.addWindowListener(new WindowCloser());
        this.setSize(700,700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
