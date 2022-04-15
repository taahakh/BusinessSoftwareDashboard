import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardFrame extends Frame {

    public DashboardFrame() {
        Employee em = Settings.getEmployee();

        Panel layout;
        Button logout, refresh;
        Label area;

        logout = new Button("Logout");
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.logout();
                dispose();
                new LoginFrame().setVisible(true);
            }
        });

        refresh = new Button("refresh");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DashboardFrame().setVisible(true);
            }
        });

        this.setLayout(new FlowLayout());
        layout = new Panel();
        layout.setLayout(new GridLayout(0,1));
        layout.setVisible(true);

        area = new Label(em.getLadder().showInfoMetric());

        System.out.println("Business list: " + Settings.getBusiness().getTotalKpis());
        System.out.println("Business name: "+ Settings.getBusiness().getName());
        Settings.getBusiness().printLinks();

        System.out.println("-------------------");
        System.out.println(Settings.getBusiness().getEmployees().toString());
        System.out.println("-------------------");

        layout.add(area);

        this.add(layout);
        // Settings
        this.add(em.showSettingsButton());
        // Key performance indicators
        for(Button x: em.showKPIButtons()) {
            this.add(x);
        }

        em.formLayout(layout);

        this.add(logout);
        this.add(refresh);

        this.addWindowListener(new WindowCloser());
        this.setSize(700,700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
