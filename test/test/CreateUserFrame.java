import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUserFrame extends Frame {

    public void closeFrame(){
        this.dispose();
    }

    public CreateUserFrame(){

        Panel layout = Panels.basicPanel();
        TextField usr, pwd, name;
        Label u, p, n, notif;
        Button submit, business, leave;

        this.setLayout(new FlowLayout());

        usr = new TextField();
        pwd = new TextField();
        name = new TextField();

        u = new Label("Username");
        p = new Label("Password");
        n = new Label("Name");
        notif = new Label("");

        submit = new Button(Conts.SUBMIT);
        business = new Button("Create business");
        leave = new Button("Leave");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usr.getText();
                String password = pwd.getText();
                String n = name.getText();
                if (username.isEmpty() || password.isEmpty() || n.isEmpty()){
                    notif.setText("These boxes cannot be empty");
                } else {
                    if(Login.createUserInterface(username, password, n)){
                        closeFrame();
                        System.out.println("Successfukl");
                        Popup p = new Popup();
                        p.setTitle("User created successfully");
                        p.add(business);
                        p.add(leave);

                        leave.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                p.closeFrame();
                                closeFrame();
                                Login.resetLoggedIn();
                                new LoginFrame().setVisible(true);
                            }
                        });

                        business.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                p.closeFrame();
                                closeFrame();
                                new BusinessFrame().setVisible(true);
                            }
                        });

                        p.addWindowListener(new CompleteClose());
                        p.launch();

                    } else {
                        notif.setText("We couldn't create a user. Try again");
                    }
                }
            }
        });



        layout.add(u);
        layout.add(usr);
        layout.add(p);
        layout.add(pwd);
        layout.add(n);
        layout.add(name);
        layout.add(submit);
        layout.add(notif);

        this.add(layout);

        this.addWindowListener(new CompleteClose());
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
