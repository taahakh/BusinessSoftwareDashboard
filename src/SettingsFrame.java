import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends Frame {

    private final Dimension textFieldDimensions = new Dimension(100, 30);

    public SettingsFrame(Identifier[] identifiers) {
        Panel layout;

        layout = new Panel();
        layout.setVisible(true);

        getCustomPanel(identifiers, layout);
        this.add(layout);

        this.addWindowListener(new WindowCloser());
        this.setSize(990, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void getCustomPanel(Identifier[] identifiers, Panel p) {
        Button temp;
        for (Identifier i : identifiers) {
            switch (i) {
                case USER:
                    temp = new Button("USERS");
                    temp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            userPanel().setVisible(true);
                            dispose();
                        }
                    });
                    p.add(temp);
                    break;
                case ADMIN:
                    temp = new Button("ADMIN");
                    temp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            adminPanel().setVisible(true);
                            dispose();
                        }
                    });
                    p.add(temp);
                    break;
                case ROLE:
                    temp = new Button("ROLES");
                    temp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            rolePanel().setVisible(true);
                            dispose();
                        }
                    });
                    p.add(temp);
                    break;
                default:
                    break;
            }
        }

    }

    //----------------------------------------------------------------------------
    public Frame userPanel() {
        //        final String a,r,t,ab,rb,empty,suc,err,rs,rf;
//        a = "Add user to business";
//        r = "Remove user from business";
//        t = "Enter user type";
//        ab = "Add user";
//        rb = "Remove user";
//        empty = "These boxes cannot be empty";
//        suc = "Operation successful";
//        err = "Error finding user or couldn't add type";
//        rs = "Remove successful";
//        rf = "Remove failed :(";

        Frame p = Panels.basicWindow();

        Label add, remove, success, type;
        TextField addTF, removeTF, typeTF;
        Button addB, removeB;

        add = new Label("Add user to business");
        remove = new Label("Remove user from business");
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
                if (typeTF.getText().isEmpty() || addTF.getText().isEmpty()) {
                    success.setText("These boxes cannot be empty");
                    return;
                }
                if (Operations.addUser(addTF.getText(), typeTF.getText())) {
                    success.setText("Operation successful");
                } else {
                    success.setText("Error finding user or couldn't add type");
                }
            }
        });

        removeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Operations.removeUser(removeTF.getText())) {
                    success.setText("Remove successful");
                } else {
                    success.setText("Remove failed :(");
                }
            }
        });

        addTF.setPreferredSize(textFieldDimensions);
        removeTF.setPreferredSize(textFieldDimensions);
        typeTF.setPreferredSize(textFieldDimensions);

        p.add(add);
        p.add(addTF);
        p.add(type);
        p.add(typeTF);
        p.add(addB);
        p.add(remove);
        p.add(removeTF);
        p.add(removeB);
        p.add(Operations.displayEmployeeTypes());
        p.add(success);


        return p;
    }

    public Frame adminPanel() {

        Frame p = Panels.basicWindow();

        Label rename, success;
        TextField renameTF;
        Button submit;

        rename = new Label("Rename business?");

        renameTF = new TextField();
        success = new Label("");

        submit = new Button("Rename");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(renameTF.getText().isEmpty()){
                    success.setText("Cannot be empty");
                } else {
                    Operations.renameBusiness(renameTF.getText());
                    success.setText("Rename successful");
                }
            }
        });

        renameTF.setPreferredSize(textFieldDimensions);

        p.add(rename);
        p.add(renameTF);
        p.add(submit);
        p.add(success);

        p.add(deleteBusiness());

        return p;
    }

    public Frame rolePanel() {

//        final String kt = "KPI Type";
//        final String n = "Name";
//        final String r = "Role";
//        final String s = "Submit";

        Frame p = Panels.basicWindow();

        Button submit;
        Label kpi, kpiName, role;
        Label success = new Label("");
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
                if(Operations.assignKPItoType(roleTF.getText(), kpiTF.getText(), kpiNameTF.getText())){
                    success.setText("Assignment successful");
                } else {
                    success.setText("Assignment Failed");
                }
            }
        });

        kpiTF.setPreferredSize(textFieldDimensions);
        kpiNameTF.setPreferredSize(textFieldDimensions);
        roleTF.setPreferredSize(textFieldDimensions);

        p.add(role);
        p.add(roleTF);
        p.add(kpi);
        p.add(kpiTF);
        p.add(kpiName);
        p.add(kpiNameTF);
        p.add(submit);
        p.add(removeKpiPanel(success));
        p.add(Operations.displayEmployeeRank());
        p.add(Operations.displayKPIs());
        p.add(success);

        return p;
    }

    //----------------------------------------------------------------------------
    public Panel removeKpiPanel(Label success) {
        Panel p = Panels.basicPanel();

        Button submit;
        Label kpi, kpiName;
        TextField kpiTF, kpiNameTF;

        kpi = new Label("KPI to delete: ");
        kpiName = new Label("KPI name to delete");

        kpiTF = new TextField();
        kpiNameTF = new TextField();

        submit = new Button("Delete KPI");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Operations.removeKPI(kpiTF.getText(), kpiNameTF.getText())){
                    success.setText("Removal successful");
                } else {
                    success.setText("Removal failed");
                }

            }
        });

        kpiTF.setPreferredSize(textFieldDimensions);
        kpiNameTF.setPreferredSize(textFieldDimensions);

        p.add(kpi);
        p.add(kpiTF);
        p.add(kpiName);
        p.add(kpiNameTF);
        p.add(submit);

        return p;
    }

    public Panel deleteBusiness() {
        Panel p = Panels.basicPanel();

        Button submit = new Button("Delete business?");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup pop = new Popup();
                Button yes, no;
                Label text = new Label("Are you sure you want to delete this business?");

                yes = new Button("yes");
                yes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Operations.deleteBusiness();
                        pop.dispose();
                        new LoginFrame().setVisible(true);
                    }
                });

                no = new Button("no");
                no.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pop.dispose();
                    }
                });

                pop.add(text);
                pop.add(yes);
                pop.add(no);
                pop.launch();
            }
        });

        p.add(submit);

        return p;
    }

}

