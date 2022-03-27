import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LoginFrame extends Frame {

    public void closeFrame(){
        this.dispose();
    }
    public Employee chosen;

    public LoginFrame(){
        Panel layout;
        TextField usr, pwd;
        Label usrLabel, pwdLabel, notif;
        Button submit, newUser;

        this.setLayout(new FlowLayout());
        layout = new Panel();
        layout.setLayout(new GridLayout(0,1));
        layout.setVisible(true);

        usrLabel = new Label("Username");
        usr = new TextField();
        pwdLabel = new Label("Password");
        pwd = new TextField();
        submit = new Button("Submit");
        newUser = new Button("Create user");
        notif = new Label();

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
                closeFrame();
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
        this.addWindowListener(new WindowCloser());

        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void createPopup(){
        User user = Login.getLoggedIn();
        if(user.getEmployee().size() == 0) {
            showNoEmployee();
        }
        Popup p = showEmployee(user.getEmployee());
        p.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                Business.current = chosen.getBusiness();
                p.closeFrame();
                closeFrame();
                new DashboardFrame().setVisible(true);
            }
        });

    }

    public void showNoEmployee(){
        Popup p = new Popup();
        Label text = new Label("You are not linked to any business. Make sure your employer adds you");
        Button button = new Button("Close");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.closeFrame();
            }
        });
        p.add(text);
        p.add(button);

        p.launch();
    }

    public Popup showEmployee(ArrayList<Employee> em) {
        Popup p = new Popup();
        Button[] buttons = new Button[em.size()];
        int i = 0;
        for (Employee x: em){
            Button b = new Button(x.getTitle());
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String employee = ((Button) e.getSource()).getLabel();
                    for(Employee x : em) {
                        if(x.getTitle().equals(employee)){
                            chosen = x;
                            p.closeFrame();
                            return;
                        }
                    }
                }
            });
            buttons[i] = b;
            p.add(buttons[i]);
            i++;
        }

        p.launch();
        return p;
    }

}
