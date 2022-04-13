import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.HashMap;

public class HumanResources extends KPI implements Serializable {

    private final HashMap<String, Integer> values = new HashMap<>();
    private String type;

    public HumanResources(String indicator) {
        super(indicator, "HumanResources");
    }

    public Button create(Method method, String name, String description){
        Button b = new Button(name);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p  = new Popup();
                Label desc, success;
                TextField item, val;
                Button submit = new Button("Submit");

                desc = new Label(description);
                success = new Label();

                item = new TextField();
                val = new TextField();

                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (method) {
                            case UPDATE:
                                update(item.getText(), val.getText(), success, p);
                                break;
                            case REMOVE:
                                remove(item.getText(), p);
                                break;
                            case ADD:
                                add(item.getText(), val.getText(), success, p);
                                break;
                            case TYPE:
                                type(item.getText(), p);
                                break;
                        }
                    }
                });


                p.add(desc);
                p.add(item);
                if(method == Method.TYPE) {
                    p.add(submit);
                    p.add(success);
                    p.launch();
                    return;
                }
                if (method != Method.REMOVE){
                    p.add(val);
                }
                p.add(submit);
                p.add(success);
                p.launch();
            }
        });


        return b;
    }

    public void add(String name, String val, Label success, Popup p){
        if(!values.containsKey(name)){
            values.put(name, Integer.parseInt(val));
            p.dispose();
            Settings.save();
        } else {
            success.setText("Cannot add. Check again please");
        }
    }

    public void update(String name, String val, Label success, Popup p){
        if (values.containsKey(name)){
            int i = Integer.parseInt(val);
            values.put(name, values.get(name)+i);
            p.dispose();
            Settings.save();
        } else {
            success.setText("Cannot update");
        }
    }

    public void remove(String name, Popup p){
        values.remove(name);
        p.dispose();
        Settings.save();
    }

    public void type(String name, Popup p){
        this.type = name;
        p.dispose();
        Settings.save();
    }

    @Override
    String description() {
        return "null";
    }

    @Override
    String provideKeyMetric() {
        return "null";
    }

    @Override
    KPI returnSameClass() {
        return null;
    }

    @Override
    Frame showKpi(boolean editable) {
        KPIFrame f = new KPIFrame(provideKeyMetric(), new TextField());
        f.addButton(create(Method.ADD, "add production item","Add item and value"));
        f.addButton(create(Method.UPDATE, "update production value","Update item value"));
        f.addButton(create(Method.REMOVE, "remove production item","Remove item"));
        f.addButton(create(Method.TYPE, "change production type","Change type"));
        System.out.println(values);
        return f;
    }
}
