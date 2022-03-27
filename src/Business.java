import java.io.Serializable;
import java.util.ArrayList;

public class Business implements Serializable {

    public static ArrayList<Business> business = new ArrayList<Business>();
    public static Business current;

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
        current = temp;
        return current;
    }

    public String getName(){
        return name;
    }
}
