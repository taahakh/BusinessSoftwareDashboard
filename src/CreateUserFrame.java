import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUserFrame extends Frame {

    public void closeFrame(){
        this.dispose();
    }

    public CreateUserFrame(){

        Panel layout;
        TextField usr, pwd, name;
        Label u, p, n, notif;
        Button submit, business, leave;

        this.setLayout(new FlowLayout());
        layout = new Panel();
        layout.setLayout(new GridLayout(0,1));
        layout.setVisible(true);

        usr = new TextField();
        pwd = new TextField();
        name = new TextField();

        u = new Label("Username");
        p = new Label("Password");
        n = new Label("Name");
        notif = new Label("");

        submit = new Button("Submit");
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
                        System.out.println("Successfukl");
                        Popup p = new Popup();
                        p.add(business);
                        p.add(leave);

                        leave.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                p.closeFrame();
                                closeFrame();
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

        this.add(layout);

        this.addWindowListener(new WindowCloser());
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
