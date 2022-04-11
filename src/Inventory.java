import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.HashMap;

public class Inventory extends KPI implements Serializable {

    private HashMap<String, Integer> items;
    private final TextField visual = new TextField();


    public Inventory(String indicator){
        super(indicator, "Inventory");
        items = new HashMap<>();
    }

    public Button updateItem() {
        Button p = new Button("Update item");
        p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                Button submit = new Button("Submit");
                TextField item, value;
                Label label, success;

                label = new Label("Enter the item and the corresponding value that you want to update");
                success = new Label("");

                item = new TextField("Item");
                value = new TextField("Number");

                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) throws NumberFormatException {
                        String str = item.getText();
                        int val = 0;
                        try {
                             val = Integer.parseInt(value.getText());
                        } catch (NumberFormatException ignored){
//                            p.dispose();
                            return;
                        }

                        if(str.equals("") || val == 0) {
//                            p.dispose();
                            success.setText("We couldn't update it");
                            return;
                        }

                        for(String i: items.keySet()){
                            if(i.equals(str)){
                                int temp = items.get(i);
                                temp += val;
                                p.dispose();
                                return;
                            }
                        }

                        success.setText("We couldn't update it");
                    }
                });

                p.add(label);
                p.add(item);
                p.add(value);
                p.add(submit);
                p.add(success);
                p.launch();
            }
        });
        return p;
    }

    @Override
    String description() {
        return "tracks inventory";
    }

    @Override
    String provideKeyMetric() {
        return "null";
    }

    @Override
    KPI returnSameClass() {
        return new Inventory("");
    }

    @Override
    Frame showKpi(boolean editable) {
        Frame f = Panels.basicWindow();
        Panel buttons = Panels.basicPanel();

        f.setLayout(new GridLayout(0,2));


        visual.setEditable(false);
        visual.setText(provideKeyMetric());

        buttons.add(updateItem());

        f.add(visual);
        f.add(buttons);

        return f;
    }



}
