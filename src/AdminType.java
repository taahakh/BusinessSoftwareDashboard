import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminType extends EmployeeLadder{

    public AdminType(){
        super(new Identifier[]{
                Identifier.ADMIN,
                Identifier.USER,
                Identifier.ROLE,
                Identifier.VIEWER,
                Identifier.EDITOR,
        });
    }

    @Override
    public String showInfoMetric() {
        return "Type: ADMIN\n" +
                "Access Rights: ADMIN, USER, ROLE, VIEWER, EDITOR";
    }

    @Override
    public Panel showRights() {
        Panel p = Panels.basicPanel();
//        Button settings = new Button("Settings");
//        settings.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new SettingsFrame(getAccess()).setVisible(true);
//            }
//        });
//        p.add(settings);
//        p.add(new Label("This is admin"));
        return p;
    }

    @Override
    public Panel showKpis() {
        Panel p = Panels.basicPanel();
//        ArrayList<KPI> list = getLevelList();
//        System.out.println(list.size());
//        for(KPI a : list) {
//            System.out.println(a.getIndicatorName() + a.getClassName());
//        }
//        Button[] buttons = new Button[list.size()];
//        int counter = 0;
//        for(KPI x: list){
//            buttons[counter] = new Button(x.getClassName());
//            p.add(buttons[counter]);
//        }

        return p;
    }
}
