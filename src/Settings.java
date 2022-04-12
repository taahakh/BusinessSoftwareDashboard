import java.awt.*;
import java.util.Locale;

/* Tracks all the global variables for the user, business and employee class
*  These methods automatically generate KPI's, employees etc. It is up to the
*  developer to add their inclusion of their types of KPI's, employees etc.
* */
public class Settings {

    private static Employee employee;
    private static Business business;

    public final static String EM_FILENAME = "em.txt";
    public final static String BUS_FILENAME = "bus.txt";
    public final static String USER_FILENAME = "user.txt";

    public static void save(){
        Login.saveObjects(employee, EM_FILENAME);
        Login.saveObjects(business, BUS_FILENAME);
        Login.userSave();
    }

    public static void logout() {
        Settings.save();
        employee = null;
        business = null;
        Login.resetLoggedIn();
    }

    public static final String [] availableKpis = new String[]{
            "Sales", "Inventory", "Marketing", "HumanResources",
    };

    public static final String [] availableEmployees = new String[] {
            "Admin", "Analyst", "AnalystLeader",
    };

    public static final String [] availableRanks = new String[] {
            "Admin", "Analyst",
    };

    public static KPI createKpiObject(String name, String type) {
        switch (type.toLowerCase()){
            case "sales":
                return new Sales(name);
            case "inventory":
                return new Inventory(name);
            case "marketing":
                return new Marketing(name);
            case "humanresources":
                return new HumanResources(name);
            default:
                break;
        }
        throw new RuntimeException();
//        return new DefaultKPI();
    }

    public static Employee castEmployees(Employee em, String type) {
        switch (type.toLowerCase()){
            case "admin":
                return (Admin) em;
            case "analyst":
                return (Analyst) em;
            case "analystleader":
                return (AnalystLeader) em;
        }
        return em;
    }

    public static Employee getEmployee(String type) {
        switch (type.toLowerCase()){
            case "admin":
                return new Admin("");
            case "analyst":
                return new Analyst();
            case "analystleader":
                return new AnalystLeader("");
        }

        return null;
    }

    public static EmployeeLadder getType(String type) {
        switch (type.toLowerCase()){
            case "admin":
                return new AdminType();
            case "analyst":
                return new AnalystType();
        }
        return null;
    }

    public static Employee getEmployee(){
        return employee;
    }

    public static void setEmployee(Employee e){
        employee = e;
    }

    public static Business getBusiness(){
        return business;
    }

    public static void setBusiness(Business b){
        business = b;
    }

}
