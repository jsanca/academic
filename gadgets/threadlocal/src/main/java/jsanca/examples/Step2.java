package jsanca.examples;

public class Step2 implements Runnable {

    public void doStep () {

        ThreadLocalService threadLocalService =
                new ThreadLocalService();

        System.out.println("Step2, current message: " +
                threadLocalService.getMessage());

        threadLocalService.setMessage(threadLocalService.getMessage() + " Step2 " + Thread.currentThread().getName());

        System.out.println("Step2, current message after set: " +
                threadLocalService.getMessage());
    }

    @Override
    public void run() {

        this.doStep();
    }
}
