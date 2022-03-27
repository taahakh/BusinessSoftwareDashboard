import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Popup extends Frame {
    public Popup() {
        this.setLayout(new GridLayout(1,2));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ((Frame) e.getSource()).dispose();
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
}
