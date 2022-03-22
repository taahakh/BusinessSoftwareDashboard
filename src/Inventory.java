public class Inventory extends KPI{

    public Inventory(String indicator){
        super(indicator, "Inventory");
    }

    @Override
    String provideKeyMetric() {
        return null;
    }
}
