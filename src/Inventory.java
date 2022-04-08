import java.awt.*;
import java.io.Serializable;

public class Inventory extends KPI implements Serializable {

    public Inventory(String indicator){
        super(indicator, "Inventory");
    }

    @Override
    String provideKeyMetric() {
        return null;
    }

    @Override
    KPI returnSameClass() {
        return new Inventory("");
    }

    @Override
    Frame showKpi(boolean editable) {
        return Panels.basicWindow();
    }
}
