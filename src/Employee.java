import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Employee extends CompareRules implements Serializable {

    private Business business; // Which business they belong to. Each employee is tied to one business
    private String title; // Job title
    // Business position. Classes inherit KPIGroup and can access certain features regarding access to KPI's, creating/deleting users etc. At each rank, certain kpi's can be viewed and certain access rights can be shown
    private KPIGroup rank;
    private String description;
    private String username;

    public Employee() {}

    public Employee(KPIGroup type){
        this.rank = type;
    }

    public Employee(String title, KPIGroup type, String description){
        assign(title, type, description);
    }

    public Employee(String title, String type, String description){
        if(Settings.getBusiness() != null) {
            this.business = Settings.getBusiness();
            assign(title, business.assignType(Settings.getType(type)), description);
        }
    }

    private void assign(String title, KPIGroup type, String description) {
        this.title = title;
        this.rank = type;
        this.description = description;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBusiness(Business business) {
        this.business = business;
        rank = business.assignType(rank);
    }

    public Business getBusiness() {
        return business;
    }

    public KPIGroup getLadder(){
        return rank;
    }

    public ArrayList<Button> showKPIButtons() {
        if(rank.has(Identifier.VIEWER)){
            if(rank.has(Identifier.EDITOR)){
                return generateKPIButtons(true);
            }
            return generateKPIButtons(false);
        }

        return new ArrayList<Button>();
    }

    public Button showSettingsButton(){
        return Operations.generateSettingsButton(rank.getAccess());
    }

    public boolean hasIdentifier(Identifier iden) {
        for (Identifier i : getIdentifiers()) {
            if(iden == i){
                return true;
            }
        }

        return false;
    }

    private Identifier[] getIdentifiers() {
        return rank.getAccess();
    }

    public String description() {
        return description;
    }

    public ArrayList<Button> generateKPIButtons(boolean editable) {
        return Operations.generateKPIButtons(rank.getKpis(), editable);
    }

    public ArrayList<Button> generateKPIButtons(Employee e, String kpi, boolean editable) {
//        return Operations.generateKPIButtons(business.getKPILadderList(e.getLadder()), kpi, editable);
        return Operations.generateKPIButtons(e.getLadder().getKpis(), kpi, editable);

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public boolean comparing(Object obj) {
        return obj.getClass().equals(getClass());
    }

    abstract void formLayout(Panel panel);

}
