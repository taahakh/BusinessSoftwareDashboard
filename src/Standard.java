import java.awt.*;

public class Standard extends Employee{

    public Standard(String title) {
        super(title, Conts.STANDARD, "Standard type");
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
