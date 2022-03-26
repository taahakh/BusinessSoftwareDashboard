public abstract class KPI extends KPIStruct {

    public KPI(String indicator, String className) {
        super(indicator, className);
    }

    abstract String provideKeyMetric();
    abstract KPI returnSameClass();

}
