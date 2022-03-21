import java.io.Serializable;

public class User implements Serializable {

    private String username; // Login information
    private String password;

    private String name; // Name identification
    private Employee[] employee; // Account access to businesses

    public User(String username, String password) {
        this.username= username;
        this.password= password;
    }

    public User(String username, String password, String name) {
        this.username= username;
        this.password= password;
        this.name = name;
    }

    public boolean confirmPassword(String password){
        return this.password.equals(password);
    }

    public void saveUser(){
        Login.userSave(this);
    }

    public Employee[] getEmployee() {
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


}
