package spring.tutorial.aop3;

public class SimpleCredentials implements Credentials {


    private String userName;
    private String password;


    public SimpleCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public String getPassword() {

        return this.password;
    }
}
