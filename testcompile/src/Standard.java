import java.awt.*;

public class Standard extends Employee{

    public Standard(String title) {
        super(title, Conts.STANDARD, "There are no special rules. This means that any KPI's can be added here");
    }

    @Override
    public boolean compare(Object obj) {
        return false;
    }

    @Override
    void formLayout(Panel panel) {
        panel.setLayout(new GridLayout(0,0));
    }
}
