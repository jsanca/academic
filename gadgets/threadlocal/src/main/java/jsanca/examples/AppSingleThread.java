package jsanca.examples;

/**
 * Hello world!
 *
 */
public class AppSingleThread
{
    public static void main( String[] args )
    {

        ThreadLocalService threadLocalService =
                new ThreadLocalService();

        Step1 step1 = new Step1();
        step1.doStep();

        Step2 step2 = new Step2();
        step2.doStep();

        System.out.println("Final message: " + threadLocalService.getMessage());
    }
}
