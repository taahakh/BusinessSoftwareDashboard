public class Settings {
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
}
