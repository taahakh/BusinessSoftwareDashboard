import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class Sales extends KPI implements Serializable {

    private static final String SALES = "Sales";
    private static final String TRACK_SALES = "Track Sales";

    private int sales;
    private int unitsRemaining;

    public Sales(String identifier) {
        super(identifier, SALES, TRACK_SALES);
    }

    // true = sales, false = unitsRemaining
    public Button create(Method method, String name, String description, boolean either) {
        Button b = new Button(name);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                TextField tf = new TextField();
                Button b = new Button("Submit");
                p.setLayout(new GridLayout(0,1));
                p.add(new Label(description));
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int x = Integer.parseInt(tf.getText());
                            switch (method) {
                                case UPDATE:
                                    update(either, x);
                                    break;
                                case ADD:
                                    add(either, x);
                                    break;
                            }
                        p.close();
                    }
                });

                p.add(tf);
                p.add(b);
                p.launch();
            }
        });
        return b;
    }

    public void update(boolean state, int x){
        if (state){
            sales += x;
        } else {
            unitsRemaining += x;
        }
    }

    public void add(boolean state, int x){
        if (state){
            sales = x;
        } else {
            unitsRemaining = x;
        }
    }

    @Override
    String provideKeyMetric() {
        return "Name: " + getIndicatorName() + "\n" +
                "Sales: " + sales + "\n" +
                "Remaining units: " + unitsRemaining;
    }

    @Override
    Frame showKpi(boolean editable) {

        KPIFrame frame = new KPIFrame(provideKeyMetric(), getVisual());
        frame.addButton(viewPKM("view KPM"));
        if(editable) {
            frame.addButton(create(Method.ADD, "Set sales", "Enter value to set sales", true));
            frame.addButton(create(Method.UPDATE, "update sales", "Enter value to set sales", true));
            frame.addButton(create(Method.ADD, "Set units", "Enter value to set sales", false));
            frame.addButton(create(Method.UPDATE, "update units", "Enter value to set sales", false));

        }
        return frame;
    }

}
