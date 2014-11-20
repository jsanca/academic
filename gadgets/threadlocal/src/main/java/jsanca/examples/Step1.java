package jsanca.examples;


public class Step1 implements Runnable {

    public void doStep () {

        ThreadLocalService threadLocalService =
                new ThreadLocalService();

        System.out.println("Step1, current message: " +
                threadLocalService.getMessage());

        threadLocalService.setMessage
                (threadLocalService.getMessage() + " Step1 " + Thread.currentThread().getName());

        System.out.println("Step1, current message after set: " +
                threadLocalService.getMessage());
    }

    @Override
    public void run() {

        this.doStep();
    }
}
