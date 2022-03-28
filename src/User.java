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
        Login.userSave(this);
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
            if(emp.getBusiness() == bus){
                temp = emp;
                employee.remove(emp);
                return temp;
            }
        }
        return temp;
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
//        boolean exists = false;
        for(Employee x: employee) {
            System.out.println("x: " + x.whatType());
            System.out.println("em: " + em.whatType());

            System.out.println("xName: " + x.getBusiness().getName());
            System.out.println("businessName: " + businessName);
            if(x.whatType().equals(em.whatType()) && x.getBusiness().getName().equals(businessName)){
//                exists = true;
                System.out.println("Exists");
                return false;
            }
        }

        employee.add(em);

        return true;
    }

}
