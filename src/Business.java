import java.io.Serializable;

public class Business implements Serializable {

    private String name; // name of the business
    private KPI[] indicators; // all kpi's that belong to that business
    private Employee [] employees; // all employees that the business has

    public Business(String name, KPI[] indicators, Employee[] employees) {
        this.name = name;
        this.indicators = indicators;
        this.employees = employees;
    }
}
