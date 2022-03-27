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

}
