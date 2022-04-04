public class Admin extends Employee{

    public Admin(String title){
        super(title, new AdminType());
    }

    @Override
    boolean compareTo(Object obj) {
        if(obj instanceof Admin) {
            return true;
        }
        return false;
    }

    @Override
    String whatType() {
        return "admin";
    }

    @Override
    String whatRank() {
        return "admin";
    }

}
