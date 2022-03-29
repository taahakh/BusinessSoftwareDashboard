import java.io.Serializable;
import java.util.ArrayList;

public class Business implements Serializable {

    private static final long serialVersionUID = 1L;
    public static ArrayList<Business> business = new ArrayList<Business>();
//    public static Business current;

    private String name; // name of the business
    private ArrayList<KPI> indicators; // all kpi's that belong to that business
    private ArrayList<Employee> employees; // all employees that the business has

    public Business(String name, ArrayList<KPI> indicators, ArrayList<Employee> employees) {
        this.name = name;
        this.indicators = indicators;
        this.employees = employees;
    }

    public static Business createBusiness(String name, ArrayList<KPI> indicators, ArrayList<Employee> employees) {
        Business temp = new Business(name, indicators, employees);
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
}
