import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public abstract class KPI implements Serializable {

    private final TextField VISUAL = new TextField();
    private final String CLASSNAME;

    private String indicator;
    private String description;

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

    public String description() {
        return description;
    }

    public TextField getVisual() {
        return VISUAL;
    }

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

    abstract String provideKeyMetric();
    abstract Frame showKpi(boolean editable);

}
