import java.awt.*;

public class Analyst extends Employee{

    public Analyst(String title) {
        super(title, Conts.ANALYST, "Analyst stuff");
    }

    @Override
    void formLayout(Panel panel) {

    }

    @Override
    public boolean compareTo(Object obj) {
        return obj instanceof Analyst;
    }


}
