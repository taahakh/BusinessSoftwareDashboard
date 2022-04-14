import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Operations {

    public static boolean addUser(String username, String type) {
        if (checkUserExists(username)) {
            Employee e = Settings.getEmployee();
            // Loading in user
            User usr = Login.getUser(username);
            // Creating a new employee
            Employee em = Settings.getEmployee(type, e.getTitle());
            if(em == null) {
                return false;
            }
            // Linking employee with business
            em.setBusiness(Settings.getBusiness());
            // Linking employee to user
            if (usr.addEmployeeSafely(em, e.getTitle())) {
                Business b = Settings.getBusiness();
                // Linking business with employee
                b.addEmployee(em);
                Settings.save();
//                usr.saveUser();
                return true;
            }
        }
        return false;
    }

    //  Removing user from the business
    public static boolean removeUser(String username) {
        // Deletes all instances of the employee and user connecting to the business
        if (checkUserExists(username)) {
            User usr = Login.getUser(username);
            Business b = Settings.getBusiness();
            // Deleting employee from user - Lets return employee
            Employee em = usr.removeEmployeeWithBusiness(b);
            // Deleting employee from business
            b.removeEmployee(em);

            Settings.save();

            return true;

        }
        return false;
    }


    private static boolean checkUserExists(String username) {
        return Login.checkUserExists(username);
    }

    // Generates buttons for KPI list
    public static Button[] generateKPIButtons(ArrayList<KPI> kpis, boolean editable) {
        Button[] buttons = new Button[kpis.size()];
        int counter = 0;
        System.out.println(kpis);
        for (KPI x : kpis) {
            Button button = new Button(x.getClassName() + ": " + x.getIndicatorName());
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
        Employee e = Settings.getEmployee();
        EmployeeLadder el = Settings.getType(type);
        if (el == null) {
            return false;
        }
        // We want people who do not have the admin rank not to change/give admin permissions
        if (el.has(Identifier.ADMIN)) {
            if (!(e.hasIdentifier(Identifier.ADMIN))) {
                return false;
            }
        }
        b.addKpiToList(el, Settings.createKpiObject(kpiName, kpi));
        b.printLinks();
        return true;
    }

    // removes kpi COMPLETELY from all ranks
    public static boolean removeKPI(String kpi, String kpiName) {
        Business b = Settings.getBusiness();
        return b.removeKPI(kpi, kpiName);
    }

    // We need to delete on references of the business obj
    // Employees are the ones with references
    public static void deleteBusiness() {
        // Deleting business from employees
        Business b = Settings.getBusiness();
        for (User u : Login.userList()) {
            u.deleteEmployee(b);
        }

        Settings.save();
    }

    public static void renameBusiness(String name) {
        Business b = Settings.getBusiness();
        b.setName(name);
        Settings.save();
    }

    public static Panel displayEmployeeTypes() {
        Panel p = Panels.basicPanel();
        p.setLayout(new GridLayout(0, 1));
        p.add(new Label("Copy the employee user type"));
        p.add(new Label(""));
        for (String employees : Settings.availableEmployees) {
            Employee em = Settings.getEmployee(employees, "");
            try {
                p.add(new Label(employees + ": " + em.description()));
            } catch (NullPointerException ignored) {
            }
        }

        return p;
    }

    public static Panel displayEmployeeRank() {
        Panel p = Panels.basicPanel();
        p.setLayout(new GridLayout(0, 1));
        p.add(new Label("Copy the role type"));
        p.add(new Label(""));
        for (String rank : Settings.availableRanks) {
            try {
                EmployeeLadder el = Settings.getType(rank);
                p.add(new Label(rank + ": " + el.description()));
            } catch (NullPointerException ignored) {}
        }
        return p;
    }

    public static Panel displayKPIs() {
        Panel p = Panels.basicPanel();
        p.setLayout(new GridLayout(0, 1));
        p.add(new Label("Copy the kpi type"));
        p.add(new Label(""));
        for (String kpis : Settings.availableKpis) {
            try {
                KPI kpi = Settings.createKpiObject("", kpis);
                p.add(new Label(kpis + ": " + kpi.description()));
            }catch (NullPointerException ignored){}
        }

        return p;
    }

}
