import java.awt.*;
import java.io.Serializable;

/*
* We could've taken the route to instantiate objects only of this type
* There could've been some flexibility in creating many ranks
* However, there can be specific functionality that can come with each rank
* This would be very hard to manage without polymorphism
* Each type must be unique and there can be only one of that type per business
*/
public abstract class EmployeeLadder implements CompareRules, Serializable {
    private final Identifier[] access;

    private final String DESC;
    private final String METRIC;

    public EmployeeLadder(Identifier[] access, String description, String showInfoMetric){
        this.access = access;
        this.DESC = description;
        this.METRIC = showInfoMetric;
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

    public String description() {
        return DESC;
    }

    public String showInfoMetric(){
        return METRIC;
    }

    abstract Frame features();

}
