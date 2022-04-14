import java.io.Serializable;

public abstract class EmployeeLadder implements EmployeeRules, Serializable {
    private final Identifier[] access;

    public EmployeeLadder(Identifier[] access){
        this.access = access;
    }

    public Identifier[] getAccess() {
        return access;
    }

    public boolean has(Identifier iden) {
        for(Identifier x : access) {
            if(x.equals(iden)){
                return true;
            }
        }
        return false;
    }

    abstract String showInfoMetric();
}
