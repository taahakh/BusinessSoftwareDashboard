import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username; // Login information
    private String password;

    private String name; // Name identification
    private ArrayList<Employee> employee; // Account access to businesses

    public User(String username, String password) {
        this.username= username;
        this.password= password;
        this.name = "";
        this.employee = new ArrayList<Employee>(0);
    }

    public User(String username, String password, String name) {
        this.username= username;
        this.password= password;
        this.name = name;
        this.employee = new ArrayList<Employee>(0);
    }

    public boolean confirmPassword(String password){
        return this.password.equals(password);
    }

    public void saveUser(){
//        Login.userSave(this);
        Settings.save();
    }

    public ArrayList<Employee> getEmployee() {
        return employee;
    }

    public void removeEmployee(Employee em) {
        employee.remove(em);
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

    // Check if the user is already added to the business
    // We check if the business name matches and their rank matches
    // NOTE: we can add multiple ranks for each business. It is safe
    // but not intended.
    public boolean addEmployeeSafely(Employee em, String businessName){
        for(Employee x: employee) {
            if(x.whatType().equals(em.whatType()) && x.getBusiness().getName().equals(businessName)){
                return false;
            }
        }

        employee.add(em);

        return true;
    }

}
