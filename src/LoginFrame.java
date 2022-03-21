import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends Frame {

    public void closeFrame(){
        this.dispose();
    }

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
                        closeFrame();
                        new DashboardFrame().setVisible(true);
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
}
