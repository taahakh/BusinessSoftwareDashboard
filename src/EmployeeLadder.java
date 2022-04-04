import java.io.Serializable;
import java.util.ArrayList;
import java.awt.*;

public abstract class EmployeeLadder implements Ladder, Serializable {
    private Identifier[] access;
//    private ArrayList<KPI> levelList;

    public EmployeeLadder(Identifier[] access){
        this.access = access;
//        this.levelList = new ArrayList<KPI>();
    }

    public Identifier[] getAccess() {
        return access;
    }

//    public void add(KPI indicator) {
//        this.levelList.add(indicator);
//    }

    public void setAccess(Identifier[] access) {
        this.access = access;
    }

//    public ArrayList<KPI> getLevelList() {
//        return levelList;
//    }

//    public void setLevelList(ArrayList<KPI> levelList) {
//        this.levelList = levelList;
//    }

    public boolean has(Identifier iden) {
        for(Identifier x : access) {
            if(x.equals(iden)){
                return true;
            }
        }
        return false;
    }

//    public boolean hasKPI(String type, String name) {
//        for(KPI x: levelList) {
//            if(x.getClassName().equals(type) && x.getIndicatorName().equals(name)){
//                return true;
//            }
//        }
//        return false;
//    }

    abstract boolean compareTo(Object obj);

    abstract Panel showRights();

    abstract Panel showKpis();
}
