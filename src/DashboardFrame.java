import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* Can only work if the user is logged in and the employee has been set
* Loads KPI's and Settings
* */

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
        layout.setVisible(true);

        this.setTitle(em.getUsername() + ": " + em.getTitle());


        this.add(layout);
        // Settings
        this.add(em.showSettingsButton());
        // Key performance indicators
        for(Button x: em.showKPIButtons()) {
            this.add(x);
        }
        
        this.add(Operations.printDetails());

        em.formLayout(layout);
        this.add(logout);
        this.add(refresh);

        this.addWindowListener(new CompleteClose());
        this.setSize(700,700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
