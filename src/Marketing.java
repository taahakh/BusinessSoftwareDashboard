import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Marketing extends KPI implements Serializable {

    private final HashMap<Date, String> IMPRESSIONS = new HashMap<>();
    private final HashMap<Date, String> REACH = new HashMap<>();

    public Marketing(String indicator) {
        super(indicator, "Marketing", "");
    }

    public Date parseDate(String date, Label success){
        try{
            return new SimpleDateFormat("dd-MM-yyyy").parse(date);
        }catch (ParseException ignored){
            success.setText("Couldn't parse date");
            return null;
        }
    }

    public Button create(Method method, String name, String description, HashMap<Date, String> set){
        Button b = new Button(name);
        b.addActionListener(new ActionListener() {
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
        });

        return b;
    }

    public void add(String date, String val, Label success, Popup p, HashMap<Date, String> set) {

        Date d = parseDate(date, success);
        if(!(set.containsKey(d))){
            set.put(d, val);
            p.close();
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
            p.close();
        }
    }

    public void remove(String date, Label success, Popup p, HashMap<Date, String> set) {
        Date d = parseDate(date, success);

        set.remove(d);
        p.close();
    }

    @Override
    String provideKeyMetric() {
        return "im: " + IMPRESSIONS + "" +
                "re: " + REACH;
    }

    @Override
    Frame showKpi(boolean editable) {

        final String ADD = "Enter date (format: dd-MM-yyyy) and value";
        final String REMOVE = "Enter date to remove";
        final String UPDATE = "Enter date and value to update";
        KPIFrame f = new KPIFrame(provideKeyMetric(), getVisual());

        f.addButton(viewPKM("View key metrics"));
        if(editable) {
            f.addButton(create(Method.ADD, "add Impressions", ADD, IMPRESSIONS));
            f.addButton(create(Method.ADD,"add reach", ADD, REACH));
            f.addButton(create(Method.REMOVE,"remove reach", REMOVE, REACH));
            f.addButton(create(Method.REMOVE,"remove impressions", REMOVE, IMPRESSIONS));
            f.addButton(create(Method.UPDATE,"update reach", UPDATE, REACH));
            f.addButton(create(Method.UPDATE,"update impressions", UPDATE, IMPRESSIONS));

        }
        return f;
    }
}
