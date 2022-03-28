public class DefaultEmployee extends Employee{
    public DefaultEmployee(){
        super();
    }

    @Override
    String whatType() {
        return "default";
    }
}
