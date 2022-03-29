import java.awt.*;

public class DefaultKPI extends KPI{

    public DefaultKPI(){
        super("default", "default");
    }

    @Override
    String provideKeyMetric() {
        return null;
    }

    @Override
    KPI returnSameClass() {
        return null;
    }

    @Override
    Panel showKpi(boolean editable) {
        return null;
    }
}
