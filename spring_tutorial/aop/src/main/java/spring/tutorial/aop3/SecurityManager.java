package spring.tutorial.aop3;

public class SecurityManager {

    private static ThreadLocal<Credentials> threadLocal = new ThreadLocal<Credentials>();

    public void login (final String u, final String p) {

        threadLocal.set(new SimpleCredentials(u, p));
    }

    public boolean isLogged () {

        return null != threadLocal.get();
    }

    public void logout () {

        threadLocal.set(null);
    }

    public Credentials getCredentials () {

        return threadLocal.get();
    }
}
