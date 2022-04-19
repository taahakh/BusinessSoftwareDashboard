import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Hiring extends Management {

    private final ArrayList<String> list;

    public Hiring(String name) {
        super(name);
        this.list = new ArrayList<>();
    }

//    @Override
//    public boolean compare(Object obj) {
//        return obj instanceof Hiring;
//    }

    @Override
    MangementFrame window() {
        MangementFrame mf = new MangementFrame();

        TextArea tf = new TextArea(viewList());
        tf.setEditable(false);


        mf.add(tf);
        mf.add(viewListButton(tf));
        mf.add(create("Add possible employee", "Enter name", true));
        mf.add(create("Remove name from list", "Enter a name", false));

        return mf;
    }

    private Button create(String usage, String description, boolean isAdd) {
        Button b = new Button(usage);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                Label desc, success;
                TextField value = new TextField();
                Button button = new Button(Conts.SUBMIT);

                desc = new Label(description);
                success = new Label("");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(isAdd){
                            addItem(value.getText());
                        } else {
                            removeItem(value.getText());
                        }
                    }
                });

                p.add(desc);
                p.add(value);
                p.add(button);
                p.add(success);
                p.launch();
            }
        });
        return b;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public String viewList() {
        StringBuilder str = new StringBuilder();
        for(String item : list) {
            str.append(item).append("\n");
        }
        return str.toString();
    }

    public Button viewListButton(TextArea tf) {
        Button b = new Button("View list");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setText(viewList());
            }
        });

        return b;
    }

    public void addItem(String name) {
        list.add(name);
    }

    public void removeItem(String name){
        list.remove(name);
    }
}
