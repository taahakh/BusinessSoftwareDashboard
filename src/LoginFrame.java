import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LoginFrame extends Frame {

    private boolean isClosed = false;

    public LoginFrame(){
        Panel layout = Panels.basicPanel();
        TextField usr, pwd;
        Label usrLabel, pwdLabel, notif;
        Button submit, newUser;

        this.setLayout(new FlowLayout());

        usrLabel = new Label("Username");
        pwdLabel = new Label("Password");
        submit = new Button("Submit");
        newUser = new Button("Create user");
        notif = new Label();

        usr = new TextField();
        pwd = new TextField();

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usr.getText();
                String password = pwd.getText();
                if(username.isEmpty() || password.isEmpty()){
                    notif.setText("These boxes cannot be empty");
                } else {
                    notif.setText("");
                    if(!Login.authenticate(username, password)){
                        notif.setText("Username/Password is wrong");
                    } else {
                        System.out.println(Login.getLoggedIn().getUsername());
                        createPopup();
                    }
                }
            }
        });

        newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CreateUserFrame().setVisible(true);
            }
        });

        layout.add(usrLabel);
        layout.add(usr);
        layout.add(pwdLabel);
        layout.add(pwd);
        layout.add(submit);
        layout.add(newUser);
        layout.add(notif);

        this.add(layout);

        this.addWindowListener(new CompleteClose());
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void createPopup(){
        User user = Login.getLoggedIn();
        if(user.getEmployee().size() == 0) {
            showNoEmployee();
        } else {
            Popup p = showEmployee(user.getEmployee());
            p.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    super.windowClosed(e);
                    p.closeFrame();
                    dispose();
                    if(!(isClosed)) {
                        new LoginFrame().setVisible(true);
                    }
                }
            });
        }
    }

    public void showNoEmployee(){
        Popup p = new Popup();
        Label text = new Label("You are not linked to any business. Make sure your employer adds you");
        p.add(text);
        p.addWindowListener(new WindowCloser());
        p.launch();
    }

    // If you are linked to a business
    public Popup showEmployee(ArrayList<Employee> em) {
        Popup p = new Popup();
        p.setTitle("Select business");
        p.setPreferredSize(new Dimension(300,100));
        Button[] buttons = new Button[em.size()];
        int i = 0;
        for (int j=0; j<em.size(); j++) {
            Employee emp = em.get(j);
            Business empBusiness = emp.getBusiness();
            Button b = new Button(empBusiness.getName() + ": "+ emp.getTitle());
            b.setName(String.valueOf(j));
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String employee = ((Button) e.getSource()).getName();
                    int pointer = Integer.parseInt(employee);
                    Settings.setEmployee(emp);
                    Settings.setBusiness(empBusiness);
                    isClosed = true;
                    p.closeFrame();
                    new DashboardFrame().setVisible(true);
                    return;
                }
            });
            buttons[i] = b;
            p.add(buttons[i]);
            i++;
        }

        p.addWindowListener(new WindowCloser());

        p.launch();
        return p;
    }

}
