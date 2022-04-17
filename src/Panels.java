import java.awt.*;
import java.lang.management.ManagementFactory;

public class Panels {
    public static Panel basicPanel() {
        Panel layout = new Panel();
//        layout.setLayout(new GridLayout(0,1));
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

class KPIFrame extends Frame {

    private Panel buttons;

    public KPIFrame() {
        super();
        assign();
    }

    public KPIFrame(String keyMetric, TextField visual) {
        super();
        assign();
        visual.setEditable(false);
        visual.setText(keyMetric);
        this.add(visual);
    }

    private void assign() {
        this.setLayout(new GridLayout(0,2));
        this.addWindowListener(new WindowCloser());
        this.setSize(700,700);
        this.setLocationRelativeTo(null);

        buttons = Panels.basicPanel();

        this.add(buttons);
    }

    public void addButton(Button b){
        buttons.add(b);
    }
}

class MangementFrame extends KPIFrame {
    public MangementFrame() {
        super();

        this.setLayout(new GridLayout(2,1));
        this.setSize(400, 700);
    }
}
