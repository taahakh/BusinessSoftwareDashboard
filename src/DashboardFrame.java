import java.awt.*;

public class DashboardFrame extends Frame {

    public void closeFrame(){
        this.dispose();
    }

    public DashboardFrame() {
        Panel layout;

        this.setLayout(new FlowLayout());
        layout = new Panel();
        layout.setLayout(new GridLayout(0,1));
        layout.setVisible(true);

        this.add(layout);

        System.out.println(Login.getLoggedIn().getEmployee().get(0).getTitle());

        this.addWindowListener(new WindowCloser());
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
