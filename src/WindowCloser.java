import java.awt.event.*;
/*
*Closes the frame only, not the program
*/
class WindowCloser extends WindowAdapter{
    public void windowClosing(WindowEvent evt) {
        evt.getWindow().dispose();
    }

}
