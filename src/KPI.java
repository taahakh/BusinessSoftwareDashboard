import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.HashMap;

/*
* Basic KPI template for sales, inventory etc..
*/

public abstract class KPI extends CompareRules implements Serializable, KPIRules {

    private final TextArea VISUAL = new TextArea();
    private final String CLASSNAME;

    private final String indicator;
    private final String description;

    public KPI(String indicator, String className, String description) {
        this.indicator = indicator;
        this.CLASSNAME = className;
        this.description = description;
    }

    public String getIndicatorName() {
        return this.indicator;
    }

    public String getClassName(){
        return this.CLASSNAME;
    }

    public TextArea getVisual() {
        return VISUAL;
    }

    public String description() {
        return description;
    }

    // ---------------------------------------------

    public Button viewPKM(String name) {
        Button p = new Button(name);
        p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VISUAL.setText(provideKeyMetric());
            }
        });
        return p;
    }

    // Generates and formats data from HashMap
    public <E, T> StringBuilder generatePKM (HashMap<E, T> set) {
        StringBuilder text = new StringBuilder();
        for (E item : set.keySet()){
            text.append(item.toString()).append(": ").append(set.get(item)).append("\n");
        }
        return text;
    }

    // Multiple subclasses deal with integers so this method handles any exceptions
    // it works by returning if there was an exception or not
    public boolean handleNumberException(String val, Label success) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException e){
            success.setText("Number format incorrect");
            return false;
        }
    }

//    abstract String provideKeyMetric();

    // ---------------------------------------------

    abstract Frame showKpi(boolean editable);


}
