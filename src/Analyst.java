public class Analyst extends Employee{
    public Analyst(){
        super(new AnalystType());
    }

    @Override
    boolean compareTo(Object obj) {
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
    String whatRank() {
        return "analyst";
    }

    @Override
    String description() {
        return "null";
    }

    public Analyst(String title) {
        super(title, new AnalystType());
    }
}
