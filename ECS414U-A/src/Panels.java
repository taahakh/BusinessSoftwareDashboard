import java.awt.*;

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

    private final Panel buttons;
//    private final TextField visual;

    public KPIFrame(String keyMetric, TextField visual) {
        super();

//        this.visual = visual;
        this.setLayout(new GridLayout(0,2));
        this.addWindowListener(new WindowCloser());
        this.setSize(700,700);
        this.setLocationRelativeTo(null);

        buttons = Panels.basicPanel();
        visual.setEditable(false);
        visual.setText(keyMetric);

        this.add(visual);
        this.add(buttons);
    }

    public void addButton(Button b){
        buttons.add(b);
    }
}
