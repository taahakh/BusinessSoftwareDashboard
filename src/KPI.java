import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public abstract class KPI implements Serializable {

    private final TextField visual = new TextField();

    private final String indicator;
    private final String className;
    private final String description;

    public KPI(String indicator, String className, String description) {
        this.indicator = indicator;
        this.className = className;
        this.description = description;
    }

    public String getIndicatorName() {
        return this.indicator;
    }

    public String getClassName(){
        return this.className;
    }

    public String description() {
        return description;
    }

    public TextField getVisual() {
        return visual;
    }

    public Button viewPKM(String name) {
        Button p = new Button(name);
        p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visual.setText(provideKeyMetric());
            }
        });
        return p;
    }

    abstract String provideKeyMetric();
    abstract Frame showKpi(boolean editable);

}
