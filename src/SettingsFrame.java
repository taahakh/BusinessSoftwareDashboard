import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends Frame {

    public SettingsFrame(Identifier[] identifiers){
        Panel layout;

        this.setLayout(new FlowLayout());
        layout = new Panel();
        layout.setLayout(new GridLayout(0,1));
        layout.setVisible(true);

        layout.add(getCustomPanel(identifiers));
        this.add(layout);

        this.addWindowListener(new WindowCloser());
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public Panel getCustomPanel(Identifier[] identifiers){
        Panel p = new Panel();

        for (Identifier i: identifiers){
            switch (i){
                case EDITOR:
                    p.add(editorPanel());
                    break;
                case VIEWER:
                    p.add(viewerPanel());
                    break;
                case USER:
                    p.add(userPanel());
                    break;
                case ADMIN:
                    p.add(adminPanel());
                    break;
                case ROLE:
                    p.add(rolePanel());
                    break;
                default:
                    break;
            }
        }

        return p;
    }

    public Panel editorPanel() {
        Panel p = new Panel();
        return p;
    }

    public Panel viewerPanel() {
        Panel p = new Panel();
        return p;
    }

    public Panel userPanel() {
        Panel p = new Panel();
        return p;
    }

    public Panel adminPanel() {
        Panel p = new Panel();
        p.setLayout(new FlowLayout());

        Label add, remove, success, type;
        TextField addTF, removeTF, typeTF;
        Button addB, removeB;

        add = new Label("Add user to business");
        remove = new Label("Remove user from businesss");
        type = new Label("Enter user type");
        success = new Label("");

        addTF = new TextField();
        removeTF = new TextField();
        typeTF = new TextField();

        addB = new Button("Add user");
        removeB = new Button("Remove user");

        addB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(typeTF.getText().isEmpty() || addTF.getText().isEmpty()){
                    success.setText("These boxes cannot be empty");
                    return;
                }
                if(Operations.addUser(addTF.getText(), typeTF.getText())){
                    success.setText("Operation successfull");
                } else {
                    success.setText("Error finding user or couldnt add type");
                }
            }
        });

        removeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Operations.removeUser(removeTF.getText())){
                    success.setText("Remove successfull");
                } else {
                    success.setText("Remove failed :(");
                }
            }
        });

        p.add(add);
        p.add(addTF);
        p.add(type);
        p.add(typeTF);
        p.add(addB);
        p.add(remove);
        p.add(removeTF);
        p.add(removeB);
        p.add(success);

        return p;
    }

    public Panel rolePanel() {
        Panel p = new Panel();
        Button submit;
        Label kpi, kpiName, role;
        TextField kpiTF, kpiNameTF, roleTF;

        kpi = new Label("KPI Type");
        kpiName = new Label("Name");
        role = new Label("Role");

        kpiTF = new TextField();
        kpiNameTF = new TextField();
        roleTF = new TextField();

        submit = new Button("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Operations.addKPItoBusiness("munch", "Sales");
                Operations.assignKPItoType(roleTF.getText(), kpiTF.getText(), kpiNameTF.getText());
            }
        });

        p.add(role);
        p.add(roleTF);
        p.add(kpi);
        p.add(kpiTF);
        p.add(kpiName);
        p.add(kpiNameTF);
        p.add(submit);

        return p;
    }
}
