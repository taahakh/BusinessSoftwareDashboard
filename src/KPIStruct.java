import java.util.ArrayList;

public class KPIStruct {
    private final String indicator;
    private final String className;

    public static ArrayList<KPIStruct> totalKPIs = new ArrayList<KPIStruct>();

    public static void appendToList(KPIStruct kpi){
        totalKPIs.add(kpi);
    }

    public static void clearTotalKpis(){
        totalKPIs = null;
    }

    public static void appendArrToList(KPIStruct[] kpi){
        for (int i=0; i<kpi.length; i++){
            totalKPIs.add(kpi[i]);
        }
    }

    public static ArrayList<KPIStruct> getList(){
        return totalKPIs;
    }

    public KPIStruct(String indicator, String className) {
        this.indicator = indicator;
        this.className = className;
    }

    public String getIndicatorName() {
        return this.indicator;
    }

    public String getClassName(){
        return this.className;
    }

    public KPIStruct returnNewKPI(){
        return this;
    }

}
