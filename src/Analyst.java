public class Analyst extends Employee{
    public Analyst(){
        super(new AnalystType());
    }

    @Override
    String whatType() {
        return "analyst";
    }

    public Analyst(String title) {
        super(title, new AnalystType());
    }
}
