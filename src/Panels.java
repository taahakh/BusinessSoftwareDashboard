import java.awt.*;

public class Panels {
    public static Panel basicPanel() {
        Panel layout = new Panel();
        layout = new Panel();
        layout.setLayout(new GridLayout(0,1));
        layout.setVisible(true);
        return layout;
    }
}
