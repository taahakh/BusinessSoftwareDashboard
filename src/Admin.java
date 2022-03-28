public class Admin extends Employee{

    public Admin(String title){
        super(title, new AdminType());
    }

    @Override
    String whatType() {
        return "admin";
    }
}
