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
            Employee em = Settings.getEmployee(type, usr.getName());

            if(em == null) {
                return false;
            }

            if(!(e.getLadder().compare(new AdminType()))) {
                if(!(e.getLadder().compare(em.getLadder()))) {
                    return false;
                }
            }
            // Setting title of the employee to its type
            em.setTitle(type);
            // Setting username for employee
            em.setUsername(usr.getUsername());

            // Linking employee with business
            em.setBusiness(Settings.getBusiness());
            // Linking employee to user
            if (usr.addEmployeeSafely(em, e.getBusiness().getName())) {
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
    public static ArrayList<Button> generateKPIButtons(ArrayList<KPI> kpis, boolean editable) {
        ArrayList<Button> buttons = new ArrayList<Button>(kpis.size());
        for (KPI x : kpis) {
            buttons.add(generateKPIButton(x, editable));
        }

        return buttons;
    }

    public static ArrayList<Button> generateKPIButtons(ArrayList<KPI> kpis, String kpi, boolean editable) {
        ArrayList<Button> buttons = new ArrayList<>();
        for(KPI k : kpis){
//            if (k.compare(kpi)){
//                buttons.add(generateKPIButton(k, editable));
//            }
            if (k.compare(Settings.getKPI(kpi))){
                buttons.add(generateKPIButton(k, editable));
            }
        }
        return buttons;
    }

    private static Button generateKPIButton(KPI k, boolean editable) {
        Button temp = new Button(k.getClassName() + ": " + k.getIndicatorName());
        temp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                k.showKpi(editable).setVisible(true);
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
        // if we don't find an employee what that certain type, we cannot assign
        // there must be a user of that type in order to carry out operations on

        Business b = Settings.getBusiness();
        Employee e = Settings.getEmployee();
        KPIGroup el = Settings.getType(type);

        if (el == null) {
            System.out.println("Failed 1");
            return false;
        }

        if (!(el.check(kpi))) {
            System.out.println("Failed 2");
            return false;
        }
        // We want people who do not have the admin rank not to change/give admin permissions
        if (el.has(Identifier.ADMIN)) {
            if (!(e.hasIdentifier(Identifier.ADMIN))) {
                System.out.println("Failed 3");
                return false;
            }
        }

        if(!(e.getLadder().compare(new AdminType()))){
            if(!(e.getLadder().compare(el))){
                System.out.println("Failed 4");
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

    private static Panel displayPanels(String desc) {
        Panel p = Panels.basicPanel();
        p.setLayout(new GridLayout(0, 1));
        p.add(new Label(desc));
        p.add(new Label(""));
        return p;
    }

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

    public static void addManagementToPanel(Panel panel) {
        ArrayList<Management> m = Settings.getBusiness().getManagement();
        for(Management man: m) {
            panel.add(man.viewWindow());
        }
    }

}
