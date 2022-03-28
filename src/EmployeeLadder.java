import java.io.Serializable;
import java.util.ArrayList;
import java.awt.*;

public abstract class EmployeeLadder implements Ladder, Serializable {
    private Identifier[] access;
    private ArrayList<KPI> levelList = new ArrayList<KPI>();

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

    public Panel basicPanel() {
        Panel layout = new Panel();
        layout = new Panel();
        layout.setLayout(new GridLayout(0,1));
        layout.setVisible(true);
        return layout;
    }

    public ArrayList<KPI> getLevelList() {
        return levelList;
    }

    abstract Panel showRights();

    abstract Panel showKpis();
}
