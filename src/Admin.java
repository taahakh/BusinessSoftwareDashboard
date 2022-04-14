public class Admin extends Employee{

    public Admin(String title){
        super(title, new AdminType());
    }

    @Override
    public boolean compareTo(Object obj) {
        return obj instanceof Admin;
    }

    @Override
    String whatType() {
        return "admin";
    }

    @Override
    public String description() {
        return "Access rights etc";
    }

}
