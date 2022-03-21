public class AnalystType extends EmployeeLadder{

    public AnalystType() {
        super(
                new Identifier[]{
                        Identifier.VIEWER,
                        Identifier.EDITOR
                }
        );
    }

    public AnalystType(Identifier[] access) {
        super(access);
    }

    @Override
    public String showInfoMetric() {
        return "Analyst";
    }
}
