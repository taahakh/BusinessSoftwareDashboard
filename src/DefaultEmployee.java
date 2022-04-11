public class DefaultEmployee extends Employee{
    public DefaultEmployee(){
        super();
    }

    @Override
    boolean compareTo(Object obj) {
        if(obj instanceof DefaultEmployee) {
            return true;
        }
        return false;
    }

    @Override
    String whatType() {
        return "default";
    }

    @Override
    String whatRank() {
        return "default";
    }

    @Override
    String description() {
        return "null";
    }
}
