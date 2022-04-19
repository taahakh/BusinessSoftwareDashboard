/* Tracks all the global variables for the user, business and employee class
*  These methods automatically generate KPI's, employees etc. It is up to the
*  developer to add their inclusion of their types of KPI's, employees etc.
* */
public class Settings {

    private static Employee employee;
    private static Business business;

    private final static String EM_FILENAME = "em.obj";
    private final static String BUS_FILENAME = "bus.obj";
    public  final static String USER_FILENAME = "user.obj";


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

    private static final String [] availableKpis = new String[]{
            Conts.SALES, Conts.INVENTORY, Conts.MARKETING, Conts.HUMAN_RESOURCES,
    };

    private static final String [] availableEmployees = new String[] {
            Conts.ADMIN, Conts.ANALYST, Conts.ANALYST_LEADER, Conts.ANALYST_SALES, Conts.HR_MANAGER, Conts.HR_VIWER, Conts.STANDARD
    };

    private static final String [] availableRanks = new String[] {
            Conts.ADMIN, Conts.ANALYST, Conts.HR, Conts.STANDARD
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

    public static KPI getKPI(String type) {
        return createKpiObject("", type);
    }

    public static Employee getEmployee(String type, String title) {
        switch (type.toLowerCase()){
            case Conts.ADMIN:
                return new Admin(title);
            case Conts.ANALYST:
                return new Analyst(title);
            case Conts.ANALYST_LEADER:
                return new AnalystLeader(title);
            case Conts.ANALYST_SALES:
                return new AnalystSales(title);
            case Conts.HR_MANAGER:
                return new HrManager(title);
            case Conts.HR_VIWER:
                return new HrViewer(title);
            case Conts.STANDARD:
                return new Standard(title);
        }

        return null;
    }

    public static Employee getEmployee(String type) {
        return getEmployee(type, "");
    }

    public static KPIGroup getType(String type) {
        switch (type.toLowerCase()){
            case Conts.ADMIN:
                return new AdminType();
            case Conts.ANALYST:
                return new AnalystType();
            case Conts.HR:
                return new HrType();
            case Conts.STANDARD:
                return new StandardType();
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

    public static String[] getAvailableKpis() {
        return availableKpis;
    }

    public static String[] getAvailableEmployees() {
        return availableEmployees;
    }

    public static String[] getAvailableRanks() {
        return availableRanks;
    }

}
