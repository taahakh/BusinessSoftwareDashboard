import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Business implements Serializable {

    private static final long serialVersionUID = 1L;
    public static ArrayList<Business> business = new ArrayList<Business>();

    private String name; // name of the business
    private ArrayList<KPI> indicators; // all kpi's that belong to that business
    private ArrayList<Employee> employees; // all employees that the business has
    private HashMap<EmployeeLadder, ArrayList<KPI>> ladderKpis;

    public Business(String name) {
        this.name = name;
        this.indicators = new ArrayList<KPI>();
        this.employees = new ArrayList<Employee>();
        this.ladderKpis = new HashMap<EmployeeLadder, ArrayList<KPI>>();
    }

    public Business(String name, ArrayList<KPI> indicators, ArrayList<Employee> employees) {
        this.name = name;
        this.indicators = indicators;
        this.employees = employees;
        this.ladderKpis = new HashMap<EmployeeLadder, ArrayList<KPI>>();
    }

    public static Business createBusiness(String name, ArrayList<KPI> indicators, ArrayList<Employee> employees) {
        Business temp = new Business(name, indicators, employees);
        business.add(temp);
        Settings.setBusiness(temp);
        return Settings.getBusiness();
    }

    public static Business createBusiness(String name) {
        Business temp = new Business(name);
        business.add(temp);
        Settings.setBusiness(temp);
        return Settings.getBusiness();
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

    public void save(){
        Login.saveObjects(this, Settings.BUS_FILENAME);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addKPI(KPI kpi) {
        indicators.add(kpi);
    }

    public boolean kpiExists(String indicator, String type) {
        for(KPI x : indicators){
            if(x.getIndicatorName().equals(indicator) && x.getClassName().equals(type)){
                return true;
            }
        }
        return false;
    }

    public boolean kpiExists(KPI indicator) {
        return indicator.equals(indicator);
    }

    public ArrayList<Employee> getEmployees(){
        return employees;
    }

    public ArrayList<KPI> getKPIList() {
        return indicators;
    }

    public Map<EmployeeLadder, ArrayList<KPI>> getLadderKpis() {
        return ladderKpis;
    }

    public void setLadderKpis(HashMap <EmployeeLadder, ArrayList<KPI>> ladderKpis) {
        this.ladderKpis = ladderKpis;
    }

    public ArrayList<KPI> comapreTo(EmployeeLadder type) {
        for(EmployeeLadder k: ladderKpis.keySet()){
            if(type.compareTo(k)){
                return ladderKpis.get(k);
            }
        }

        throw new RuntimeException("okkkkk");
    }

    public ArrayList<KPI> getTotalKpis() {
        return comapreTo(new AdminType());
    }

    public void linkLadderList(EmployeeLadder e, ArrayList<KPI> k) {
        ladderKpis.put(e,k);
    }

    public boolean hasLadderLink(EmployeeLadder e) {
        for (EmployeeLadder x: ladderKpis.keySet()){
            if(e.compareTo(x)){
                return true;
            }
        }

        return false;
    }

    public void addKpiToList(EmployeeLadder e, KPI k) {
        if(hasLadderLink(e) == true) {
            appendKPI(e,k);
        } else {
            // no ladders exists so we need to create a new link
            ArrayList<KPI> list = new ArrayList<KPI>();
            list.add(k);
            linkLadderList(e, list);
        }
        Settings.save();
    }

    public boolean appendKPI (EmployeeLadder e, KPI k) {
        // checks for any ladders that exists
        for (EmployeeLadder x: ladderKpis.keySet()){
            if(e.compareTo(x)){
                return append(ladderKpis.get(x), k);
            }
        }

        return false;
    }

    public boolean append(ArrayList<KPI> list, KPI k) {
        for (KPI x: list) {
            if(x.getIndicatorName().equals(k.getIndicatorName())){
                return false;
            }
        }
        list.add(k);
        return true;
    }

    public void printLinks() {
        for(EmployeeLadder x: ladderKpis.keySet()){
            System.out.println("Ladder: " + x + " List: " + ladderKpis.get(x));
        }
    }

    public ArrayList<KPI> getKPILadderList(EmployeeLadder e) {
        for (EmployeeLadder x: ladderKpis.keySet()) {
            if(x.compareTo(e)){
                return ladderKpis.get(x);
            }
        }

        throw new RuntimeException("OH NO");
    }
}
