import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Operations {

    private static Employee assignEmployee(String username, String type) {
        switch (type){
            case "admin":
                return new Admin(username);
            case "analyst":
                return new Analyst(username);
            case  "analystleader":
                return new AnalystLeader();
        }

        return new DefaultEmployee();
    }

    // username, title, rank, business
    public static boolean addUser(String username, String type) {
        boolean exists = checkUserExists(username);
        if(exists){
            User usr = Login.loadUser(username);
            Employee em = assignEmployee(Settings.getEmployee().getTitle(), type);
            em.setBusiness(Settings.getBusiness());
            if(usr.addEmployeeSafely(em, Settings.getEmployee().getTitle())){
                Settings.getBusiness().addEmployee(em);
                Settings.save();
                usr.saveUser();
            }
            return true;
        }
        return false;
    }

    //  Removing user from the business
    public static boolean removeUser(String username){
        boolean exists = checkUserExists(username);
        // Deletes all instances of the employee and user connecting to the business
        if(exists){
            User usr = (User) Login.loadObject(username);
            Employee bus = usr.removeEmployeeWithBusiness(Settings.getBusiness()); // user
            if(bus == null) {
                return false;
            }
            Settings.getBusiness().removeEmployee(bus); // business
            Settings.save();
            usr.saveUser();
            return true;
        }

        return false;
    }

    public static void changeRank(String username, Identifier[] iden) {
        boolean exists = checkUserExists(username);
    }

    private static boolean checkUserExists(String username) {
//        boolean exists = false;
//        try{
//            exists = Login.checkUserExists(username);
//        } catch (IOException e) {
//            System.out.println(e);
//            return false;
//        }
//        return exists;
        return Login.checkUserExists(username);
    }

    // Generates buttons for KPI list
    public static Button[] generateKPIButtons(ArrayList<KPI> kpis, boolean editable){
        Button[] buttons = new Button[kpis.size()];
        int counter =0;
        System.out.println(kpis);
        for(KPI x: kpis){
            Button button = new Button(x.getClassName()+": "+x.getIndicatorName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    x.showKpi(editable).setVisible(true);
                }
            });
            buttons[counter] = button;
            counter++;
        }

        return buttons;
    }

    public static Button generateSettingsButton(Identifier[] iden) {
        Button settings = new Button("Settings");
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingsFrame(iden).setVisible(true);
            }
        });
        return settings;
    }

    // assigns to which class the user belongs to
    public static boolean assignKPItoType(String type, String kpi, String kpiName) {
        // we need to search the employee list that belong to a certain class
        // we can access the list for that class and append it there for all users
        // if we don't find an employee what that certain type, we cannot assign
        // there must be a user of that type in order to carry out operations on

//        Business bus = Settings.getBusiness();
//        EmployeeLadder el = null;
//        Employee em = null;
//        for(Employee x : bus.getEmployees()){
//            System.out.println("Employee type: " + x.whatType());
//            if(x.whatType().equals(type)){
//                System.out.println("accepted");
//                el = x.getLadder();
//                em = Settings.castEmployees(x, type);
//                break;
//            }
//        }
//
//        if(el == null){
//            return false;
//        }
//
//        if(el.hasKPI(kpi, kpiName)){
//            return false;
//        }
//
//        KPI k = Settings.createKpiObject(kpiName, kpi);
//        em.getLadder().add(k);
//        System.out.println(em.whatType());
//        System.out.println(em.getLadder().getLevelList());
//        bus.addKPI(k);
//        bus.save();
////        em.save();
////        el.add(Settings.createKpiObject(kpiName, kpi));
//        Settings.save();
//        System.out.println("here");

        Business b = Settings.getBusiness();
        EmployeeLadder el = Settings.getType(type);
        System.out.println(type);
        System.out.println(kpi);
        System.out.println(kpiName);
        System.out.println(el);
//        Map<EmployeeLadder, ArrayList<KPI>> map = b.getLadderKpis();
//        for (EmployeeLadder x: map.keySet()) {
//            if(x.compareTo(el)){
//                System.out.println(true+" it does exist");
//            }
//        }
        b.addKpiToList(el, Settings.createKpiObject(kpiName, kpi));
        b.printLinks();
        System.out.println();
        return true;
    }

    public static boolean addKPItoBusiness(String name, String type) {
        Business bus = Settings.getBusiness();
        if(bus.kpiExists(name, type)) {
            return false;
        }
        bus.addKPI(Settings.createKpiObject(name, type));
        Settings.save();
        return true;
    }

}
