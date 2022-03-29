import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {

    private final static String fileLocation = "/Users/taaha/Documents/savedata/";
    private final static String userTable = fileLocation + "data.csv";
    private static User loggedIn;
    private static ArrayList<Employee> tempEmployee;

    private static ArrayList<String[]> readUserTable() throws IOException {
        final int tempLen = 2;
        BufferedReader reader = new BufferedReader(new FileReader(userTable));
        ArrayList<String[]> table = new ArrayList<String[]>();
        while (true) {
            String line = reader.readLine();
            if(line == null) {
                break;
            }

            Scanner sc = new Scanner(line);
            sc.useDelimiter(",");

            String[] temp = new String[tempLen];
            int counter = 0;

            while(sc.hasNext()){
                temp[counter]= sc.next();
                counter++;
            }

            table.add(temp);

        }

        reader.close();
        return table;
    }

    private static boolean checkFileExists() {
        return new File(userTable).exists();
    }

    public static boolean checkUserExists(String username) throws IOException {
        ArrayList<String[]> list = readUserTable();
        for(String[] x: list){
            if(x[0].equals(username)){
                return true;
            }
        }
        return false;
    }

    public static Boolean authenticate(String username, String password){
        ArrayList<String[]> table = null;
        try {
          table = readUserTable();
        } catch (IOException e) {
            System.out.println(e);
        }
        for(String[] x: table){
            if(x[0].equals(username)){
                if(x[1].equals(password)){
                    setLoggedIn(loadUser(username, password));
                    tempEmployee = loggedIn.getEmployee();
                    return true;
                }
            }
        }
        return false;
    }


    public static User createUser(String username, String password){
        User temp = null;
        try {
            FileWriter fw = new FileWriter(userTable, true);
            fw.append("\n");
            fw.append(username);
            fw.append(",");
            fw.append(password);
            fw.flush();
            fw.close();
            temp = new User(username, password);
            userSave(temp);
        } catch (IOException e) {
            System.out.println(e);
            return temp;
        }
        return temp;
    }

    public static boolean createUserInterface(String username, String password, String name){
        User usr = createUser(username, password);
        if(usr == null){
            return false;
        }
        usr.setName(name);
        usr.saveUser();
        setLoggedIn(usr);
        return true;
    }

    public static void userSave(User usr){
        try {
            FileOutputStream fs = new FileOutputStream(fileLocation + usr.getUsername() + ".txt");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(usr);
            os.flush();
            os.close();
            fs.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static User loadUser(String username, String password){
        try {
            FileInputStream fs = new FileInputStream(fileLocation+ username+".txt");
            ObjectInputStream os = new ObjectInputStream(fs);
            User usr = (User) os.readObject();
            return usr;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
            System.out.println("User was found in directory - recreating class");
            userSave(new User(username, password));
        }
        return new User("","");
    }

    public static void setLoggedIn(User loggedIn) {
        Login.loggedIn = loggedIn;
    }

    public static User getLoggedIn() {
        return loggedIn;
    }

    public static <T extends Serializable> void saveObjects(T obj, String name) {
        try {
            FileOutputStream fs = new FileOutputStream(fileLocation + name + ".txt");
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
            FileInputStream fs = new FileInputStream(fileLocation + name + ".txt");
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
