import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.HashMap;

public class Inventory extends KPI implements Serializable {

    private final HashMap<String, Integer> items = new HashMap<>();

    public Inventory(String indicator){
        super(indicator, "Inventory", "tracks inventory");
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

                submit = new Button("Submit");
                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (method){
                            case ADD:
                                break;
                            case REMOVE:
                                break;
                            case UPDATE:
                                break;
                        }
                    }
                });

                p.add(desc);
                p.add(name);
                p.add(val);
                p.add(submit);
                p.add(success);

                p.launch();
            }
        });
        return b;
    }

    public void add() {

    }

    public void update() {

    }

    public void remove() {

    }

    public Button addItem() {
        Button b = new Button("Add item");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                Label desc, success;
                TextField name, val;
                Button submit;

                desc = new Label("Enter the item you want to enter and number of");
                success = new Label("");

                name = new TextField();
                val = new TextField();

                submit = new Button("Submit");
                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(items.get(name.getText()) == null){
                            items.put(name.getText(), Integer.parseInt(val.getText()));
                            Settings.save();
                        }
                    }
                });

                p.add(desc);
                p.add(name);
                p.add(val);
                p.add(submit);
                p.add(success);

                p.launch();
            }
        });
        return b;
    }

    public Button deleteItem() {
        Button b = new Button("Delete item");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                Label desc, success;
                TextField tf = new TextField();
                Button submit = new Button("Submit");

                desc = new Label("Enter the item you want to delete");
                success = new Label("");

                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        items.remove(tf.getText());
                        p.close();
                    }
                });

                p.add(desc);
                p.add(tf);
                p.add(submit);
                p.add(success);

                p.launch();
            }
        });
        return b;
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
                        int val;
                        try {
                             val = Integer.parseInt(value.getText());
                        } catch (NumberFormatException ignored){
                            return;
                        }

                        if(str.equals("") || val == 0) {
                            success.setText("We couldn't update it");
                            return;
                        }

                        for(String i: items.keySet()){
                            if(i.equals(str)){
                                int temp = items.get(i);
                                temp += val;
                                items.put(i, temp);
                                p.dispose();
                                Settings.save();
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
    String provideKeyMetric() {
        System.out.println(items);
        return "items -> " + items;
    }

    @Override
    Frame showKpi(boolean editable) {

        KPIFrame f = new KPIFrame(provideKeyMetric(), getVisual());
        f.addButton(viewPKM("view key metrics"));
        if(editable) {
            f.addButton(updateItem());
            f.addButton(addItem());
            f.addButton(deleteItem());
        }
        return f;
    }



}
