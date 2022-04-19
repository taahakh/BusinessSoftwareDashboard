import java.io.Serializable;
import java.util.ArrayList;

public final class User implements Serializable {
    private static final long serialVersionUID = 1L;

    // Note: there is no current functionality to change username/password so it's set to final
    private final String username; // Login information
    private final String password;

    private String name; // Name identification
    private final ArrayList<Employee> employee; // Account access to businesses

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.employee = new ArrayList<Employee>(0);
    }

    public boolean confirmPassword(String password){
        return this.password.equals(password);
    }

    public ArrayList<Employee> getEmployee() {
        return employee;
    }

    public Employee removeEmployeeWithBusiness(Business bus) {
        Employee temp = null;
        for(Employee emp: employee){
            if(emp.getBusiness().getName().equals(bus.getName())){
                System.out.println("remove success");
                temp = emp;
                employee.remove(emp);
                return temp;
            }
        }
        return temp;
//        throw new RuntimeException();
    }

    public String getUsername(){
        return this.username;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addEmployee(Employee employee){
        this.employee.add(employee);
    }

    public boolean addEmployeeSafely(Employee em, String businessName){
        for(Employee x: employee) {
            if(x.compare(em) && x.getBusiness().getName().equals(businessName)){
                return false;
            }
        }

        employee.add(em);

        return true;
    }

    public Boolean deleteEmployee(Business b) {
        for(Employee e: employee){
            if(e.getBusiness().equals(b)){
                return employee.remove(e);
            }
        }
        return false;
    }
}
