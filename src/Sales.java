import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class Sales extends KPI implements Serializable {

    private int sales;
    private int unitsRemaining;

    public Sales(String identifier) {
        super(identifier, "Sales", " Track sales");
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
                                    if (either){
                                        sales += x;
                                    } else {
                                        unitsRemaining += x;
                                    }
                                    p.close();
                                    break;
                                case ADD:
                                    if (either){
                                        sales = x;
                                    } else {
                                        unitsRemaining = x;
                                    }
                                    p.close();
                                    break;
                            }
                    }
                });

                p.add(tf);
                p.add(b);
                p.launch();
            }
        });
        return b;
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
