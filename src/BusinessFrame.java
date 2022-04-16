import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BusinessFrame extends Frame {

    public void closeFrame(){
        this.dispose();
    }

    public BusinessFrame(){

        String[] list = Settings.availableKpis;

        Panel layout;
        Button submit;
        Label name, error;
        TextField nameInput;

        name = new Label("Enter name for the company");
        error = new Label();

        nameInput = new TextField();

        Checkbox [] boxes = new Checkbox[list.length];
        TextField [] fields = new TextField[list.length];

        int counter = 0;

        this.setLayout(new FlowLayout());
        layout = new Panel();
        layout.setLayout(new GridLayout(0,1));
        layout.setVisible(true);

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

        submit = new Button("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameInput.getText().isEmpty()){
                    error.setText("The business name box cannot be empty");
                    return;
                }

                ArrayList<KPI> items = new ArrayList<KPI>();

                for(Checkbox box: boxes){
                    // Looking for selected boxed
                    if(box.getState()){
                        // checking for fields
                        for(TextField tf : fields){
                            // if the fields name is related to the box
                            if(tf.getName().equals(box.getLabel())){
                                // if the name is not empty then we can add it
                                if(!(tf.getText().isEmpty())){
                                    items.add(Settings.createKpiObject(tf.getText(), box.getLabel()));
                                } else {
                                    // boxes cannot be empty
                                    error.setText("The kpi name boxes cannot be empty");
                                    return;
                                }
                            }
                        }
                    }
                }

                User usr = Login.getLoggedIn();

                // Lets create the business first
                Business b = Business.createBusiness(nameInput.getText());
                // Lets create the new Employee of type Admin
                Admin a = new Admin(nameInput.getText());
                // He himself is an employee of the business so we add him to the business
                b.addEmployee(a);
                // Lets link the type of admin to the list of KPI's
                b.linkLadderList(new AdminType(), items);
                // Lets add business to admin
                a.setBusiness(b);
                // Lets now link the employee to the user
                usr.addEmployee(a);


                Settings.setEmployee(a); // Setting global variables
                Settings.setBusiness(b);
                Settings.save();
                closeFrame();
                new DashboardFrame().setVisible(true);
            }
        });

        layout.add(submit);
        layout.add(name);
        layout.add(nameInput);
        layout.add(error);

        this.add(layout);

        this.addWindowListener(new WindowCloser());
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
