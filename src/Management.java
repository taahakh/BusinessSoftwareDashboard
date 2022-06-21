import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/*
* Management Indicators. Similar to KPI's but they are heavily stripped down version
* */

public abstract class Management extends Rules implements Serializable {
    private final String name;

    public Management(String name) {
        this.name = name;
    }

    // Button than summons frame
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

    abstract Frame window();
}
