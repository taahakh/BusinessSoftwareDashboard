public class Analyst extends Employee{
    public Analyst(){
        super(new AnalystType());
    }

    public Analyst(String title) {
        super(new AnalystType());
        setTitle(title);
    }
}
