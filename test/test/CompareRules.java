import java.awt.*;

public class CompareRules {
    public boolean compare(Object obj) {
        return obj.getClass().equals(getClass());
    };
}
