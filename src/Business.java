import java.io.Serializable;
import java.util.*;

/*
* Stores all employees. KPIgroups, and management indicators
* Adding/Deleting employees, KPIGroup and Management manipulation occurs here as well as Class Operation
* */

public class Business implements Serializable {

    private static final long serialVersionUID = 1L;
    public static ArrayList<Business> business = new ArrayList<Business>();

    private String name; // name of the business
    private final ArrayList<Employee> employees; // all employees that the business hasIdentifier
    private final ArrayList<KPIGroup> groups;
    private final ArrayList<Management> management;

    public Business(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.management = new ArrayList<>();
        addManagementButtons();
    }

    public static Business createBusiness(String name) {
        Business temp = new Business(name);
        business.add(temp);
        Settings.setBusiness(temp);
        return Settings.getBusiness();
    }

    private void addManagementButtons(){
        management.add(new Hiring("Hiring List"));
        management.add(new EmployeeList("Employee List"));
        management.add(new Finance("Finances"));
    }

    public String getName(){
        return name;
    }

    public void addEmployee(Employee em) {
        employees.add(em);
    }

    public void removeEmployee(Employee em) {
        employees.remove(em);
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Employee> getEmployees(){
        return employees;
    }

    public ArrayList<Management> getManagement(){
        return management;
    }

    // ----------------------------------------------

    private ArrayList<KPI> compareGroup(KPIGroup type) {
        for(KPIGroup k: groups){
            if(type.compare(k)){
                return k.getKpis();
            }
        }

        throw new RuntimeException("okkkkk");
    }

    public ArrayList<KPI> getTotalKpis() {
        return compareGroup(new AdminType());
    }

    public void linkLadderList(KPIGroup group, ArrayList<KPI> kpis) {
        group.setKpis(kpis);
        groups.add(group);
    }

    public void linkLadderList(String type, ArrayList<KPI> kpis) {
        KPIGroup group = Settings.getType(type);
        group.setKpis(kpis);
        groups.add(group);
    }

    public void linkLadderList() {
        for (String type : Settings.getAvailableRanks()) {
            KPIGroup el = Settings.getType(type);
            if(!hasLadderLink(el)){
                groups.add(el);
            }
        }
    }

    public boolean hasLadderLink(KPIGroup group) {
        for (KPIGroup x: groups){
            if(group.compare(x)){
                return true;
            }
        }

        return false;
    }

    public void addKpiToList(KPIGroup group, KPI k) {
        // Linking employee rank with KPI
        if(hasLadderLink(group)) {
            if(!(appendKPI(new AdminType(), k))){
                System.out.println("repat");
                return;
            }
            appendKPI(group,k);
        } else {
            // if no ladders exists, we need to create a new link
            ArrayList<KPI> list = new ArrayList<KPI>();
            list.add(k);
            linkLadderList(group, list);
        }
        // We link admin employee with all kpis
        appendKPI(new AdminType(), k);
        Settings.save();
    }

    private boolean appendKPI (KPIGroup group, KPI k) {
        // checks for any ladders that exists
        for (KPIGroup x: groups){
            if(group.compare(x)){
                return append(x.getKpis(), k);
            }
        }

        return false;
    }

    private boolean append(ArrayList<KPI> list, KPI k) {
        for (KPI kpi: list) {
            if(kpi.getIndicatorName().equals(k.getIndicatorName()) && kpi.compare(k)){
                return false;
            }
        }
        list.add(k);
        return true;
    }

    public ArrayList<KPI> returnLadderKPI(String item) {
        KPIGroup group = Settings.getType(item);
        for (KPIGroup e : groups) {
            if(e.compare(group)){
                return e.getKpis();
            }
        }
        return null;
    }

    public boolean removeKPI(String kpi, String kpiName) {
        KPI temp = null;
        ArrayList<KPI> list = returnLadderKPI(Conts.ADMIN);
        for (KPI k : list) {
            if(k.getIndicatorName().equals(kpiName) && k.compare(kpi)){
                temp = k;
                list.remove(k);
                break;
            }
        }

        if(temp == null){
            return false;
        }

        //Removing KPI from all groups
        for (KPIGroup group : groups){
            group.getKpis().remove(temp);
        }

        Settings.save();

        return true;
    }

    // We assign KPIGroups kpis to our employees KPIGroups
    // Sounds confusing but essentially linking the group to the employee account
    public KPIGroup assignType(KPIGroup group) {
        for (KPIGroup x: groups){
            if(group.compare(x)){
                group.setKpis(x.getKpis());
                return group;
            }
        }

        groups.add(group);
        Settings.save();

        return group;
    }

}
