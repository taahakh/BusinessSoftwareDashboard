import java.io.*;
import java.util.ArrayList;

public class Login {

    private static User loggedIn;
    private static ArrayList<User> users = new ArrayList<>(3);
    private final static String fileLocation = "./savedata/";

    public static ArrayList<User> userList(){
        return users;
    }

    public static User getUser(String username) {
        for (User usr: users) {
            if(usr.getUsername().equals(username)){
                return usr;
            }
        }
        return null;
    }

    private static ArrayList<User> readUserObject() throws IOException {

        File f = new File(fileLocation+Settings.USER_FILENAME);
        if(!(f.exists())){
            f.createNewFile();
            userSave();
        }

        if(users.size() == 0){
            users = (ArrayList<User>) loadObject(Settings.USER_FILENAME);
        }

        return users;
    }

    public static Boolean authenticate(String username, String password){

        try {
            users = readUserObject();
        } catch (IOException e) {
            return false;
        }

        for(User usr: users){
            if(usr.getUsername().equals(username) && usr.confirmPassword(password)){
                setLoggedIn(usr);
                return true;
            }
        }

        return false;
    }

    public static User createUser(String username, String password) {
        return new User(username, password);
    }

    public static boolean checkUserExists(String username){
        for(User usr: users){
            if(usr.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public static void addUser(User usr) {
        users.add(usr);
        userSave();
    }

    // Creating user and setting variables up
    public static boolean createUserInterface(String username, String password, String name){
        try {
            readUserObject();
        } catch (IOException e){}

        if(checkUserExists(username)) {
            return false;
        }

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
        } catch (IOException e) {}
    }

    private static void setLoggedIn(User loggedIn) {
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
        } catch (IOException e) {}
    }

    //  Unsafe method. Bypasses any authentication processes where needed
    // NOTE: the object is not casted when loaded
    // However we know what file belongs to which object
    private static Object loadObject(String name) {
        Object obj = null;
        try {
            FileInputStream fs = new FileInputStream(fileLocation + name);
            ObjectInputStream os = new ObjectInputStream(fs);
            obj = os.readObject();
            os.close();
            fs.close();
        } catch (IOException | ClassNotFoundException e) {

        }

        return obj;
    }

}
