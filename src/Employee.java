import java.util.ArrayList;

enum Identifier {
    // This is not the employee hierarchy.
    // This is about who can access the KPI and users of the business
    // Editor just has read/write access to KPI's
    // Role has assignment controls on roles for users
    // User has Manager and add/delete users
    // Admin has Leader and has rights to delete the business
    // Viewer has access just to read KPI

    ADMIN,
    USER,
    ROLE,
    EDITOR,
    VIEWER
}

public class Employee implements EmployeeRules{

    private Business business; // Which business they belong to. Each employee is tied to one business
    private String title; // Job title
    // Business position. Classes inherit EmployeeLadder and can access certain features regarding access to KPI's, creating/deleting users etc. At each rank, certain kpi's can be viewed and certain access rights can be shown
    private EmployeeLadder rank;
    // which KPI's the employee has no matter what the EmployeeLadder shows what KPI's they have access to
    private ArrayList<KPI> viewingList = new ArrayList<KPI>();

    public Employee() {}

    public Employee(EmployeeLadder type){
        this.rank = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }
}
