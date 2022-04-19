import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Finance extends Management{

    private int amount;
    private final int[] track = new int[3];
    private int pointer;
    private boolean ready;

    public Finance(String name) {
        super(name);
        this.pointer = 0;
        this.ready = false;
    }

    @Override
    MangementFrame window() {
        System.out.println(300/3);
        MangementFrame mf = new MangementFrame();
        mf.add(template("Withdraw","Enter value to withdraw from account", true));
        mf.add(template("Deposit","Enter value to deposit from account", false));
        mf.add(showMovingAverage());
        mf.add(showAmount());
        return mf;
    }

    private Button template(String name, String description, boolean isWithdraw) {
        Button b = new Button(name);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                Label desc, success;
                TextField input = new TextField();
                Button button = new Button(Conts.SUBMIT);

                desc = new Label(description);
                success = new Label("");

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) throws NumberFormatException {
                        int temp = Integer.parseInt(input.getText());
                        boolean state;
                        if (isWithdraw) {
                            state = isSuccess(withdraw(temp), success);
                        } else {
                            state = isSuccess(deposit(temp), success);
                        }
                        if(state){
                            p.close();
                        }
                    }
                });

                p.add(desc);
                p.add(input);
                p.add(button);
                p.add(success);

                p.launch();
            }
        });
        return b;
    }

    private boolean withdraw(int x) {
        if(x > amount || x < 0){
            return false;
        }
        amount -= x;
        updateTrack();
        return true;
    }

    private boolean deposit(int x) {
        if(x < 0) {
            return false;
        }
        amount += x;
        updateTrack();
        return true;
    }

    private Button showMovingAverage() {
        Button b = new Button("Show moving average");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                Label label;
                if(!(checkFull())){
                    label = new Label("Cannot calculate moving average just yet. need more data");
                } else {
                    label = new Label(calculateMovingAverage() + "%");
                }
                p.add(label);
                p.addWindowListener(new WindowCloser());
                p.launch();
            }
        });
        return b;
    }

    private Button showAmount() {
        Button b = new Button("Show amount");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup p = new Popup();
                Label label = new Label(String.valueOf(amount));
                p.add(label);
                p.addWindowListener(new WindowCloser());
                p.launch();
            }
        });
        return b;
    }

    private int calculateMovingAverage() {
        int temp = 0;
        for(int x : track) {
            temp += x;
        }
        return temp/track.length;
    }

    private void updateTrack(){
        if(pointer == track.length){
            pointer = 0;
        }
        track[pointer] = amount;
        pointer++;
    }

    private Boolean isSuccess(boolean state, Label success){
        if(!state){
            success.setText("Couldn't process");
        }
        return state;
    }

    private boolean checkFull() {
        if (ready) {
            return true;
        }
        if(track[track.length-1] == 0){
            return false;
        }
        ready = true;
        return true;

    }
}
