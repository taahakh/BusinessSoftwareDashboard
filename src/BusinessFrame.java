import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This frame is where you decide which kpis to add and the name of the business
* */

public class BusinessFrame extends Frame {

    // NOTE: This is an exact copy of the method in CreateUserFrame.
    public void closeFrame(){
        this.dispose();
    }

    public BusinessFrame(){

        String[] list = Settings.getAvailableKpis();

        Panel layout = Panels.basicPanel();
        Button submit;
        Label name, error, desc;
        TextField nameInput;

        name = new Label("Enter name for the company");
        desc = new Label("Choose any of these KPIs to start off and add their respective names");
        error = new Label();

        nameInput = new TextField();

        int length = list.length;

        Checkbox [] boxes = new Checkbox[length];
        TextField [] fields = new TextField[length];

        int counter = 0;

        this.setLayout(new FlowLayout());

        layout.add(desc);

        for(String x: list){
            Checkbox box = new Checkbox(x);
            TextField tf = new TextField();
            tf.setName(x);
            boxes[counter] = box;
            fields[counter] = tf;
            counter++;
            layout.add(box);
            layout.add(tf);
        }

        submit = new Button(Conts.SUBMIT);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameInput.getText();
                if(name.isEmpty()){
                    error.setText("The business name box cannot be empty");
                    return;
                }

                ArrayList<KPI> items = new ArrayList<KPI>();

                if(!(addKPIs(boxes, fields, items, error))){
                    return;
                }


                createAccount(name, items);
                closeFrame();
                new DashboardFrame().setVisible(true);
            }
        });

        layout.add(submit);
        layout.add(name);
        layout.add(nameInput);
        layout.add(error);

        this.add(layout);

        this.addWindowListener(new CompleteClose());
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private boolean addKPIs(Checkbox[] boxes, TextField[] fields, ArrayList<KPI> items, Label error) {
        for(Checkbox box: boxes){
            // Looking for selected boxed
            if(box.getState()){
                // checking for fields
                for(TextField tf : fields){
                    // if the fields name is related to the box
                    String kpiName = tf.getText();
                    String kpiType = box.getLabel();
                    if(tf.getName().equals(kpiType)){
                        // if the name is not empty then we can add it
                        if(!(kpiName.isEmpty())){
                            items.add(Settings.getKPI(kpiName, kpiType));
                        } else {
                            // boxes cannot be empty
                            error.setText("The kpi name boxes cannot be empty");
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private void createAccount(String name, ArrayList<KPI> items) {
        User usr = Login.getLoggedIn();

        // Lets create the business first
        Business b = Business.createBusiness(name);
        // Lets create the new Employee of type Admin
        Admin a = new Admin(name);
        // Set title for Admin
        a.setTitle(Conts.ADMIN);
        // Set username for Admin
        a.setUsername(usr.getUsername());
        // He himself is an employee of the business so we add him to the business
        b.addEmployee(a);
        // Lets link the type of admin to the list of KPI's
        b.linkLadderList(Conts.ADMIN, items);
        // Lets create links to all types
        b.linkLadderList();
        // Lets add business to admin
        a.setBusiness(b);
        // Lets now link the employee to the user
        usr.addEmployee(a);


        Settings.setEmployee(a); // Setting global variables
        Settings.setBusiness(b);
        Settings.save();
    }
}
