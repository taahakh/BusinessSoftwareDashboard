import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BusinessFrame extends Frame {

    public ArrayList<KPIStruct> availableKpis(){
        KPIStruct.appendArrToList(new KPIStruct[]{
                new Sales(""),
                new Inventory(""),
        });
        return KPIStruct.getList();
    }

    public KPI createKpiObject(String type) {
        switch (type){
            case "Sales":
                return new Sales("");
            case "Inventory":
                return new Inventory("");
            default:
                break;
        }
        return new DefaultKPI();
    }

    private KPIStruct findKpi(ArrayList<KPIStruct> list, String className){
        for(KPIStruct x : list){
            if(x.getClassName().equals(className)){
                return x.returnNewKPI();
            }
        }
        return new KPIStruct("","");
    }

    public BusinessFrame(){

        ArrayList<KPIStruct> list = availableKpis();

        Panel layout;
        Button submit;
        Checkbox [] boxes = new Checkbox[list.size()];
        int boxCounter = 0;

        this.setLayout(new FlowLayout());
        layout = new Panel();
        layout.setLayout(new GridLayout(0,1));
        layout.setVisible(true);

        for(KPIStruct x: list){
            Checkbox box = new Checkbox(x.getClassName());
            boxes[boxCounter] = box;
            boxCounter++;
            layout.add(box);
        }

        submit = new Button("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<KPI> items = new ArrayList<KPI>(boxes.length);
                for(Checkbox box: boxes){
                    if(box.getState()){
                        System.out.println(box.getLabel());
                        items.add(createKpiObject(box.getLabel()));
                    }
                }

                for(KPIStruct x: items){
                    System.out.println("Added to list: "+x.getClassName());
                }

                System.out.println(items.size());
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
