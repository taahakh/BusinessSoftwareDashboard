import java.awt.*;

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
    boolean compareTo(Object obj) {
        if(obj instanceof AdminType){
            return true;
        }
        return false;
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
    public Frame showKpis() {
        Frame f = Panels.basicWindow();
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

//        return p;

        return f;
    }
}
