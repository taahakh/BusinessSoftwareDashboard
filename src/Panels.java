import java.awt.*;

public class Panels {
    public static Panel basicPanel() {
        Panel layout = new Panel();
        layout.setLayout(new GridLayout(0,1));
        layout.setVisible(true);
        return layout;
    }

    public static Frame basicWindow() {
        Frame frame = new Frame();
        frame.setLayout(new FlowLayout());

        frame.addWindowListener(new WindowCloser());
        frame.setSize(700,700);
        frame.setLocationRelativeTo(null);

        return frame;
    }
}
