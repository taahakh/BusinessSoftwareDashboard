import java.io.Serializable;
import java.util.ArrayList;

/*
* Stores KPI's as well as access rights.
* Access rights (Identifier) determines what the employee can see in the Settings frame
* kpis arraylist store whatever a user has given it which must follow the check rule when appending
*/
public abstract class KPIGroup extends Rules implements Serializable, KPIRules {
    private final Identifier[] access;
    private ArrayList<KPI> kpis = new ArrayList<>();

    private final String DESCRIPTION;
    private final String METRIC;

    public KPIGroup(Identifier[] access, String description, String metric){
        this.access = access;
        this.DESCRIPTION = description;
        this.METRIC = metric;
    }

    public Identifier[] getAccess() {
        return access;
    }

    public boolean hasIdentifier(Identifier iden) {
        for(Identifier x : access) {
            if(x.equals(iden)){
                return true;
            }
        }
        return false;
    }

    public String description() {
        return DESCRIPTION;
    }

//    public String showInfoMetric(){
//        return METRIC;
//    }

    public String provideKeyMetric(){
        return METRIC;
    }

    public ArrayList<KPI> getKpis() {
        return kpis;
    }

    public void setKpis(ArrayList<KPI> kpis) {
        this.kpis = kpis;
    }

    abstract boolean check(String kpi);

}
