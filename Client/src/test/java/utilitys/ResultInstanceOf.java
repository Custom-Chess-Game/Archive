package utilitys;

public class ResultInstanceOf implements Result {

    private final Class object;

    public <T> ResultInstanceOf(Class<T> object) {
        this.object = object;
    }

    @Override
    public boolean check(Object value) {
        return object.isAssignableFrom(value.getClass());
    }
}
