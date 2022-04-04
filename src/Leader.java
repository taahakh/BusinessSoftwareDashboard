public class Leader extends Employee {

    public Leader(){
        super();
    }

    @Override
    boolean compareTo(Object obj) {
        if(obj instanceof Leader) {
            return true;
        }
        return false;
    }

    @Override
    String whatType() {
        return "leader";
    }

    @Override
    String whatRank() {
        return "leader";
    }


}
