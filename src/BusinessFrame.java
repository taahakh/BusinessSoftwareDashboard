import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BusinessFrame extends Frame {

    public ArrayList<KPI> items = new ArrayList<KPI>();

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
//                        items.add(Settings.createKpiObject("", box.getLabel()));
                    }
                }

                Admin em = new Admin(nameInput.getText());
                ArrayList<Employee> aem = new ArrayList<Employee>();
                aem.add(em);
                em.getLadder().setLevelList(items);
                Business main = Business.createBusiness(nameInput.getText(), items, aem);
                em.setBusiness(main);

                Login.getLoggedIn().addEmployee(em);
                Login.saveObjects(main, Settings.BUS_FILENAME);   // business save
                Login.saveObjects(em, Settings.EM_FILENAME);      // Employee save
                Login.getLoggedIn().saveUser();         // user save

                Settings.setEmployee(em); // Setting global variables
                Settings.setBusiness(main);

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
