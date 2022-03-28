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

    public static KPI createKpiObject(String type) {
        switch (type){
            case "Sales":
                return new Sales("");
            case "Inventory":
                return new Inventory("");
            default:
                break;
        }
        return new DefaultKPI();
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
