public class AnalystLeader extends Employee {

    public AnalystLeader(){
        super(new AnalystType(
                new Identifier[]{
                        Identifier.VIEWER,
                        Identifier.EDITOR,
                        Identifier.ROLE,
                        Identifier.USER
                }
        ));
    }

}
