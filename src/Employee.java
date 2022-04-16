import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Employee implements CompareRules, Serializable {

    private Business business; // Which business they belong to. Each employee is tied to one business
    private String title; // Job title
    // Business position. Classes inherit EmployeeLadder and can access certain features regarding access to KPI's, creating/deleting users etc. At each rank, certain kpi's can be viewed and certain access rights can be shown
    private EmployeeLadder rank;
    private String description;

    public Employee() {}

    public Employee(EmployeeLadder type){
        this.rank = type;
    }

    public Employee(String title, EmployeeLadder type, String description){
        assign(title, type, description);
    }

    public Employee(String title, EmployeeLadder type, String description, Business b){
        this.business = b;
        assign(title, b.assignType(type), description);
    }

    private void assign(String title, EmployeeLadder type, String description) {
        this.title = title;
        this.rank = type;
        this.description = description;
    }

    public String getTitle(){
        return this.title;
    }

    public void setBusiness(Business business) {
        this.business = business;
        rank = business.assignType(rank);
    }

    public Business getBusiness() {
        return business;
    }

    public EmployeeLadder getLadder(){
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

    public Identifier[] getIdentifiers() {
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

    abstract void formLayout(Panel panel);

}
