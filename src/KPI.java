public abstract class KPI extends KPIStruct {

    public KPI(String indicator) {
        super(indicator);
    }

    abstract String provideKeyMetric();


}
