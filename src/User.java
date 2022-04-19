import java.io.Serializable;
import java.util.ArrayList;

/*
* Basic template for Users
* Stores employees and credentials
* NOTE: This model allows it so that you can be multiple employees to multiple businesses or in fact the same business
* */

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

    public String getUsername(){
        return this.username;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Employee> getEmployee() {
        return employee;
    }

    // -------------------------------------

    public Employee removeEmployeeWithBusiness(Business bus) {
        Employee temp = null;
        for(Employee emp: employee){
            if(emp.getBusiness().getName().equals(bus.getName())){
                temp = emp;
                employee.remove(emp);
                return temp;
            }
        }
        return temp;
//        throw new RuntimeException();
    }

    public void addEmployee(Employee employee){
        this.employee.add(employee);
    }

    // We check the employee type and the business name
    // You can be multiple different employee types for the same business as well
    // This makes sure that we are not adding the same employee type in the same business
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
        for(Employee em: employee){
            if(em.getBusiness().equals(b)){
                return employee.remove(em);
            }
        }
        return false;
    }
}
