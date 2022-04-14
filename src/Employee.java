import java.awt.*;
import java.io.Serializable;

public abstract class Employee implements EmployeeRules, Serializable {

    private Business business; // Which business they belong to. Each employee is tied to one business
    private String title; // Job title
    // Business position. Classes inherit EmployeeLadder and can access certain features regarding access to KPI's, creating/deleting users etc. At each rank, certain kpi's can be viewed and certain access rights can be shown
    private EmployeeLadder rank;

    public Employee() {}

    public Employee(EmployeeLadder type){
        this.rank = type;
    }

    public Employee(String title, EmployeeLadder type){
        this.title = title;
        this.rank = type;
    }

    public String getTitle(){
        return this.title;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Business getBusiness() {
        return business;
    }

    public EmployeeLadder getLadder(){
        return rank;
    }

    public Button[] showKPIButtons() {
        if(rank.has(Identifier.VIEWER)){
            System.out.println("view");
            if(rank.has(Identifier.EDITOR)){
                System.out.println("view, edit");
                return Operations.generateKPIButtons(business.getKPILadderList(rank), true);
            }
            return Operations.generateKPIButtons(business.getKPILadderList(rank), false);
        }

        throw new RuntimeException();
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

    abstract Button load();
}
