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

        Business b = Settings.getBusiness();
        EmployeeLadder el = Settings.getType(type);
        b.addKpiToList(el, Settings.createKpiObject(kpiName, kpi));
        b.printLinks();
        System.out.println();
        return true;
    }

    public static void removeKPI(String kpi, String kpiName) {
        Business b = Settings.getBusiness();
        b.removeKPI(kpi, kpiName);
    }

    // We need to delete on references of the business obj
    // Employees are the ones with references
    public static void deleteBusiness() {
        // Deleting business from employees
        Business b = Settings.getBusiness();
//        for(Employee e: b.getEmployees()){
//            e.setBusiness(null);
//        }
//        // Deleting employee from user
//        User usr = Login.getLoggedIn();
//        usr.getEmployee().remove(Settings.getEmployee());
        for(User u : Login.userList()){
//            for(Employee e: u.getEmployee()){
//                if(e.getBusiness().equals(b)){
//                    u.deleteEmployee(b);
//                }
//            }

            if(u.deleteEmployee(b)){
                System.out.println("Deleting");
            }
        }

        Settings.save();
    }

}
