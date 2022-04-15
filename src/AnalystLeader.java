import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnalystLeader extends Employee {

    public AnalystLeader(String title){
        super(title, new AnalystType(
                new Identifier[]{
                        Identifier.VIEWER,
                        Identifier.EDITOR,
                        Identifier.ROLE,
                        Identifier.USER
                }

        ), "Head leader of analysts of the company");
    }

    @Override
    public boolean compareTo(Object obj) {
        return obj instanceof AnalystLeader;
    }

    @Override
    void formLayout(Panel panel) {
        panel.add(viewAnalysts());
    }

    public Button viewAnalysts() {
        Button b = new Button("View analysts");
        AnalystType at = (AnalystType) getLadder();
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                StringBuilder items = new StringBuilder();
                for (String item : at.getLinks()) {
                    items.append(Operations.viewEmployees(item)).append("\n");
                }
                TextArea ta = new TextArea(items.toString());
                ta.setEditable(false);
                p.add(ta);
                p.launch();
            }
        });

        return b;
    }
}
