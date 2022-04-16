import java.awt.*;
import java.util.Locale;

/* Tracks all the global variables for the user, business and employee class
*  These methods automatically generate KPI's, employees etc. It is up to the
*  developer to add their inclusion of their types of KPI's, employees etc.
* */
public class Settings {

    private static Employee employee;
    private static Business business;

    private final static String EM_FILENAME = "em.txt";
    private final static String BUS_FILENAME = "bus.txt";
    public  final static String USER_FILENAME = "user.txt";

    /**/

    private static final String ADMIN = "admin";

    /**/


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
            Conts.SALES, Conts.INVENTORY, Conts.MARKETING, Conts.HUMAN_RESOURCES,
    };

    public static final String [] availableEmployees = new String[] {
            ADMIN, Conts.ANALYST, Conts.ANALYST_LEADER, Conts.ANALYST_SALES,
    };

    public static final String [] availableRanks = new String[] {
            ADMIN, Conts.ANALYST,
    };

    public static KPI createKpiObject(String name, String type) {
        switch (type.toLowerCase()){
            case Conts.SALES:
                return new Sales(name);
            case Conts.INVENTORY:
                return new Inventory(name);
            case Conts.MARKETING:
                return new Marketing(name);
            case Conts.HUMAN_RESOURCES:
                return new HumanResources(name);
            default:
                break;
        }
        throw new RuntimeException();
    }


    public static Employee getEmployee(String type, String title) {
        switch (type.toLowerCase()){
            case ADMIN:
                return new Admin(title);
            case Conts.ANALYST:
                return new Analyst(title);
            case Conts.ANALYST_LEADER:
                return new AnalystLeader(title);
            case Conts.ANALYST_SALES:
                return new AnalystSales(title);
        }

        return null;
    }

    public static Employee getEmployee(String type) {
        return getEmployee(type, "");
    }

    public static EmployeeLadder getType(String type) {
        switch (type.toLowerCase()){
            case ADMIN:
                return new AdminType();
            case Conts.ANALYST:
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
