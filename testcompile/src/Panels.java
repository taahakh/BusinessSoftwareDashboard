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

//class KPIFrame extends Frame {
//
//    private Panel buttons;
//
//    public KPIFrame() {
//        super();
//        assign();
//    }
//
//    public KPIFrame(String keyMetric, TextArea visual) {
//        super();
//        assign();
//        visual.setEditable(false);
//        visual.setText(keyMetric);
//
//        this.setLayout(new GridLayout(0,2));
//        this.addWindowListener(new WindowCloser());
//        this.setSize(700,700);
//        this.setLocationRelativeTo(null);
//
//        buttons = Panels.basicPanel();
//
//        this.add(buttons);
//
//        this.add(visual);
//    }
//
//    protected void assign() {
//        this.addWindowListener(new WindowCloser());
//        this.setPreferredSize(new Dimension(400, 600));
//    }
//
//    public void addButton(Button b){
//        buttons.add(b);
//    }
//}

//class MangementFrame extends KPIFrame {
//    public MangementFrame() {
//        super();
//        this.setLayout(new GridLayout(0,1));
//        this.setSize(400, 700);
//    }
//}
