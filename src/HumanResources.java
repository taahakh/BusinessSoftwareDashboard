//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.Serializable;
//import java.util.HashMap;
//
//public class HumanResources extends KPI implements Serializable {
//
//    private final HashMap<String, Integer> VALUES = new HashMap<>();
//    private String type;
//
//    public HumanResources(String indicator) {
//        super(indicator, "HumanResources",  "Track Human resources");
//        type = "";
//    }
//
//    public Button create(Method method, String name, String description){
//        Button b = new Button(name);
//
//        b.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Popup p  = new Popup();
//                Label desc, success;
//                TextField item, val;
//                Button submit = new Button(Conts.SUBMIT);
//
//                desc = new Label(description);
//                success = new Label("");
//
//                item = new TextField();
//                val = new TextField();
//
//                submit.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        switch (method) {
//                            case UPDATE:
//                                update(item.getText(), val.getText(), success, p);
//                                break;
//                            case REMOVE:
//                                remove(item.getText(), p);
//                                break;
//                            case ADD:
//                                add(item.getText(), val.getText(), success, p);
//                                break;
//                            case TYPE:
//                                type(item.getText(), p);
//                                break;
//                        }
//                    }
//                });
//
//
//                p.add(desc);
//                p.add(item);
//                if(method == Method.TYPE) {
//                    p.add(submit);
//                    p.add(success);
//                    p.launch();
//                    return;
//                }
//                if (method != Method.REMOVE){
//                    p.add(val);
//                }
//                p.add(submit);
//                p.add(success);
//                p.launch();
//            }
//        });
//
//
//        return b;
//    }
//
//    private void add(String name, String val, Label success, Popup p) {
//        if(!VALUES.containsKey(name)){
//            if(handleNumberException(val, success)){
//                VALUES.put(name, Integer.parseInt(val));
//                p.close();
//            }
//        } else {
//            success.setText("Cannot add. Check again please");
//        }
//    }
//
//    private void update(String name, String val, Label success, Popup p) {
//        if (VALUES.containsKey(name)){
//            if(handleNumberException(val, success)) {
//                int i = Integer.parseInt(val);
//                VALUES.put(name, VALUES.get(name)+i);
//                p.close();
//            }
//        } else {
//            success.setText("Cannot update");
//        }
//    }
//
//    private void remove(String name, Popup p){
//        VALUES.remove(name);
//        p.dispose();
//        Settings.save();
//    }
//
//    private void type(String name, Popup p){
//        this.type = name;
//        p.dispose();
//        Settings.save();
//    }
//
//    @Override
//    String provideKeyMetric() {
//        return "Type: " + type + "\n" + generatePKM(VALUES);
//    }
//
//
//    @Override
//    Frame showKpi(boolean editable) {
//        KPIFrame f = new KPIFrame(provideKeyMetric(), getVisual());
//        f.addButton(viewPKM("view key metrics"));
//        f.setTitle(Conts.HUMAN_RESOURCES);
//        if(editable) {
//            f.addButton(create(Method.ADD, "add production item","Add item and value"));
//            f.addButton(create(Method.UPDATE, "update production value","Update item value"));
//            f.addButton(create(Method.REMOVE, "remove production item","Remove item"));
//            f.addButton(create(Method.TYPE, "change production type","Change type"));
//        }
//        return f;
//    }
//
//    @Override
//    public boolean compare(Object obj) {
//        if(obj instanceof String){
//            return obj.equals(Conts.HUMAN_RESOURCES);
//        }
//        return false;
//    }
//}

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.HashMap;

public class HumanResources extends Inventory implements Serializable {

//    private final HashMap<String, Integer> VALUES = new HashMap<>();
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
    String provideKeyMetric() {
        return "Type: " + type +" \n" + super.provideKeyMetric();
    }

    @Override
    Frame showKpi(boolean editable) {
        KPIFrame f = (KPIFrame) super.showKpi(editable);
        f.setTitle(Conts.HUMAN_RESOURCES);
        if(editable) {
            f.addButton(addTypeButton());
        }
        return f;
    }

//    @Override
//    public boolean compare(Object obj) {
//        if(obj instanceof String){
//            return obj.equals(Conts.HUMAN_RESOURCES);
//        }
//        return false;
//    }
}
