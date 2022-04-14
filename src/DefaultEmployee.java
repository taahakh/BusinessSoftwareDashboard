public class DefaultEmployee extends Employee{
    public DefaultEmployee(){
        super();
    }

    @Override
    public boolean compareTo(Object obj) {
        return obj instanceof DefaultEmployee;
    }

    @Override
    String whatType() {
        return "default";
    }

    @Override
    public String description() {
        return "null";
    }
}
