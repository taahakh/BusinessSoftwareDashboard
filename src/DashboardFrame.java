import java.awt.*;

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

        System.out.println("Business sattus: " + Settings.getBusiness().getName());

        layout.add(area);

        this.add(layout);
//        this.add(em.getLadder().showRights());
//        this.add(em.getLadder().showKpis());

        this.addWindowListener(new WindowCloser());
        this.setSize(700,700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
