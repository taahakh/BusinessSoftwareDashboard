import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class should be in use when the user has been logged in and in dashboard
* These functions will not work if the Settings (Employee, Business) and Login (User, Users) variables have not been set
* These functions already assume that you have been logged in
* */

public class Operations {

    // Adds users to business by giving the username and employee type
    // We go through checks if the user exists at first
    // As well as if we are given a valid type
    // A new employee object is appended to the user employee list
    public static boolean addUser(String username, String type) {
        if (Login.checkUserExists(username)) {
            Employee currentEmployee = Settings.getEmployee();
            // Loading in user
            User usr = Login.getUser(username);
            // Creating a new employee
            Employee newEmployee = Settings.getEmployee(type, usr.getName());

            if(newEmployee == null) {
                return false;
            }

            KPIGroup group = currentEmployee.getGroup();

            if(!(group.compare(new AdminType()))) {
                if(!(group.compare(newEmployee.getGroup()))) {
                    return false;
                }
            }
            // Setting title of the employee to its type
            newEmployee.setTitle(type);
            // Setting username for employee
            newEmployee.setUsername(usr.getUsername());

            // Linking employee with business
            newEmployee.setBusiness(Settings.getBusiness());
            // Linking employee to user
            if (usr.addEmployeeSafely(newEmployee, currentEmployee.getBusiness().getName())) {
                Business b = Settings.getBusiness();
                // Linking business with employee
                b.addEmployee(newEmployee);
                Settings.save();
                return true;
            }
        }
        return false;
    }

    //  Removing user from the business
    public static boolean removeUser(String username) {
        // Deletes all instances of the employee and user connecting to the business
        if (Login.checkUserExists(username)) {
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

    // Generates buttons for KPI list
    public static ArrayList<Button> generateKPIButtons(ArrayList<KPI> kpis, boolean editable) {
        ArrayList<Button> buttons = new ArrayList<Button>(kpis.size());
        for (KPI kpi : kpis) {
            buttons.add(generateKPIButton(kpi, editable));
        }

        return buttons;
    }

    public static ArrayList<Button> generateKPIButtons(ArrayList<KPI> kpis, String kpi, boolean editable) {
        ArrayList<Button> buttons = new ArrayList<>();
        for(KPI k : kpis){
            if (k.compare(Settings.getKPI(kpi))){
                buttons.add(generateKPIButton(k, editable));
            }
        }
        return buttons;
    }

    private static Button generateKPIButton(KPI kpi, boolean editable) {
        Button temp = new Button(kpi.getClassName() + ": " + kpi.getIndicatorName());
        temp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kpi.showKpi(editable).setVisible(true);
            }
        });

        return temp;
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

        Business b = Settings.getBusiness();
        Employee e = Settings.getEmployee();
        KPIGroup group = Settings.getType(type);

        // checking if nothing has returned null due to incorrect user input
        if (group == null) {
            return false;
        }

        // We see if it matches the rules or not to append to this group
        if (!(group.check(kpi))) {
            return false;
        }
        // We want people who do not have the admin rank not to change/give admin permissions
        if (group.hasIdentifier(Identifier.ADMIN)) {
            if (!(e.getGroup().hasIdentifier(Identifier.ADMIN))) {
                return false;
            }
        }

        KPIGroup employeeGroup = e.getGroup();

        // Unless it is of the admin type, we want users to only add kpis/users to their own respective groups
        if(!(employeeGroup.compare(new AdminType()))){
            if(!(employeeGroup.compare(group))){
                return false;
            }
        }

        b.addKpiToList(group, Settings.getKPI(kpiName, kpi));
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
        Settings.getBusiness().setName(name);
        Settings.save();
    }

    private static Panel displayPanels(String desc) {
        Panel p = Panels.basicPanel();
        p.setLayout(new GridLayout(0, 1));
        p.add(new Label(desc));
        p.add(new Label(""));
        return p;
    }

    // Settings
    public static Panel displayEmployeeTypes() {
        Panel p = displayPanels("Copy the employee user type");
        p.add(new Label());
        for (String employees : Settings.getAvailableEmployees()) {
            Employee em = Settings.getEmployee(employees, "");
            if(em != null) {
                p.add(new Label(employees + ": " + em.description()));
            }
        }

        return p;
    }

    public static Panel displayEmployeeRank() {
        Panel p = displayPanels("");
        p.add(new Label("NOTE: these are KPI groups. You can choose which group you want to add to."));
        p.add(new Label("Employees are LINKED to the groups meaning they don't necessarily belong to that group"));
        p.add(new Label("However, with the naming conventions, it semantically means they belong to each other"));
        p.add(new Label("You can only add roles to the group you are linked to. e.g analystleader linked to analyst"));
        p.add(new Label("If you are linked to admin, you can add to any of these groups but KPI adding rules still apply"));
        p.add(new Label("KPIs of the same class cannot have the same name but the same name can exist for different KPIs"));
        p.add(new Label());
        p.add(new Label("Copy the role type"));
        for (String rank : Settings.getAvailableRanks()) {
            KPIGroup el = Settings.getType(rank);
            if(el != null){
                p.add(new Label(rank + ": " + el.description()));
            }
        }
        p.add(new Label());
        return p;
    }

    public static Panel displayKPIs() {
        Panel p = displayPanels("Copy the kpi type");
        for (String kpis : Settings.getAvailableKpis()) {
            KPI kpi = Settings.getKPI(kpis);
            p.add(new Label(kpis + ": " + kpi.description()));
        }

        return p;
    }

    public static String viewEmployees(String type) {
        Employee e = Settings.getEmployee(type);
        StringBuilder items = new StringBuilder();
        if(e != null) {
            for (Employee em: Settings.getBusiness().getEmployees()) {
                if(em.compare(e)){
                    items.append("--->").append(em.getUsername()).append("\n");
                }
            }
        }

        return items.toString();
    }

    // Account details
    public static Button printDetails() {
        Button b = new Button("Details of account");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KPIRules rules = Settings.getEmployee().getGroup();
                Popup p = new Popup();
                p.setLayout(new GridLayout(0, 1));
                p.add(new Label(rules.description()));
                p.add(new Label(rules.provideKeyMetric()));
                p.launch();
            }
        });
        return b;
    }

    // Management
    public static void addManagementToPanel(Panel panel) {
        ArrayList<Management> m = Settings.getBusiness().getManagement();
        for(Management man: m) {
            panel.add(man.viewWindow());
        }
    }


}
