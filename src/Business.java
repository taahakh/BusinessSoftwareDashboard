import java.io.Serializable;
import java.util.*;

public class Business implements Serializable {

    private static final long serialVersionUID = 1L;
    public static ArrayList<Business> business = new ArrayList<Business>();

    private String name; // name of the business
    private ArrayList<Employee> employees; // all employees that the business has
    private HashMap<EmployeeLadder, ArrayList<KPI>> ladderKpis;

    public Business(String name) {
        this.name = name;
        this.employees = new ArrayList<Employee>();
        this.ladderKpis = new HashMap<EmployeeLadder, ArrayList<KPI>>();
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

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Employee> getEmployees(){
        return employees;
    }

    public Map<EmployeeLadder, ArrayList<KPI>> getLadderKpis() {
        return ladderKpis;
    }

    public void setLadderKpis(HashMap <EmployeeLadder, ArrayList<KPI>> ladderKpis) {
        this.ladderKpis = ladderKpis;
    }

    public ArrayList<KPI> compareTo(EmployeeLadder type) {
        for(EmployeeLadder k: ladderKpis.keySet()){
            if(type.compareTo(k)){
                return ladderKpis.get(k);
            }
        }

        throw new RuntimeException("okkkkk");
    }

    public ArrayList<KPI> getTotalKpis() {
        return compareTo(new AdminType());
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
        for (EmployeeLadder x: ladderKpis.keySet()){
            if(e.compareTo(x)){
                return append(ladderKpis.get(x), k);
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

        return new ArrayList<KPI>();
//        throw new RuntimeException("OH NO");
    }

    public ArrayList<KPI> returnLadderKPI(EmployeeLadder ladder) {
        ArrayList<KPI> temp = null;
        for (EmployeeLadder e : ladderKpis.keySet()) {
            if(e.compareTo(ladder)){
                return ladderKpis.get(e);
            }
        }
        return temp;
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

        for (EmployeeLadder e : ladderKpis.keySet()){
            ladderKpis.get(e).remove(temp);
        }

        printLinks();

        Settings.save();

        return true;
    }

}
