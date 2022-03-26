public class Inventory extends KPI{

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
}
