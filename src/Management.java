import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public abstract class Management implements CompareRules, Serializable {
    private final String name;

    public Management(String name) {
        this.name = name;
    }

    public Button viewWindow() {
        Button b = new Button(name);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window().setVisible(true);
            }
        });
        return b;
    }

    public String getName() {
        return name;
    }

    abstract MangementFrame window();
}
