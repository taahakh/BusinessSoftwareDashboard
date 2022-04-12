import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

enum Method {
    ADD, REMOVE, UPDATE, TYPE

}

public class Marketing extends KPI implements Serializable {

    private final HashMap<Date, String> impressions = new HashMap<>();
    private final HashMap<Date, String> reach = new HashMap<>();
    private final TextField visual = new TextField();


    public Marketing(String indicator) {
        super(indicator, "Marketing");
    }

    public Date parseDate(String date, Label success){
        try{
            return new SimpleDateFormat("dd-MM-yyyy").parse(date);
        }catch (ParseException ignored){
            success.setText("Couldn't parse date");
            return null;
        }
    }

    public ActionListener template(Method method, String description, HashMap<Date, String> set){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                Label desc, success;
                TextField date, val;
                Button submit = new Button("Submit");

                desc = new Label(description);
                success = new Label("");

                date = new TextField();
                val = new TextField();
                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (method){
                            case ADD:
                                add(date.getText(), val.getText(), success, p, set);
                                break;
                            case REMOVE:
                                remove(date.getText(), success, p, set);
                                break;
                            case UPDATE:
                                update(date.getText(), val.getText(), success, p, set);
                                break;
                        }
                    }
                });


                p.add(desc);
                p.add(date);
                if(method != Method.REMOVE){
                    p.add(val);
                }
                p.add(submit);
                p.add(success);
                p.launch();
            }
        };
    }

    public void add(String date, String val, Label success, Popup p, HashMap<Date, String> set) {

        Date d = parseDate(date, success);
        if(!(set.containsKey(d))){
            set.put(d, val);
            p.dispose();
            Settings.save();
        } else {
            success.setText("Cannot add. Check again please");
        }

    }

    public void update(String date, String val, Label success, Popup p, HashMap<Date, String> set) {
        Date d = parseDate(date, success);

        if(set.get(d) != null){
            int x, y;
            try{
                x = Integer.parseInt(val);
                y = Integer.parseInt(set.get(d));
            } catch (NumberFormatException e){
                success.setText("There has been a conversion error");
                return;
            }

            set.put(d, String.valueOf(x+y));
            Settings.save();
            p.dispose();
        }
    }

    public void remove(String date, Label success, Popup p, HashMap<Date, String> set) {
        Date d = parseDate(date, success);

        set.remove(d);
        Settings.save();
        p.dispose();
    }



    public Button create(Method method, String name, HashMap<Date, String> set) {
        Button b = new Button(name);
        switch (method){
            case ADD:
                b.addActionListener(template(method, "Enter date (format: dd-MM-yyyy) and value", set));
                break;
            case REMOVE:
                b.addActionListener(template(method, "Enter date to remove", set));
                break;
            case UPDATE:
                b.addActionListener(template(method, "Enter date and value to update", set));
                break;
        }


        return b;
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
        return "null";
    }

    @Override
    String provideKeyMetric() {
        return "im: " + impressions + "" +
                "re: " + reach;
    }

    @Override
    KPI returnSameClass() {
        return null;
    }

    @Override
    Frame showKpi(boolean editable) {
        KPIFrame f = new KPIFrame(provideKeyMetric(), visual);
        f.addButton(viewPKM());
        f.addButton(create(Method.ADD, "add Impressions", impressions));
        f.addButton(create(Method.ADD,"add reach", reach));
        f.addButton(create(Method.REMOVE,"remove reach", reach));
        f.addButton(create(Method.REMOVE,"remove impressions", impressions));
        f.addButton(create(Method.UPDATE,"update reach", reach));
        f.addButton(create(Method.UPDATE,"update impressions", impressions));

        return f;
    }
}
