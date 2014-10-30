package spring.tutorial;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class ShutdownHookBean implements BeanFactoryAware, Runnable {

    private ConfigurableListableBeanFactory factory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

        if (beanFactory  instanceof ConfigurableListableBeanFactory) {

            factory = (ConfigurableListableBeanFactory)beanFactory;
            Runtime.getRuntime().addShutdownHook(new Thread(this));
        }
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        if (null != factory) {

            System.out.println("Starting the singleton destruction");
            factory.destroySingletons();
            System.out.println("Destruction done");
        }
    }
}
