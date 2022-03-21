import java.io.Serializable;
import java.util.ArrayList;

public abstract class EmployeeLadder implements Ladder, Serializable {
    private Identifier[] access;
    protected ArrayList<KPI> levelList = new ArrayList<KPI>();

    public EmployeeLadder(Identifier[] access){
        this.access = access;
    }

    public Identifier[] getAccess() {
        return access;
    }

    public void add(KPI indicator) {
        this.levelList.add(indicator);
    }

    public void setAccess(Identifier[] access) {
        this.access = access;
    }
}
