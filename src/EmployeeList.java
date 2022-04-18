import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeList extends Hiring {

    private ArrayList<String> description;
    private final TextField visual = new TextField();

    public EmployeeList(String name) {
        super(name);
        description = new ArrayList<>();
        visual.setEditable(false);
    }

    @Override
    public boolean compare(Object obj) {
        return obj instanceof EmployeeList;
    }

    @Override
    MangementFrame window() {
        MangementFrame mf = new MangementFrame();
        mf.add(visual);
        viewEmployeeList();

        mf.add(view("View Employee List", "employeeList"));
        mf.add(view("View Employees only", "onlyEmployee"));
        mf.add(add());
        mf.add(remove());

        return mf;
    }

    @Override
    public void removeItem(String name) {
        for (int i=0; i<getList().size(); i++){
            if(getList().get(i).equals(name)){
                getList().remove(i);
                description.remove(i);
                return;
            }
        }
    }

    private Button view(String name, String which) {
        Button b = new Button(name);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (which) {
                    case "employeeList":
                        viewEmployeeList();
                        break;
                    case "onlyEmployee":
                        viewEmployeesOnly();
                        break;
                }
            }
        });
        return b;
    }

    private Button add() {
        Button b = new Button("add");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                Label desc, success;
                TextField name, details;
                Button submit = new Button(Conts.SUBMIT);

                desc = new Label("Enter name and description");
                success = new Label("");

                name = new TextField();
                details = new TextField();

                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (hasName(name.getText()) || name.getText().equals("") || details.getText().equals("")){
                            success.setText("Name already exists or there are blank boxes");
                            return;
                        }
                        add(name.getText(), details.getText());
                        p.close();
                    }
                });

                p.add(desc);
                p.add(name);
                p.add(details);
                p.add(submit);
                p.add(success);
                p.launch();
            }
        });
        return b;
    }

    private Button remove() {
        Button b = new Button("Remove employee");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                Label desc, success;
                TextField tf = new TextField();
                Button submit = new Button(Conts.SUBMIT);

                desc = new Label("Enter name to remove");
                success = new Label("");

                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(tf.getText().isEmpty() || !hasName(tf.getText())) {
                            success.setText("Cannot remove");
                            return;
                        }
                        removeItem(tf.getText());
                        p.close();
                    }
                });

                p.add(desc);
                p.add(tf);
                p.add(submit);
                p.add(success);
                p.launch();
            }
        });
        return b;
    }

    private void viewEmployeeList() {
        StringBuilder text = new StringBuilder();
        for(int i=0; i<getList().size(); i++) {
            text.append(getList().get(i) +": " + description.get(i) +"\n");
        }
        visual.setText(text.toString());
    }

    private void add(String name, String description) {
        addItem(name);
        this.description.add(description);
    }

    public void viewEmployeesOnly(){
        visual.setText(super.viewList());
    }

    private boolean hasName(String name){
        return getList().contains(name);
    }

}
