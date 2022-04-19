public class Rules {
    public boolean compare(Object obj) {
        return obj.getClass().equals(getClass());
    };
}
