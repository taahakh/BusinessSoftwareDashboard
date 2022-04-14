public class AnalystLeader extends Employee {

    public AnalystLeader(String title){
        super(title, new AnalystType(
                new Identifier[]{
                        Identifier.VIEWER,
                        Identifier.EDITOR,
                        Identifier.ROLE,
                        Identifier.USER
                }
        ));
    }

    @Override
    public boolean compareTo(Object obj) {
        if(obj instanceof AnalystLeader) {
            return true;
        }
        return false;
    }

    @Override
    String whatType() {
        return "analystleader";
    }

    @Override
    public String description() {
        return "Head leader of analysts of the company";
    }

}
