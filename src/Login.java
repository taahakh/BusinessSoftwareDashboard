import java.io.*;
import java.util.ArrayList;

public class Login {

    private static User loggedIn;

    private static ArrayList<User> users = new ArrayList<User>(3);

    private final static String fileLocation = "/Users/taaha/Documents/savedata/";

    public static ArrayList<User> userList(){
        return users;
    }

    public static User getUser(String username) {
        User temp = null;
        for (User usr: users) {
            if(usr.getUsername().equals(username)){
                return usr;
            }
        }
        return temp;
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

        for (User x:  users){
            System.out.println(x.getUsername());
        }
        return users;
    }

    public static Boolean authenticate(String username, String password){

        try {
            users = readUserObject();
        }catch (IOException e) {
            return false;
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
        } catch (IOException e) {}
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
        } catch (IOException e) {}
    }

    //  Unsafe method. Bypasses any authentication processes where needed
    // NOTE: the object is not casted when loaded
    public static Object loadObject(String name) {
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
