package jsanca.examples;

/**
 * Hello world!
 *
 */
public class AppMultiThread
{
    public static void main( String[] args )
    {

        ThreadLocalService threadLocalService =
                new ThreadLocalService();
        final String threadGroupName =
                "testThreadGroup";
        ThreadGroup threadGroup =
                new ThreadGroup(threadGroupName);

        threadLocalService.setMessage(Thread.currentThread().getName());

        Step1 step1 = new Step1();

        new Thread(threadGroup, step1, "thread1").start();
        new Thread(threadGroup, step1, "thread2").start();

        Step2 step2 = new Step2();

        new Thread(threadGroup, step2, "thread3").start();
        new Thread(threadGroup, step2, "thread4").start();


        System.out.println("Final Message: " + threadLocalService.getMessage());
    }
}
