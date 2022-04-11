import java.awt.*;
import java.io.Serializable;

enum Identifier {
    // This is not the employee hierarchy exactly.
    // This is about who can access the KPI and users of the business
    // You can customise employee heirarchy but these enums set which user certain rights to parts of the software

    // Users, permissions and KPI
    ADMIN, // delete business, change business name
    USER, // add/remove users : note: you cannot delete admin user if you are not admin nor add
    ROLE, // assign kpis for each role
    // Viewing and Editing KPI
    EDITOR, // can edit and update values
    VIEWER // view essential information
}

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

    public void setTitle(String title) {
        this.title = title;
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

    abstract boolean compareTo(Object obj);

    abstract String whatType();

    abstract String whatRank();

    abstract String description();

}
