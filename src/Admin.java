import java.awt.*;

public class Admin extends Employee{

    public Admin(String title){
        super(title, new AdminType());
    }

    @Override
    public boolean compareTo(Object obj) {
        return obj instanceof Admin;
    }

    @Override
    public Frame features() {
        return null;
    }

    @Override
    public String description() {
        return "Access rights etc";
    }

    @Override
    Button load() {
        return null;
    }

    public void unique() {
        System.out.println("HERE");
    }
}
