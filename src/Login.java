import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class LoginExceptions extends RuntimeException {
    public LoginExceptions(String msg) {
        super(msg);
    }
}

public class Login {

    private static ArrayList<User> users = new ArrayList<User>(3);

    private final static String fileLocation = "/Users/taaha/Documents/savedata/";
    private static User loggedIn;

    public static ArrayList<User> userList(){
        return users;
    }

    private static ArrayList<User> readUserObject() throws IOException {

        File f = new File(fileLocation+Settings.USER_FILENAME);
        if(!(f.exists())){
            f.createNewFile();
            userSave();
        }

        if(users.size() == 0){
            ArrayList<User> usr = (ArrayList<User>) loadObject(Settings.USER_FILENAME);
            users = usr;
        }

        for (User x:  users){
            System.out.println(x.getUsername());
        }
        return users;
    }

    public static Boolean authenticate(String username, String password){

        try {
            users = readUserObject();
        }catch (IOException e) {
            System.out.println(e);
        }

        for(User x: users){
            if(x.getUsername().equals(username) && x.confirmPassword(password)){
                setLoggedIn(x);
                return true;
            }
        }

        return false;
    }

    public static User createUser(String username, String password) {
        return new User(username, password);
    }

    public static User loadUser(String username)  {
        try {
            for (User x: readUserObject()){
                if(x.getUsername().equals(username)){
                    System.out.println("1: "+ x);
                    return x;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new RuntimeException();
    }

    public static boolean checkUserExists(String username){
        for(User x: users){
            if(x.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public static void addUser(User usr) {
        users.add(usr);
        userSave();
    }

    public static boolean createUserInterface(String username, String password, String name){
        try{
            readUserObject();
        } catch (IOException e){}

        User usr = createUser(username, password);
        usr.setName(name);
        addUser(usr);
        setLoggedIn(usr);
        Settings.save();
        return true;
    }

    public static void resetLoggedIn(){
        Login.loggedIn = null;
    }

    public static void userSave(){
        try {
            FileOutputStream fs = new FileOutputStream(fileLocation+Settings.USER_FILENAME);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(users);
            os.flush();
            os.close();
            fs.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void setLoggedIn(User loggedIn) {
        Login.loggedIn = loggedIn;
    }

    public static User getLoggedIn() {
        return loggedIn;
    }

    public static <T extends Serializable> void saveObjects(T obj, String name) {
        try {
            FileOutputStream fs = new FileOutputStream(fileLocation + name);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(obj);
            os.flush();
            os.close();
            fs.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //  Unsafe method. Bypasses any authentication processes where needed
    public static <T extends Serializable> Object loadObject(String name) {
        Object obj = null;
        try {
            FileInputStream fs = new FileInputStream(fileLocation + name);
            ObjectInputStream os = new ObjectInputStream(fs);
            obj = os.readObject();
            os.close();
            fs.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return obj;
    }
}
