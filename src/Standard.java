import java.awt.*;

/*
* Simple class that has no special rules or features
* */

public class Standard extends Employee{

    public Standard(String title) {
        super(title, Conts.STANDARD, "There are no special rules. This means that any KPI's can be added here");
    }

    @Override
    void formLayout(Panel panel) {
        panel.setLayout(new GridLayout(1,1));
    }
}
