import java.awt.*;
import java.io.Serializable;

public abstract class KPI extends KPIStruct implements Serializable {

//    private final TextField visual = new TextField();

    public KPI(String indicator, String className) {
        super(indicator, className);
    }

    abstract String description();
    abstract String provideKeyMetric();
    abstract KPI returnSameClass();
    abstract Frame showKpi(boolean editable);

}
