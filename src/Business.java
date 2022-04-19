import java.io.Serializable;
import java.util.*;

public class Business implements Serializable {

    private static final long serialVersionUID = 1L;
    public static ArrayList<Business> business = new ArrayList<Business>();

    private String name; // name of the business
    private final ArrayList<Employee> employees; // all employees that the business has
    private final ArrayList<EmployeeLadder> ladderKpis;
    private final ArrayList<Management> management;

    public Business(String name) {
        this.name = name;
        this.employees = new ArrayList<Employee>();
        this.ladderKpis = new ArrayList<>();
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

    public ArrayList<KPI> compare(EmployeeLadder type) {
        for(EmployeeLadder k: ladderKpis){
            if(type.compare(k)){
                return k.getKpis();
            }
        }

        throw new RuntimeException("okkkkk");
    }

    public ArrayList<KPI> getTotalKpis() {
        return compare(new AdminType());
    }

    public void linkLadderList(EmployeeLadder e, ArrayList<KPI> k) {
        e.setKpis(k);
        ladderKpis.add(e);
    }

    public void linkLadderList(String type, ArrayList<KPI> kpi) {
        EmployeeLadder e = Settings.getType(type);
        e.setKpis(kpi);
        ladderKpis.add(e);
    }

    public boolean hasLadderLink(EmployeeLadder e) {
        for (EmployeeLadder x: ladderKpis){
            if(e.compare(x)){
                return true;
            }
        }

        return false;
    }

    public void addKpiToList(EmployeeLadder e, KPI k) {
        // Linking employee rank with KPI
        if(hasLadderLink(e)) {
            if(!(appendKPI(new AdminType(), k))){
                System.out.println("repat");
                return;
            }
            appendKPI(e,k);
        } else {
            // no ladders exists so we need to create a new link
            ArrayList<KPI> list = new ArrayList<KPI>();
            list.add(k);
            linkLadderList(e, list);
        }
        // We link admin employee with all kpis
        appendKPI(new AdminType(), k);
        Settings.save();
    }

    private boolean appendKPI (EmployeeLadder e, KPI k) {
        // checks for any ladders that exists
        for (EmployeeLadder x: ladderKpis){
            if(e.compare(x)){
                return append(x.getKpis(), k);
            }
        }

        return false;
    }

    private boolean append(ArrayList<KPI> list, KPI k) {
        for (KPI x: list) {
            if(x.getIndicatorName().equals(k.getIndicatorName()) && x.getClassName().equals(k.getClassName())){
                return false;
            }
        }
        list.add(k);
        return true;
    }

    public void printLinks() {
        for(EmployeeLadder x: ladderKpis){
            System.out.println("Ladder: " + x + " List: " + x.getKpis());
        }
    }

    public ArrayList<KPI> returnLadderKPI(EmployeeLadder ladder) {
        for (EmployeeLadder e : ladderKpis) {
            if(e.compare(ladder)){
                return e.getKpis();
            }
        }
        return null;
    }

    public ArrayList<KPI> returnLadderKPI(String item) {
        EmployeeLadder ladder = Settings.getType(item);
        for (EmployeeLadder e : ladderKpis) {
            if(e.compare(ladder)){
                return e.getKpis();
            }
        }
        return null;
    }

    public boolean removeKPI(String kpi, String kpiName) {
        KPI temp = null;
        ArrayList<KPI> list = returnLadderKPI(new AdminType());
        for (KPI k : list) {
            if(k.getIndicatorName().equals(kpiName) && k.getClassName().equals(kpi)){
                temp = k;
                list.remove(k);
                break;
            }
        }

        if(temp == null){
            return false;
        }

//        boolean remove;

        for (EmployeeLadder e : ladderKpis){
            e.getKpis().remove(temp);
        }

        printLinks();

        Settings.save();

        return true;
    }

    public EmployeeLadder assignType(EmployeeLadder el) {
        for (EmployeeLadder x: ladderKpis){
            if(el.compare(x)){
                el.setKpis(x.getKpis());
                return el;
            }
        }

        ladderKpis.add(el);
        Settings.save();

        return el;
    }

    public ArrayList<Management> getManagement(){
        return management;
    }

}
