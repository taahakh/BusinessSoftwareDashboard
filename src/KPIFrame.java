import java.awt.*;

public class KPIFrame extends Frame {

    private Panel buttons;

    public KPIFrame() {
        super();
        assign();
    }

    public KPIFrame(String keyMetric, TextArea visual) {
        super();
        assign();
        visual.setEditable(false);
        visual.setText(keyMetric);

        this.setLayout(new GridLayout(0,2));
        this.addWindowListener(new WindowCloser());
        this.setSize(700,700);
        this.setLocationRelativeTo(null);

        buttons = Panels.basicPanel();

        this.add(buttons);

        this.add(visual);
    }

    protected void assign() {
        this.addWindowListener(new WindowCloser());
        this.setPreferredSize(new Dimension(400, 600));
    }

    public void addButton(Button b){
        buttons.add(b);
    }
}