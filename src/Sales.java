import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class Sales extends KPI implements Serializable {

    private int sales;
    private int unitsRemaining;
    private final TextField visual = new TextField();

    public Sales(String identifier) {
        super(identifier, "Sales");
    }

    public Button setSales() {
        Button b = new Button("Set sales");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                TextField tf = new TextField();
                Button b = new Button("Submit");
                p.setLayout(new GridLayout(0,1));
                p.add(new Label("Enter sales amount"));
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sales = Integer.parseInt(tf.getText());
                        p.dispose();
                        Settings.save();
                    }
                });
                p.add(tf);
                p.add(b);
                p.launch();
            }
        });
        return b;
    }

    public Button updateSales(){
        Button p = new Button("Updates sales");
        p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                TextField tf = new TextField();
                Button b = new Button("Submit");
                p.setLayout(new GridLayout(0,1));
                p.add(new Label("Update amount"));
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sales += Integer.parseInt(tf.getText());
                        p.dispose();
                        Settings.save();
                    }
                });
                p.add(tf);
                p.add(b);
                p.launch();
            }
        });
        return p;
    }


    public Button updateUnitsRemaining(){
        Button p = new Button("Update remaining units");
        p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                TextField tf = new TextField();
                Button b = new Button("Submit");
                p.setLayout(new GridLayout(0,1));
                p.add(new Label("Update remaining units"));
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        unitsRemaining += Integer.parseInt(tf.getText());
                        p.dispose();
                        Settings.save();
                    }
                });
                p.add(tf);
                p.add(b);
                p.launch();
            }
        });
        return p;
    }

    public Button viewPKM() {
        Button p = new Button("View Key Metrics");
        p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visual.setText(provideKeyMetric());
            }
        });
        return p;
    }

    @Override
    String description() {
        return "tracks sales";
    }

    @Override
    String provideKeyMetric() {
        return "Name: " + getIndicatorName() + "\n" +
                "Sales: " + sales + "\n" +
                "Remaining units: " + unitsRemaining;
    }

    @Override
    KPI returnSameClass() {
        return new Sales("");
    }

    @Override
    Frame showKpi(boolean editable) {

        KPIFrame frame = new KPIFrame(provideKeyMetric(), visual);
        frame.addButton(viewPKM());
        frame.addButton(setSales());
        frame.addButton(updateSales());
        frame.addButton(updateUnitsRemaining());

        return frame;
    }

}
