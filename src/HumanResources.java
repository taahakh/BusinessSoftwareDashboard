import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class HumanResources extends Inventory implements Serializable {

    private String type;

    public HumanResources(String indicator) {
        super(indicator, "HumanResources",  "Track Human resources");
        type = "";
    }

    private Button addTypeButton() {
        Button b = new Button("Update type");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                Label label = new Label("update type");
                TextField tf = new TextField();
                Button submit = new Button(Conts.SUBMIT);
                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        type(tf.getText(), p);
                    }
                });

                p.add(label);
                p.add(tf);
                p.add(submit);
                p.launch();
            }
        });
        return b;
    }

    private void type(String name, Popup p){
        this.type = name;
        p.close();
    }

    @Override
    public String provideKeyMetric() {
        return "Type: " + type +" \n" + super.provideKeyMetric();
    }

    @Override
    public Frame showKpi(boolean editable) {
        KPIFrame f = (KPIFrame) super.showKpi(editable);
        f.setTitle(Conts.HUMAN_RESOURCES);
        if(editable) {
            f.addButton(addTypeButton());
        }
        return f;
    }

}
