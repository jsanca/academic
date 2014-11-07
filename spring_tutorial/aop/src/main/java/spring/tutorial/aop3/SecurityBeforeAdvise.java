package spring.tutorial.aop3;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SecurityBeforeAdvise implements MethodBeforeAdvice {

    private SecurityManager securityManager =
            new SecurityManager();

    @Override
    public void before(final Method   method,
                       final Object[] arguments,
                       final Object   target) throws Throwable {

        if (this.securityManager.isLogged()) {

            if (("admin".equals(this.securityManager.getCredentials().getUserName())) &&
                    ("12345".equals(this.securityManager.getCredentials().getPassword()))){

                System.out.println("The credentials are OK!");
            } else {

                throw new SecurityException("Invalid credentials");
            }
        } else {

            throw new SecurityException("No user authenticated");
        }
    } // before
}
