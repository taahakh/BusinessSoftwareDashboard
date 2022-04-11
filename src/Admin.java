public class Admin extends Employee{

    public Admin(String title){
        super(title, new AdminType());
    }

    @Override
    boolean compareTo(Object obj) {
        return obj instanceof Admin;
    }

    @Override
    String whatType() {
        return "admin";
    }

    @Override
    String whatRank() {
        return "admin";
    }

    @Override
    String description() {
        return "Access rights etc";
    }

}
