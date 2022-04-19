import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.HashMap;

public class Inventory extends KPI implements Serializable {

    private final HashMap<String, Integer> ITEMS = new HashMap<>();

    public Inventory(String indicator){
        super(indicator, "Inventory", "tracks inventory");
    }

    public Inventory(String indicator, String className, String description){
        super(indicator, className, description);
    }

    public Button create(Method method, String name, String description){
        Button b = new Button(name);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                Label desc, success;
                TextField name, val;
                Button submit;

                desc = new Label(description);
                success = new Label("");

                name = new TextField();
                val = new TextField();

                submit = new Button(Conts.SUBMIT);
                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (method){
                            case ADD:
                                add(name.getText(), val.getText(), success);
                                break;
                            case REMOVE:
                                remove(name.getText(), p);
                                break;
                            case UPDATE:
                                update(name.getText(), val.getText(), success, p);
                                break;
                        }
                    }
                });

                p.add(desc);
                p.add(name);
                if(method != Method.REMOVE){
                    p.add(val);
                }
                p.add(submit);
                p.add(success);
                p.launch();
            }
        });
        return b;
    }

    private void add(String name, String val, Label success) {
        if(ITEMS.get(name) == null){
            if(handleNumberException(val, success)){
                ITEMS.put(name, Integer.parseInt(val));
                Settings.save();
            }
        } else {
            success.setText("Item already exists");
        }
    }

    private void update(String item, String value, Label success, Popup p) {
        int val;
        if(handleNumberException(value, success)){
            val = Integer.parseInt(value);
        } else {
            return;
        }

        if(item.equals("") || val == 0) {
            success.setText("We couldn't update it");
            return;
        }

        for(String i: ITEMS.keySet()){
            if(i.equals(item)){
                int temp = ITEMS.get(i);
                temp += val;
                ITEMS.put(i, temp);
                p.close();
                return;
            }
        }

        success.setText("We couldn't update it");
    }

    private void remove(String name, Popup p) {
        ITEMS.remove(name);
        p.close();
    }

    @Override
    public String provideKeyMetric() {
        return "Items -> \n" + generatePKM(ITEMS).toString();
    }

    @Override
    Frame showKpi(boolean editable) {

        KPIFrame f = new KPIFrame(provideKeyMetric(), getVisual());
        f.setTitle(Conts.INVENTORY);
        f.addButton(viewPKM());
        if(editable) {
            f.addButton(create(Method.ADD, "Add item", "Enter the item you want to enter and number of item"));
            f.addButton(create(Method.UPDATE, "Update item",  "Enter the item and the corresponding value that you want to update"));
            f.addButton(create(Method.REMOVE, "Remove item", "Enter the item you want to delete"));
        }
        return f;
    }



}
