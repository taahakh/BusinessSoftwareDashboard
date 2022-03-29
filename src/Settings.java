import java.awt.*;

/* Tracks all the global variables for the user, business and employee class
*  These methods automatically generate KPI's, employees etc. It is up to the
*  developer to add their inclusion of their types of KPI's, employees etc.
* */
public class Settings {

    private static Employee employee;
    private static Business business;

    public final static String EM_FILENAME = "em";
    public final static String BUS_FILENAME = "bus";

    public static void save(){
        employee.save();
        business.save();
        Login.userSave(Login.getLoggedIn());
    }

    public static String [] availableKpis = new String[]{
            "Sales", "Inventory",
    };

    public static KPI createKpiObject(String name, String type) {
        switch (type){
            case "Sales":
                return new Sales(name);
            case "Inventory":
                return new Inventory(name);
            default:
                break;
        }
        throw new RuntimeException();
//        return new DefaultKPI();
    }

    public static Employee castEmployees(Employee em, String type) {
        switch (type){
            case "admin":
                return (Admin) em;
            case "analyst":
                return (Analyst) em;
            case "analystleader":
                return (AnalystLeader) em;
        }
        return em;
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
