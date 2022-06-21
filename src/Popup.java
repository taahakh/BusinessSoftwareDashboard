import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
* Simple Popup Frame
* */

public class Popup extends Frame {
    public Popup() {
        this.setLayout(new GridLayout(1,2));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ((Frame) e.getSource()).dispose();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
            }
        });
    }

    public void closeFrame(){
        this.dispose();
    }

    public void launch(){
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void close() {
        this.dispose();
        Settings.save();
    }
}
