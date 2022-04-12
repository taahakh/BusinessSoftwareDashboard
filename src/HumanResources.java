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

    public Button create(Method method, String name, String desc){
        Button b = new Button();

        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (method) {
                    case UPDATE:
                        update();
                        break;
                    case REMOVE:
                        remove();
                        break;
                    case ADD:
                        add();
                        break;
                    case TYPE:
                        type();
                        break;
                }
            }
        };


        return b;
    }

    public void add(){

    }

    public void update(){

    }

    public void remove(){

    }

    public void type(){

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
        f.addButton(create(Method.ADD, "add production item",""));
        f.addButton(create(Method.UPDATE, "remove production value",""));
        f.addButton(create(Method.REMOVE, "remove production item",""));
        f.addButton(create(Method.TYPE, "change production type",""));
        return f;
    }
}
