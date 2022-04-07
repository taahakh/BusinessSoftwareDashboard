import java.io.Serializable;
import java.util.ArrayList;
import java.awt.*;

public abstract class EmployeeLadder implements Ladder, Serializable {
    private Identifier[] access;

    public EmployeeLadder(Identifier[] access){
        this.access = access;
    }

    public Identifier[] getAccess() {
        return access;
    }

    public void setAccess(Identifier[] access) {
        this.access = access;
    }

    public boolean has(Identifier iden) {
        for(Identifier x : access) {
            if(x.equals(iden)){
                return true;
            }
        }
        return false;
    }

    abstract boolean compareTo(Object obj);

    abstract Panel showRights();

    abstract Panel showKpis();
}
