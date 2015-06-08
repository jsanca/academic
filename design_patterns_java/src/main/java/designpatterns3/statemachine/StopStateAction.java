package designpatterns3.statemachine;

/**
 * Perform an play action
 * @author jsanca
 */
public class StopStateAction implements State {

    @Override
    public void handleIn(Context context) {

        System.out.println("Stopping the video: " + context.get("video"));
    }

    @Override
    public void handleOut(Context context) {

        System.out.println("The vide: " + context.get("video") + " was stopped");
    }
}
