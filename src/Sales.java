import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/*
* Track sales
* */

public class Sales extends KPI implements Serializable {

    private int sales;
    private int unitsRemaining;

    public Sales(String identifier) {
        super(identifier, Conts.SALES, "Track Sales");
    }

    // true = sales, false = unitsRemaining
    public Button create(Method method, String name, String description, boolean either) {
        Button b = new Button(name);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                TextField tf = new TextField();
                Button b = new Button(Conts.SUBMIT);
                p.setLayout(new GridLayout(0,1));
                p.add(new Label(description));
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(handleNumberException(tf.getText(), new Label())){
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

                    }
                });

                p.add(tf);
                p.add(b);
                p.launch();
            }
        });
        return b;
    }

    private void update(boolean state, int x){
        if (state){
            sales += x;
        } else {
            unitsRemaining += x;
        }
    }

    private void add(boolean state, int x){
        if (state){
            sales = x;
        } else {
            unitsRemaining = x;
        }
    }

    @Override
    public String provideKeyMetric() {
        return "Name: " + getIndicatorName() + "\n" +
                "Sales: " + sales + "\n" +
                "Remaining units: " + unitsRemaining;
    }

    @Override
    Frame showKpi(boolean editable) {

        KPIFrame frame = new KPIFrame(provideKeyMetric(), getVisual());
        frame.setTitle(Conts.SALES);
        frame.addButton(viewPKM());
        if(editable) {
            frame.addButton(create(Method.ADD, "Set sales", "Enter value to set sales", true));
            frame.addButton(create(Method.UPDATE, "update sales", "Enter value to set sales", true));
            frame.addButton(create(Method.ADD, "Set units", "Enter value to set sales", false));
            frame.addButton(create(Method.UPDATE, "update units", "Enter value to set sales", false));

        }
        return frame;
    }


    public int getSales(){
        return sales;
    }

}
