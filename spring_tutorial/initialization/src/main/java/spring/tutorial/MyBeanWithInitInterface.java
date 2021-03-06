package spring.tutorial;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class MyBeanWithInitInterface implements InitializingBean, DisposableBean {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware).
     * <p>This method allows the bean instance to perform initialization only
     * possible when all bean properties have been set and to throw an
     * exception in the event of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such
     *                   as failure to set an essential property) or if initialization fails.
     */
    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println("Init MyBean with Init interface");

        if (null == name) {

            this.name = "Anonymous";
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyBean{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * Invoked by a BeanFactory on destruction of a singleton.
     *
     * @throws Exception in case of shutdown errors.
     *                   Exceptions will get logged but not rethrown to allow
     *                   other beans to release their resources too.
     */
    @Override
    public void destroy() throws Exception {

        System.out.println("Destroying my bean with interface...");
    }
}
