import java.awt.*;

public class Analyst extends Employee{

    public Analyst(String title) {
        super(title, Conts.ANALYST, "Analyst stuff");
    }

    public Analyst(String title, EmployeeLadder el, String description) {
        super(title, el, description);
    }

    @Override
    void formLayout(Panel panel) {}

    @Override
    public boolean compare(Object obj) {
        return obj instanceof Analyst;
    }


}
