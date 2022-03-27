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
        Checkbox [] boxes = new Checkbox[list.length];
        int boxCounter = 0;

        this.setLayout(new FlowLayout());
        layout = new Panel();
        layout.setLayout(new GridLayout(0,1));
        layout.setVisible(true);

        for(String x: list){
            Checkbox box = new Checkbox(x);
            boxes[boxCounter] = box;
            boxCounter++;
            layout.add(box);
        }

        submit = new Button("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Checkbox box: boxes){
                    if(box.getState()){
                        System.out.println(box.getLabel());
                        items.add(Settings.createKpiObject(box.getLabel()));
                    }
                }
                Employee em = new Employee("Main", new AdminType());
                ArrayList<Employee> aem = new ArrayList<Employee>();
                aem.add(em);
                Business main = Business.createBusiness("temp", items, aem);
                em.setBusiness(main);
                Login.getLoggedIn().addEmployee(em);
                closeFrame();
                new DashboardFrame().setVisible(true);
            }
        });

        layout.add(submit);
        this.add(layout);

        this.addWindowListener(new WindowCloser());
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
