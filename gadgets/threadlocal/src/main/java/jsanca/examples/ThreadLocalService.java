package jsanca.examples;

public class ThreadLocalService {

    private static ThreadLocal<String> local = new ThreadLocal<String>();

    public void setMessage (final String message) {

        local.set(message);
    }

    public String getMessage () {

        return (null != local.get())?
                local.get():"";
    }

}
