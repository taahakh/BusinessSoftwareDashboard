public class Leader extends Employee {

    public Leader(){
        super();
    }

    @Override
    public boolean compareTo(Object obj) {
        return obj instanceof Leader;
    }

    @Override
    String whatType() {
        return "leader";
    }

    @Override
    public String description() {
        return "null";
    }


}
