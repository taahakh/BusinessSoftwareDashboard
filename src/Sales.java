import java.awt.*;
import java.io.Serializable;

public class Sales extends KPI implements Serializable {

    private int sales;
    private int unitsRemaining;

    public Sales(String identifier) {
        super(identifier, "Sales");
    }

    public int getSales() {
        return sales;
    }

    public int getUnitsRemaining() {
        return unitsRemaining;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public void setUnitsRemaining(int unitsRemaining) {
        this.unitsRemaining = unitsRemaining;
    }

    public void updateSales(int update){
        this.sales += update;
    }

    public void updateUnitsRemaining(int update){
        this.sales = update;
    }

    @Override
    String provideKeyMetric() {
        return null;
    }

    @Override
    KPI returnSameClass() {
        return new Sales("");
    }

    @Override
    Frame showKpi(boolean editable) {
        Frame f = Panels.basicWindow();
        return f;
    }

}
