package designpatterns3.statemachine;

/**
 * Perform an play action
 * @author jsanca
 */
public class PauseStateAction implements State {

    @Override
    public void handleIn(Context context) {

        System.out.println("Pausing the video: "  + context.get("video"));
    }

    @Override
    public void handleOut(Context context) {

        System.out.println("I was paused: "  + context.get("video"));
    }
}
