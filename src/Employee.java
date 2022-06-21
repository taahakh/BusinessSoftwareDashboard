import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;


public abstract class Employee extends Rules implements Serializable {

    private Business business; // Which business they belong to. Each employee is tied to one business

    private String title; // Job title. Assigned by the employee subclass
    private KPIGroup group;

    private String description;
    private String username;

    public Employee() {}

    public Employee(KPIGroup type){
        this.group = type;
    }

    public Employee(String title, KPIGroup type, String description){
        assign(title, type, description);
    }

    public Employee(String title, String type, String description){
        if(Settings.getBusiness() != null) {
            this.business = Settings.getBusiness();
            assign(title, business.assignType(Settings.getType(type)), description);
        }
    }

    // For constructor use
    private void assign(String title, KPIGroup type, String description) {
        this.title = title;
        this.group = type;
        this.description = description;
    }

    // --------------------------------------------------

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBusiness(Business business) {
        this.business = business;
        group = business.assignType(group);
    }

    public Business getBusiness() {
        return business;
    }

    public KPIGroup getGroup(){
        return group;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String description() {
        return description;
    }


    // --------------------------------------------------

    public ArrayList<Button> showKPIButtons() {
        if(group.hasIdentifier(Identifier.VIEWER)){
            if(group.hasIdentifier(Identifier.EDITOR)){
                return generateKPIButtons(true);
            }
            return generateKPIButtons(false);
        }

        return new ArrayList<Button>();
    }

    public Button showSettingsButton(){
        return Operations.generateSettingsButton(group.getAccess());
    }


    public ArrayList<Button> generateKPIButtons(boolean editable) {
        return Operations.generateKPIButtons(group.getKpis(), editable);
    }

    public ArrayList<Button> generateKPIButtons(Employee e, String kpi, boolean editable) {
        return Operations.generateKPIButtons(e.getGroup().getKpis(), kpi, editable);
    }

    // --------------------------------------------------

    abstract void formLayout(Panel panel);

}
