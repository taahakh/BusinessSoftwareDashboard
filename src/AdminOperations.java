import java.io.IOException;
import java.util.Set;

public class AdminOperations {

    private static Employee assignEmployee(String username, String type) {
        switch (type){
            case "admin":
                return new Admin(username);
            case "analyst":
                return new Analyst(username);
            case  "analystleader":
                return new AnalystLeader();
        }

        return new DefaultEmployee();
    }

    // username, title, rank, business
    public static boolean addUser(String username, String type) {
        boolean exists = false;
        try{
            exists = Login.checkUserExists(username);
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }

        if(exists){
            User usr = (User) Login.loadObject(username);
            Employee em = assignEmployee(Settings.getEmployee().getTitle(), type);
            em.setBusiness(Settings.getBusiness());
            if(usr.addEmployeeSafely(em, Settings.getEmployee().getTitle())){
                Settings.getBusiness().addEmployee(em);
                Settings.save();
                usr.saveUser();
            }
            return true;
        }
        return false;
    }

    public static boolean removeUser(String username){
        boolean exists = false;
        try{
            exists = Login.checkUserExists(username);
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }

        if(exists){
            User usr = (User) Login.loadObject(username);
            Employee bus = usr.removeEmployeeWithBusiness(Settings.getBusiness());
            Settings.getBusiness().removeEmployee(bus);
            Settings.save();
            usr.saveUser();
            return true;
        }

        return false;
    }

    public static void changeRank(String username, String type) {

    }
}
