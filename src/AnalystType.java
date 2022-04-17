import java.awt.*;

public class AnalystType extends EmployeeLadder{

    private final String[] links = {
            Conts.ANALYST, Conts.ANALYST_LEADER, Conts.ANALYST_SALES
    };

    private static final String DESC = "this is for analysts";
    private static final String METRIC = "Contains KPI types for Analysts";

    public AnalystType() {
        super(
                new Identifier[]{
                        Identifier.VIEWER,
                        Identifier.EDITOR
                },
                DESC,
                METRIC
        );
    }

    public AnalystType(Identifier[] access) {
        super(access, DESC, METRIC);
    }

    @Override
    public boolean compare(Object obj) {
        return obj instanceof AnalystType;
    }

    @Override
    boolean check(String kpi) {
        return true;
    }

    public String[] getLinks() {
//        ArrayList<String> list = new ArrayList<String>();
//        for(String em : Settings.availableEmployees){
//            Employee e = Settings.getEmployee(em);
//            if(e != null) {
//                if(e.getLadder().compareTo(new AnalystType())){
//                    list.add(em);
//                }
//            }
//        }
//        String[] items = (String[]) list.toArray();
        return links;
    }
}
