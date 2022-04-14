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
    private static final String ANALYST = "analyst";
    private static final String ANALYST_LEADER = "analystleader";

    /**/

    private static final String SALES = "sales";
    private static final String INVENTORY = "inventory";
    private static final String MARKETING = "marketing";
    private static final String HUMAN_RESOURCES = "humanresources";

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
            SALES, INVENTORY, MARKETING, HUMAN_RESOURCES,
    };

    public static final String [] availableEmployees = new String[] {
            ADMIN, ANALYST, ANALYST_LEADER,
    };

    public static final String [] availableRanks = new String[] {
            ADMIN, ANALYST,
    };

    public static KPI createKpiObject(String name, String type) {
        switch (type.toLowerCase()){
            case SALES:
                return new Sales(name);
            case INVENTORY:
                return new Inventory(name);
            case MARKETING:
                return new Marketing(name);
            case HUMAN_RESOURCES:
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
            case ANALYST:
                return new Analyst(title);
            case ANALYST_LEADER:
                return new AnalystLeader(title);
        }

        return null;
    }

    public static EmployeeLadder getType(String type) {
        switch (type.toLowerCase()){
            case ADMIN:
                return new AdminType();
            case ANALYST:
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
