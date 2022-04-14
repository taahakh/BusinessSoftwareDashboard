public class Analyst extends Employee{
    public Analyst(){
        super(new AnalystType());
    }

    @Override
    public boolean compareTo(Object obj) {
        if (obj instanceof Analyst) {
            return true;
        }
        return false;
    }

    @Override
    String whatType() {
        return "analyst";
    }

    @Override
    public String description() {
        return "null";
    }

    public Analyst(String title) {
        super(title, new AnalystType());
    }
}
