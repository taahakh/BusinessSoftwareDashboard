import java.io.Serializable;

public class KPIStruct implements Serializable {
    private final String indicator;
    private final String className;

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
